package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDates;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLOrderDatesDAO implements IOrderDates {
	
	private static final Logger LOG = Logger.getLogger(MySQLOrderDatesDAO.class);

	@Override
	public void insertDatesForSpecifiedOrderById(List<Date> orderDates, int orderId) throws DBException {

		String query = "INSERT INTO `order_dates` (order_id, order_date) VALUES (?, ?);";
		
		for (Date currentOrderDate: orderDates) {		
			
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection.prepareStatement(query)) {
				
				LOG.info("insertion of date start");	
				
				ps.setInt(1, orderId);
				ps.setDate(2, currentOrderDate);		
				ps.executeUpdate();
				
			} catch (SQLException e) {
				LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_ORDER_DATES  + e);
				throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_ORDER_DATES);
			}
		}
		
	}

}
