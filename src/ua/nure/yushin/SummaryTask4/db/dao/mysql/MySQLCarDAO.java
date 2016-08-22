package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLCarDAO implements ICarDAO {

	private static final Logger LOG = Logger.getLogger(MySQLCarDAO.class);
	
	@Override
	public void insertNewCar(Car newCar) throws DBException {

		LOG.info("insertion of new Car start");
		
		int current_id = 0;
		String query = "INSERT INTO `car` (`brend`, `model`, `qualityClass`, "
				+ "`rentalCost`, `carStatus`, `yearOfIssue`) VALUES (?, ?, ?, ?, ?, ?);"; 
		String query2 = "SELECT LAST_INSERT_ID();";
		String query3 = "SET FOREIGN_KEY_CHECKS=0;";
		String query4 = "SET FOREIGN_KEY_CHECKS=1;";
		
		Connection connection = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;

		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			ps1 = connection.prepareStatement(query3);
			ps1.executeUpdate();			
			
			ps2 = connection.prepareStatement(query);
			ps2.setString(1, newCar.getCarBrend());
			ps2.setString(2, newCar.getCarModel());
			ps2.setString(3, newCar.getCarQualityClass().toString());
			ps2.setInt(4, newCar.getCarRentalCost());
			ps2.setString(5, newCar.getCarStatus().toString());
			ps2.setDate(6, newCar.getCarYearOfIssue());
			ps2.executeUpdate();
			 
			rs = ps2.executeQuery(query2);
			while (rs.next()) {
				current_id = rs.getInt(1);
			}			
			newCar.setId(current_id);
			
			ps3 = connection.prepareStatement(query4);
			ps3.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error("Exception in MySQLCarDAO rollback: " + ee);
			}
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_CAR, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_CAR, e);
		} finally {
			//close connection
            try {connection.close();} catch (SQLException e) {}
            try {ps1.close();} catch (SQLException e) {}
            try {ps2.close();} catch (SQLException e) {}
            try {ps3.close();} catch (SQLException e) {}
		}		
		LOG.info("insertion of new Car was successfull");
	}
	

	@Override
	public void removeParticularCarById(int removeCarId) throws DBException {
		
		//DELETE FROM `summary_task4_car_rental`.`car` WHERE `id`='41';
		LOG.info("removeParticularCarById start");
		
		String query = "DELETE FROM `car` WHERE `id`= ?;";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, removeCarId);
			ps.executeUpdate();
			
		} catch (Exception e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_REMOVE_CAR, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_REMOVE_CAR, e);
		}			
	}

	@Override
	public List<Car> getAllCarsFromDB() throws DBException {
		
		LOG.info(" getAllCarsFromDB start");

		String query = "SELECT * FROM `car`";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		List <Car> cars = new ArrayList<>();
		
		//DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		//ICarBusyDates carBusyDatesDAO = daoFactory.getCarBusyDatesDAO();

		/*
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
		*/
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query);			
			ps.execute();
			
			rs = ps.getResultSet();
			while (rs.next()) {
				Car car = new Car ();
				car.setId(rs.getInt(1));
				car.setCarBrend(rs.getString(2));
				car.setCarModel(rs.getString(3));
				car.setCarQualityClass(CarQualityClass.getByName(rs.getString(4)));
				car.setCarRentalCost(rs.getInt(5));
				car.setCarStatus(CarStatus.getByName(rs.getString(6)));				
				car.setCarYearOfIssue(rs.getDate(7));
				
				DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
				ICarBusyDates carBusyDatesDAO = daoFactory.getCarBusyDatesDAO();
				car.setCarBusyDates(carBusyDatesDAO.getAllBusyDatesBySpecifiedCar(car));
				
				cars.add(car);
				LOG.info(car.toString());	
			}
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ee) {
				LOG.error("SQLException in MySQLCarDAO.getAllCarsFromDB after trying rollback" + ee);
			}
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_CARS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_CARS, e);
			
		} finally {
			//close connection
            try {connection.close();} catch (SQLException e) {}
            try {ps.close();} catch (SQLException e) {}
		}	
		return cars;		
	}

	@Override
	public Car getCarById(int id) throws DBException {
		
		LOG.info(" getCarById start");
		String query = "SELECT * FROM `summary_task4_car_rental`.`car` WHERE id = ?";
		
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarBusyDates iCarBusyDates = daoFactory.getCarBusyDatesDAO();
		ResultSet rs = null;
		Car car = null;

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){			
			
			ps.setInt(1, id);
			ps.execute();			
			rs = ps.getResultSet();
			
			while (rs.next()) {
				car = new Car();
				
				car.setId(rs.getInt(1));
				car.setCarBrend(rs.getString(2));
				car.setCarModel(rs.getString(3));
				car.setCarQualityClass(CarQualityClass.getByName(rs.getString(4)));
				car.setCarRentalCost(rs.getInt(5));
				car.setCarStatus(CarStatus.getByName(rs.getString(6)));
				car.setCarYearOfIssue(rs.getDate(7));							
				car.setCarBusyDates(iCarBusyDates.getAllBusyDatesBySpecifiedCar(car));
			}
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_CAR_BY_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_CAR_BY_ID, e);
		}
		return car;
	}

	@Override
	public void updateCar(Car updateCar) throws DBException {
		
		LOG.info(" updateCar start");
		String query = "UPDATE `car` SET `brend` = ?, `model` = ?, `qualityClass` = ?, "
				+ "`rentalCost`=?, `carStatus`= ?, `yearOfIssue` = ? WHERE `id` = ?";
		// UPDATE `summary_task4_car_rental`.`car` SET `brend`='Lad', `model`='211',
		//`qualityClass`='ECONOM', `rentalCost`='4', `carStatus`='FRE', `yearOfIssue`='2016-08-0' WHERE `id`='46';

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, updateCar.getCarBrend());
			ps.setString(2, updateCar.getCarModel());
			ps.setString(3, updateCar.getCarQualityClass().toString());
			ps.setInt(4, updateCar.getCarRentalCost());
			ps.setString(5, updateCar.getCarStatus().toString());
			ps.setDate(6, updateCar.getCarYearOfIssue());
			ps.setInt(7, updateCar.getId());			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ALL_CAR_PARAMETRS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ALL_CAR_PARAMETRS, e);
		}
		
	}
	
	

}
