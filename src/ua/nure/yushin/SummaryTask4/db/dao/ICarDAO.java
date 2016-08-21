package ua.nure.yushin.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface ICarDAO {

	void insertNewCar(Car newCar) throws DBException;

	void removeParticularCarById(int removeCarId) throws DBException;
	
	List<Car> getAllCarsFromDB()throws DBException;
	
	Car getCarById (int id) throws DBException;
	
	void updateCar (Car updateCar) throws DBException;
	
	/*
	public List<Car> getAllCarByModel ();
 
	void updateCarBrend (String newCarBrend);
	
	void updateCarModel (String newCarModel);
	
	void updateCarQualityClass (CarQualityClass newCarQualityClass);
	
	void updateCarRentalCost (int newCarRentalCost);
	
	void updateCarStatus (CarStatus newCarStatus);
	
	void updateCarYearOfIssue (Date newCarYearOfIssue);
	
	void updateCarBusyDates (List <Date> newCarBusyDates);
	*/

}
