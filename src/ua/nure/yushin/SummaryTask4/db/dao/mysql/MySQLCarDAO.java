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

public class MySQLCarDAO implements ICarDAO {

	private static final Logger LOG = Logger.getLogger(MySQLCarDAO.class);
	
	@Override
	public void insertNewCar(Car newCar) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void removeParticularCar(Car carToRemove) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public List<Car> getAllCarsFromDB() {
		//throw new UnsupportedOperationException();
		String query = "SELECT * FROM `car`";
		List <Car> cars = new ArrayList<>();
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarBusyDates carBusyDatesDAO = daoFactory.getCarBusyDatesDAO();
		//Connection connection;
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			LOG.info("getAllCarsFromDB start");	
			
			//connection.setAutoCommit(false);
			
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Car car = new Car ();
				car.setId(rs.getInt(1));
				car.setCarBrend(rs.getString(2));
				car.setCarModel(rs.getString(3));
				car.setCarQualityClass(CarQualityClass.getByName(rs.getString(4)));
				car.setCarRentalCost(rs.getInt(5));
				car.setCarStatus(CarStatus.getByName(rs.getString(6)));				
				car.setCarYearOfIssue(rs.getDate(7));
				car.setCarBusyDates(carBusyDatesDAO.getAllBusyDatesBySpecifiedCar(car));
				
				cars.add(car);
			}
			//connection.commit();
			
		} catch (Exception e) {
			LOG.error("Exception in MySQLCarDAO.getAllCarsFromDB: " + e);
			/*
			try {
				connection.rollback();
			} catch (SQLException ee) {
				LOG.error("SQLException in MySQLCarDAO.getAllCarsFromDB after trying rollback" + ee);
			}
			*/
		}
		
		return cars;
		
	}

	@Override
	public void updateCarBrend(String newCarBrend) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarModel(String newCarModel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarQualityClass(CarQualityClass newCarQualityClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarRentalCost(int newCarRentalCost) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarStatus(CarStatus newCarStatus) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarYearOfIssue(Date newCarYearOfIssue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateCarBusyDates(List<Date> newCarBusyDates) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Car> getAllCarByModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
