package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface IOrderDate {
	
	void insertDatesForSpecifiedOrderById (List<Date> orderDates, int orderId) throws DBException;

}
