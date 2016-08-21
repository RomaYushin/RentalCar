package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.DynamicAny.DynAnyOperations;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Car;
import ua.nure.yushin.SummaryTask4.entity.Order;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.entity.User;

public class MySQLOrderDAO implements IOrderDAO {

	private static final Logger LOG = Logger.getLogger(MySQLOrderDAO.class);
	
	@Override
	public void insertNewOrder(Order newOrder) {
		
		int current_id = 0;
		String query = "INSERT INTO `summary_task4_car_rental`.`order` "
				+ "(`car_id`, `client_id`, `manager_id`, `presenceOfTheDriver`, "
				+ "`startDate`, `endDate`, `account_id`, `orderStatus`, `rejectionReason`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			LOG.info("insertion of new Order start");
			/*
			LOG.info("1. newOrder.getOrderCar().getId(): " + newOrder.getOrderCar().getId());
			LOG.info("2. newOrder.getOrderClient().getId(): " + newOrder.getOrderClient().getId());
			LOG.info("3: " + 2);
			LOG.info("4. String.valueOf(newOrder.isOrderPresenceOfTheDriver()): " + String.valueOf(newOrder.isOrderPresenceOfTheDriver()));
			LOG.info("5. newOrder.getOrderStartDate(): " + newOrder.getOrderStartDate());
			LOG.info("6. newOrder.getOrderEndDate(): " + newOrder.getOrderEndDate());
			LOG.info("7. newOrder.getOrderAccount().getId(): " + newOrder.getOrderAccount().getId());
			LOG.info("8. newOrder.getOrderStatus().toString(): " +  newOrder.getOrderStatus().toString());
			LOG.info("9. newOrder.getOrderRejectionReason(): " + newOrder.getOrderRejectionReason());
			*/
			ps.setInt(1, newOrder.getOrderCar().getId());
			ps.setInt(2, newOrder.getOrderClient().getId());
			ps.setInt(3,  2);
			ps.setString(4, String.valueOf(newOrder.isOrderPresenceOfTheDriver()));
			ps.setDate(5, newOrder.getOrderStartDate());
			ps.setDate(6, newOrder.getOrderEndDate());
			ps.setInt(7, newOrder.getOrderAccount().getId());
			ps.setString(8, newOrder.getOrderStatus().toString());
			ps.setString(9, newOrder.getOrderRejectionReason());		
			
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
	
	public void insertNewOrder(Order newOrder, List<Date> busyDates) {
		
		LOG.info("insertion of new Order start");
		
		int current_id = 0;
		String query = "INSERT INTO `summary_task4_car_rental`.`order` "
				+ "(`car_id`, `client_id`, `presenceOfTheDriver`,`startDate`, `endDate`, "
				+ "`account_id`, `orderStatus`, `rejectionReason`, `createOrderDate`, `managerNameWhoClosedOrder`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		/*
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
		*/
		try {
			connection = DAOFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(query);

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
			
			ps.execute();
			
			rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			
			while(rs.next()) {
				current_id = rs.getInt(1);
			}
			
			// добавить забронированные даты заказа к авто
			DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
			ICarBusyDates iCarBusyDatesDAO = daoFactory.getCarBusyDatesDAO();
			iCarBusyDatesDAO.insertNewBusyDates(newOrder.getOrderCar(), busyDates);
			
			newOrder.setId(current_id);
			connection.commit();	
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error("Exception in MySQLOrderDAO rollback: " + ee);
			}
			newOrder.setId(-1);
			LOG.error("SQLException in MySQLOrderDAO.insertNewOrder: " + e);
		} finally {
			//close connection
            try {connection.close();} catch (SQLException e) {}
            try {ps.close();} catch (SQLException e) {}
		}	
	}

	@Override
	public List<Order> getAllOrdersFromDB() {
		// throw new UnsupportedOperationException();
		return null;
	}
	
	@Override
	public Order getOrderById(int orderId) {
		/*
		String query = "SELECT * FROM `order` WHERE id = ?;";
		
		try ( Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, orderId);
			ps.executeUpdate();
			
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Order newOrder = new Order();
				
				newOrder.setId(rs.getInt(1));
				newOrder.set
			}
			
		} catch (SQLException e) {
			
		}
		return null;*/
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getAccountIdByOrderIdFromOrder (int orderId) {
		
		String query = "SELECT `order`.`account_id` FROM `order` WHERE id = ?;";
		int account_id = 0;
		
		try ( Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, orderId);
			ps.executeUpdate();
			
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
	public boolean deleteOrderById(int orderId) {

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
	public void updateOrderStatus(OrderStatus newOrderStatus) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateOrderRejectionReason(String newOrderRejectionReason) {
		throw new UnsupportedOperationException();
	}
}
