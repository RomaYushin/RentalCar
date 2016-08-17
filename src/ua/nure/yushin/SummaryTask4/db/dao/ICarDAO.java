package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.CarQualityClass;
import ua.nure.yushin.SummaryTask4.entity.CarStatus;

public interface ICarDAO {

	void insertNewCar(Car newCar);

	void removeParticularCar(Car carToRemove);
	
	List<Car> getAllCarsFromDB();
	
	//********************************
	public List<Car> getAllCarByModel ();
	//********************************
	
	void updateCarBrend (String newCarBrend);
	
	void updateCarModel (String newCarModel);
	
	void updateCarQualityClass (CarQualityClass newCarQualityClass);
	
	void updateCarRentalCost (int newCarRentalCost);
	
	void updateCarStatus (CarStatus newCarStatus);
	
	void updateCarYearOfIssue (Date newCarYearOfIssue);
	
	void updateCarBusyDates (List <Date> newCarBusyDates);

}
