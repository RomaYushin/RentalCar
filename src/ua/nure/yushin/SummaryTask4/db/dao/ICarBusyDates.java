package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface ICarBusyDates {
	
	void insertNewBusyDates (Car specifiedCar, List <Date> busyDates);
	
	void removeParticularBusyDate (Car specifiedCar, Date busyDates );
	
	List <Car> findAllFreeCarsByBusyDates (List<Date> busyDates);
	
	List <Date> getAllBusyDatesBySpecifiedCar (Car specifiedCar) throws DBException;

}
