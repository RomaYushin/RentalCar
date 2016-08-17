package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Car;

public interface ICarBusyDates {
	
	void insertNewBusyDate (Car specifiedCar, Date busyDates);
	
	void removeParticularBusyDate (Car specifiedCar, Date busyDates );
	
	List <Car> findAllFreeCarsByBusyDates (List<Date> busyDates);
	
	List <Date> getAllBusyDatesBySpecifiedCar (Car specifiedCar);

}
