package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLCarBusyDatesDAO implements ICarBusyDates {

	private static final Logger LOG = Logger.getLogger(MySQLCarBusyDatesDAO.class);

	@Override
	public void insertNewBusyDates (Car specifiedCar, List<Date> busyDates) throws DBException {
		
		String query = "INSERT INTO `car_busy_dates` (car_id, busyDate) VALUES (?, ?);";
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DAOFactory.getConnection();
			ps = connection.prepareStatement(query);
			
			connection.setAutoCommit(false);
			
			for (Date currentBusyDate: busyDates) {
				ps.setInt(1, specifiedCar.getId());
				ps.setDate(2, currentBusyDate);
				ps.addBatch();
			}
			ps.executeBatch();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error(ee);
			}
			LOG.error("Exception in MySQLCarBusyDatesDAO.insertNewBusyDates: " + e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_BYSY_DATE);
		} finally {
			try {connection.close();} catch (Exception e) {	LOG.error(e);}
			try {ps.close();} catch (Exception e) {	LOG.error(e);}
		}
		
		/*
		for (Date currentBusyDate: busyDates) {		
			
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection.prepareStatement(query)) {
				LOG.info("insertion of new User start");	
				
				ps.setInt(1, specifiedCar.getId());
				ps.setDate(2, currentBusyDate);		
				ps.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error("Exception in MySQLCarBusyDatesDAO.insertNewBusyDates: " + e);
				throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_BYSY_DATE);
			}
		}
		*/
	}
	
	@Override
	public void removeBusyDatesByOrderId (int orderId) throws DBException {
		
		LOG.info("removeBusyDatesByOrderId start");
		String query = "DELETE FROM `car_busy_dates` WHERE `car_id` = "
				+ "(SELECT car_id FROM `order` WHERE id = ?);";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setInt(1, orderId);
			ps.execute();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_DELETE_CAR_BUSY_DATES_BY_ORDER_ID);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_DELETE_CAR_BUSY_DATES_BY_ORDER_ID);
		}
		
	}

	@Override
	public List<Car> findAllFreeCarsByBusyDates (List<Date> busyDates) throws DBException {

		// найти все авто по каждой дате
		String query = "SELECT car_id FROM carBusyDates WHERE busyDate = ?";

		List<Integer> notAvailableCarsId = new ArrayList<>();
		List <Car> allCars = null;
		List <Car> freeCars = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DAOFactory.getConnection();
			ps = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			
			for (Date d : busyDates) {
				ps.setDate(1, d);
				ps.addBatch();
			}			
			notAvailableCarsId = Arrays.asList(ArrayUtils.toObject( ps.executeBatch()));
			
			DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
			ICarDAO iCarDAO = daoFactory.getCarDAO();
			allCars = iCarDAO.getAllCarsFromDB(); 
			
			for (Car c : allCars) {
				if (!notAvailableCarsId.contains(c.getId())) {
					freeCars.add(c);
				}
			}
			
			connection.commit();
		} catch (SQLException e) {
			/*
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error(ee);
			}
			*/
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_FIND_ALL_FREE_CARS_BY_BUSY_DATES);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_FIND_ALL_FREE_CARS_BY_BUSY_DATES);
		} finally {
			try {connection.close();} catch (Exception e) {	LOG.error(e);}
			try {ps.close();} catch (Exception e) {	LOG.error(e);}
		}
		return freeCars;
	}

	@Override
	public List<Date> getAllBusyDatesBySpecifiedCar(Car specifiedCar) throws DBException {
		
		LOG.info("getAllBusyDatesBySpecifiedCar start");
		String query = "SELECT busyDate FROM car_busy_dates WHERE car_id = ?;";
		List <Date> busyDates = new ArrayList<>();
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, specifiedCar.getId());	
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				busyDates.add(rs.getDate(1));
			}	
			
		} catch (SQLException e) {
			LOG.error("Exception in MySQLCarDAO.getAllCarsFromDB: ", e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_BUSY_DATES, e);
		}
		
		return busyDates;
	}

	

}
