package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.util.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;

public class MySQLCarDAO implements ICarDAO {

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
		throw new UnsupportedOperationException();
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
