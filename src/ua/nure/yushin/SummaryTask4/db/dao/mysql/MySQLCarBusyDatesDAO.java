package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLCarBusyDatesDAO implements ICarBusyDates {

	private static final Logger LOG = Logger.getLogger(MySQLCarBusyDatesDAO.class);

	@Override
	public void insertNewBusyDates (Car specifiedCar, List<Date> busyDates) {
		
		String query = "INSERT INTO `summary_task4_car_rental`.`car_busy_dates` "
				+ "(car_id, busyDate) VALUES (?, ?);";
		
		for (Date currentBusyDate: busyDates) {		
			
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection.prepareStatement(query)) {
				LOG.info("insertion of new User start");	
				
				ps.setInt(1, specifiedCar.getId());
				ps.setDate(2, currentBusyDate);		
				ps.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error("Exception in MySQLCarBusyDatesDAO.insertNewBusyDates: " + e);
			}
		}
	}

	@Override
	public void removeParticularBusyDate(Car specifiedCar, Date busyDates) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Car> findAllFreeCarsByBusyDates (List<Date> busyDates) {

		// найти все авто по каждой дате
		String query = "SELECT car_id FROM carBusyDates WHERE busyDate = ?";
		//String query = "SELECT id FROM Car JOIN carbusydates  carBusyDates WHERE busyDate = ?";

		List<Car> allAvailableCars = new ArrayList<>();
		
		for (Date d : busyDates) {
			// поиск авто, занятого по этой дате
			Car c = new Car();
			// исключение его из списка авто
			allAvailableCars.remove(c);
		}
		return allAvailableCars;
	}

	@Override
	public List<Date> getAllBusyDatesBySpecifiedCar(Car specifiedCar) throws DBException {
		
		String query = "SELECT busyDate FROM car_busy_dates WHERE car_id =" + specifiedCar.getId() + ";";
		List <Date> busyDates = new ArrayList<>();
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			LOG.info("getAllBusyDatesBySpecifiedCar start");	
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
