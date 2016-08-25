package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.DynamicAny.DynAnyOperations;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDates;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLOrderDAO implements IOrderDAO {

	private static final Logger LOG = Logger.getLogger(MySQLOrderDAO.class);
	/*
	@Override
	public void insertNewOrder(Order newOrder) {
		
		int current_id = 0;
		String query = "INSERT INTO `summary_task4_car_rental`.`order` "
				+ "(`car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, "
				+ "`endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`,"
				+ "`managerNameWhoClosedOrder`, `order_dates_id`); "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			LOG.info("insertion of new Order start");
			
			LOG.info("1. newOrder.getOrderCar().getId(): " + newOrder.getOrderCar().getId());
			LOG.info("2. newOrder.getOrderClient().getId(): " + newOrder.getOrderClient().getId());
			LOG.info("3: " + 2);
			LOG.info("4. String.valueOf(newOrder.isOrderPresenceOfTheDriver()): " + String.valueOf(newOrder.isOrderPresenceOfTheDriver()));
			LOG.info("5. newOrder.getOrderStartDate(): " + newOrder.getOrderStartDate());
			LOG.info("6. newOrder.getOrderEndDate(): " + newOrder.getOrderEndDate());
			LOG.info("7. newOrder.getOrderAccount().getId(): " + newOrder.getOrderAccount().getId());
			LOG.info("8. newOrder.getOrderStatus().toString(): " +  newOrder.getOrderStatus().toString());
			LOG.info("9. newOrder.getOrderRejectionReason(): " + newOrder.getOrderRejectionReason());
			
			ps.setInt(1, newOrder.getOrderCar().getId());
			ps.setInt(2, newOrder.getOrderClient().getId());
			ps.setString(3, String.valueOf(newOrder.isOrderPresenceOfTheDriver()));
			ps.setDate(4, newOrder.getOrderStartDate());
			ps.setDate(5, newOrder.getOrderEndDate());
			ps.setInt(6, newOrder.getOrderAccount().getId());
			ps.setString(7, newOrder.getOrderStatus().toString());
			ps.setString(8, newOrder.getOrderRejectionReason());	
			ps.setDate(9, newOrder.getCreateOrderDate());
			ps.setString(10, newOrder.getManagerNameWhoClosedOrder());
			ps.setNull(11, java.sql.Types.INTEGER);
			
			ps.executeUpdate();
			
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			while(rs.next()) {
				current_id = rs.getInt(1);
			}
			newOrder.setId(current_id);
			
			
			
		} catch (SQLException e) {
			//!!!
			newOrder.setId(-1);
			LOG.error("SQLException in MySQLUserDAO.insertNewOrder: " + e);
		}		
	}
	*/
	
	public void insertNewOrder(Order newOrder, List<Date> busyDates) throws DBException {
		
		LOG.info("insertion of new Order start");
		
		int counter = 0;
		int current_id = 0;
		
		String query_insertNewAccount = "INSERT INTO `summary_task4_car_rental`.`account` "
				+ "(`accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) " 
				+ "VALUES(?, ?, ?, ?);";
		
		String query_insertNewOrder = "INSERT INTO `summary_task4_car_rental`.`order` "
				+ "(`car_id`, `client_id`, `presenceOfTheDriver`, `startDate`, "
				+ "`endDate`, `account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`,"
				+ "`managerNameWhoClosedOrder`, `order_dates_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		String query_insertDatesForSpecifiedOrderById = "INSERT INTO `order_dates` "
				+ "(order_id, order_date) VALUES (?, ?);";
		
		String query_updateOrderDatesId = "UPDATE `order` SET `order_dates_id` = ? WHERE id = ?";
		
		String query_insertCarBusyDates = "INSERT INTO `car_busy_dates` (car_id, busyDate, order_id) VALUES (?, ?, ?);";
		
		Connection connection = null;
		PreparedStatement ps = null;
		//PreparedStatement ps2 = null;
		ResultSet rs = null;
		/*
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		IOrderDates iOrderDates = daoFactory.getOrderDatesDAO();
		ICarBusyDates iCarBusyDatesDAO = daoFactory.getCarBusyDatesDAO();
		*/
		
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query_insertNewAccount, PreparedStatement.RETURN_GENERATED_KEYS);
	
			// *****добавить счет			
			//iAccountDAO.insertNewAccount(newOrder.getOrderAccount());
			ps.setInt(1, newOrder.getOrderAccount().getAccountForRent());
			ps.setInt(2, newOrder.getOrderAccount().getAccountForRepair());
			ps.setString(3, String.valueOf(newOrder.getOrderAccount().isAccountRentPaid()));
			ps.setString(4, String.valueOf(newOrder.getOrderAccount().isAccountRepairPaid()));
			ps.executeUpdate();
			rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			while (rs.next()) {
				if (++counter > 1) {
					throw new SQLException("Must be one");
				}
				current_id = rs.getInt(1);
			}			
			counter = 0;
			newOrder.getOrderAccount().setId(current_id);
			
			//******добавить заказ
			ps = connection.prepareStatement(query_insertNewOrder, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, newOrder.getOrderCar().getId());
			ps.setInt(2, newOrder.getOrderClient().getId());
			ps.setString(3, String.valueOf(newOrder.isOrderPresenceOfTheDriver()));
			ps.setDate(4, newOrder.getOrderStartDate());
			ps.setDate(5, newOrder.getOrderEndDate());
			ps.setInt(6, newOrder.getOrderAccount().getId());
			ps.setString(7, newOrder.getOrderStatus().toString());
			ps.setString(8, newOrder.getOrderRejectionReason());	
			ps.setDate(9, newOrder.getCreateOrderDate());
			ps.setString(10, newOrder.getManagerNameWhoClosedOrder());
			ps.setNull(11, java.sql.Types.INTEGER);			
			ps.execute();
			
			//rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			rs = ps.getGeneratedKeys();
			
			while(rs != null && rs.next()) {
				if (++counter > 1 ) {
					throw new SQLException("Must be one");
				}
				current_id = rs.getInt(1);
			}
			counter = 0;
			newOrder.setId(current_id);
			//************* добавить даты заказа в order_dates по current_id
			//iOrderDates.insertDatesForSpecifiedOrderById(busyDates, current_id);
			for (Date currentOrderDate: busyDates) {		
				ps = connection.prepareStatement(query_insertDatesForSpecifiedOrderById);					
				LOG.info("insertion of date start");	
					
				ps.setInt(1, current_id);
				ps.setDate(2, currentOrderDate);		
				ps.executeUpdate();				
			}
			
			// *******дабавить в таблицу order.order_date_id id заказа
			ps = connection.prepareStatement( query_updateOrderDatesId);
			ps.setInt(1, newOrder.getId());
			ps.setInt(2, newOrder.getId());		
			ps.executeUpdate();
			
			// *****добавить забронированные даты заказа к авто			
			//iCarBusyDatesDAO.insertNewBusyDates(newOrder.getOrderCar(), busyDates);
			
			for (Date currentBusyDate: busyDates) {		
				ps = connection.prepareStatement(query_insertCarBusyDates);
				LOG.info("insertion of new User start");	
					
				ps.setInt(1, newOrder.getOrderCar().getId());
				ps.setDate(2, currentBusyDate);
				ps.setInt(3, newOrder.getId());
				ps.executeUpdate();
			}
			
			//newOrder.setId(current_id);
			connection.commit();	
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error("Exception in MySQLOrderDAO rollback: " + ee);
			}
			newOrder.setId(-1);
			LOG.error("SQLException in MySQLOrderDAO.insertNewOrder: " + e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_ORDER);
		} finally {
			//close connection
            try {connection.close();} catch (SQLException e) {}
            try {ps.close();} catch (SQLException e) {}
		}	
	}

	@Override
	public List<Order> getAllOrdersFromDB() throws DBException {

		LOG.info("getting all Orders start");
		String query = "SELECT * FROM `order`;";
		List <Order> orders = new ArrayList<>();
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		/*
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		*/
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			while (rs.next()) {
				Order order = new Order();
				
				order.setId(rs.getInt(1));
				order.setOrderCar(iCarDAO.getCarById(rs.getInt(2)));
				order.setOrderClient(iUserDAO.getUserById(rs.getInt(3)));
				order.setOrderPresenceOfTheDriver(Boolean.valueOf(rs.getString(4)));
				order.setOrderStartDate(rs.getDate(5));
				order.setOrderEndDate(rs.getDate(6));
				order.setOrderAccount(iAccountDAO.getAccountById(rs.getInt(7)));
				order.setOrderStatus(OrderStatus.getByName(rs.getString(8)));
				order.setOrderRejectionReason(rs.getString(9));
				order.setCreateOrderDate(rs.getDate(10));
				order.setManagerNameWhoClosedOrder(rs.getString(11));
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ORDERS, e);
		} 
		return orders;
	}
	
	@Override
	public Order getOrderById(int orderId) throws DBException {
		
		LOG.info("getting Order by ID start");
		
		String query = "SELECT * FROM `order` WHERE id = ?;";
		
		int counter = 0;
		Order order = new Order();
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, orderId);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {					
				if (++counter > 1) {
					throw new SQLException("must be one order");
				}
				order.setId(rs.getInt(1));
				order.setOrderCar(iCarDAO.getCarById(rs.getInt(2)));
				order.setOrderClient(iUserDAO.getUserById(rs.getInt(3)));
				order.setOrderPresenceOfTheDriver(Boolean.valueOf(rs.getString(4)));
				order.setOrderStartDate(rs.getDate(5));
				order.setOrderEndDate(rs.getDate(6));
				order.setOrderAccount(iAccountDAO.getAccountById(rs.getInt(7)));
				order.setOrderStatus(OrderStatus.getByName(rs.getString(8)));
				order.setOrderRejectionReason(rs.getString(9));
				order.setCreateOrderDate(rs.getDate(10));
				order.setManagerNameWhoClosedOrder(rs.getString(11));							
			}
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ID, e);
		} 
		return order;

	}
	
	@Override
	public int getAccountIdByOrderIdFromOrder (int orderId) {
		
		String query = "SELECT `order`.`account_id` FROM `order` WHERE id = ?;";
		int account_id = 0;
		
		try ( Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, orderId);
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				account_id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			LOG.error("ERROR in getAccountIdByOrderId" + e);
		}		
		return account_id;
	}
	
	@Override
	public List<Order> getOrdersByOrderStatus (OrderStatus orderStatus) throws DBException {
		
		LOG.info("getting Order by OrderStatus start");
		
		String query = "SELECT * FROM `order` WHERE orderStatus = ?;";
		ResultSet rs = null;
		List <Order> orders = new ArrayList<>();
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		ICarDAO iCarDAO = daoFactory.getCarDAO();
		IUserDAO iUserDAO = daoFactory.getUserDAO();
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		
		try ( Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, orderStatus.toString());
			ps.execute();
			rs = ps.getResultSet();
			
			while (rs.next()) {
				Order order = new Order ();
				order.setId(rs.getInt(1));
				order.setOrderCar(iCarDAO.getCarById(rs.getInt(2)));
				order.setOrderClient(iUserDAO.getUserById(rs.getInt(3)));
				order.setOrderPresenceOfTheDriver(Boolean.valueOf(rs.getString(4)));
				order.setOrderStartDate(rs.getDate(5));
				order.setOrderEndDate(rs.getDate(6));
				order.setOrderAccount(iAccountDAO.getAccountById(rs.getInt(7)));
				order.setOrderStatus(OrderStatus.getByName(rs.getString(8)));
				order.setOrderRejectionReason(rs.getString(9));
				order.setCreateOrderDate(rs.getDate(10));
				order.setManagerNameWhoClosedOrder(rs.getString(11));
				
				orders.add(order);
			}			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ORDERSTATUS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ORDER_BY_ORDERSTATUS, e);
		} 
		return orders;
	}
	
	@Override
	public boolean deleteOrderById (int orderId) {

		String query = "DELETE FROM `summary_task4_car_rental`.`order` WHERE `id`= ?;";
		boolean flag = true;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			
			// получить id счета перед удалением
			int account_id = getAccountIdByOrderIdFromOrder(orderId);			
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.executeUpdate(query);			
			
			// удалить счет
			DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
			IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
			iAccountDAO.deleteAccountById (orderId);			
			
			connection.commit();
		} catch (SQLException e) {
			flag = false;
			try {
				connection.rollback();
			} catch (SQLException ee) {
				
			}
			
		} finally {
			//close connection
            try {connection.close();} catch (SQLException e) {}
            try {ps.close();} catch (SQLException e) {}
		}		
		return flag;
	}	

	@Override
	public void updateOrderCar(Car newOrderCar) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderClient(User newOrderClient) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderManager(User newOrderManager) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderPresenceOfTheDriver(boolean newOrderPresenceOfTheDriver) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderStartDate(Date newOrderStartDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderEndDate(Date newOrderEndDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderAccount(int newOrderAccount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderStatusById (int orderId, OrderStatus newOrderStatus)	throws DBException {
		
		String query = "UPDATE `order` SET `orderStatus`= ? WHERE `id`= ?;";
		
		String query2 = "DELETE FROM `car_busy_dates` WHERE `order_id` = ?;";

		if (newOrderStatus.equals(OrderStatus.CLOSE)) {	
			
			LOG.info("closing order start");
			
			Connection connection = null;
			PreparedStatement ps = null;
			
			try {
				connection = DAOFactory.getConnection();
				connection.setAutoCommit(false);
				
				ps = connection.prepareStatement(query);
				ps.setString(1, newOrderStatus.toString());
				ps.setInt(2, orderId);
				ps.executeUpdate();	
				
				ps = connection.prepareStatement(query2);
				ps.setInt(1, orderId);
				ps.execute();		
				
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException ee) {
					throw new DBException(ExceptionMessages.EXCEPTION_ROLLBACK_CLOSE_ORDER, ee);
				}
				throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_CLOSE_ORDER);
				
			} finally {
				//close connection
	            try {connection.close();} catch (SQLException e) {}
	            try {ps.close();} catch (SQLException e) {}
			}		
		} else {	
			
			LOG.info("update orderStatus start");
			try (Connection connection = DAOFactory.getConnection();
					PreparedStatement ps = connection.prepareStatement(query)) {				
				
				ps.setString(1, newOrderStatus.toString());
				ps.setInt(2, orderId);
				ps.executeUpdate();				
				
			} catch (SQLException e) {
				LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ORDER);
				throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ORDER);
			}		
		}		
	}

	@Override
	public void updateRejectionReasonById (int orderId, String newRejectionReason) throws DBException {
		
		String query = "UPDATE `order` SET `rejectionReason`= ? WHERE `id`= ?;";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, newRejectionReason);
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_REJECTION_REASON);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_REJECTION_REASON);
		}	
	}
}
