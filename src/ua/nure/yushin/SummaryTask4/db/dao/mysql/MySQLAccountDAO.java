package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.SQLError;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.entity.Account;
import ua.nure.yushin.SummaryTask4.entity.OrderStatus;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLAccountDAO implements IAccountDAO {

	private static final Logger LOG = Logger.getLogger(MySQLAccountDAO.class);
	
	@Override
	public void insertNewAccount(Account newAccount) {
		
		LOG.info("insertion of new Account start");
		int current_id = 0;
		String query = "INSERT INTO `summary_task4_car_rental`.`account` "
				+ "(`accountForRent`, `accountForRepair`, `accountRentPaid`, `accountRepairPaid`) " 
				+ "VALUES(?, ?, ?, ?);";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			
			ps.setInt(1, newAccount.getAccountForRent());
			ps.setInt(2, newAccount.getAccountForRepair());
			ps.setString(3, String.valueOf(newAccount.isAccountRentPaid()));
			ps.setString(4, String.valueOf(newAccount.isAccountRepairPaid()));
			ps.executeUpdate();

			//ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				current_id = rs.getInt(1);
			}			
			newAccount.setId(current_id);
		} catch (SQLException e) {
			LOG.error("Exception in MySQLAccountDAO.insertNewAccount: " + e);
			newAccount.setId(-1);
		}				
	}
	
	@Override
	public void updateAccountForRentByOrderId (int orderId, boolean value) throws DBException {
		
		LOG.info("updateAccountForRentByOrderId start");
		LOG.info("orderId: " + orderId);
		LOG.info("value: " + value);
		
		String query = "UPDATE `account` SET `accountRentPaid`= ? WHERE `id` = "
				+ "(SELECT `order`.`account_id` FROM `order` WHERE `order`.`id` = ?);";
		
		String query2 = "UPDATE `order` SET `orderStatus`= ? WHERE `id`= ?;";

		String query3 = "UPDATE `order` SET `rejectionReason`= ? WHERE `id`= ?;";
		
		/*
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
		*/
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DAOFactory.getConnection();
			ps = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			
			ps.setString(1, String.valueOf(value));
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
			if (value) {
				ps = connection.prepareStatement(query2);
				ps.setString (1, OrderStatus.UNTREATED.toString());
				ps.setInt(2, orderId);
				ps.executeUpdate();		
				
				ps = connection.prepareStatement(query3);				
				ps.setString(1, "-");
				ps.setInt(2, orderId);
				ps.executeUpdate();	
			}
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error(ee);
			}
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT);		
		} finally {
			try {connection.close();} catch (Exception e) {	LOG.error(e);}
			try {ps.close();} catch (Exception e) {	LOG.error(e);}
		}	
	}
	
	@Override
	public void updateAccountForRepairByOrderId(int orderId, boolean value) throws DBException {
		
		String query = "UPDATE `account` SET `accountRepairPaid`= ? WHERE `id` = "
				+ "(SELECT `order`.`account_id` FROM `order` WHERE id = ?);";
		
		String query2 = "UPDATE `order` SET `orderStatus`= ? WHERE `id`= ?;";

		String query3 = "UPDATE `order` SET `rejectionReason`= ? WHERE `id`= ?;";
		
		Connection connection = null;
		PreparedStatement ps = null;

		/*
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
		*/
		try {
			connection = DAOFactory.getConnection();
			ps = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			
			ps.setString(1, String.valueOf(value));
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
			if (value) {
				ps = connection.prepareStatement(query2);
				ps.setString (1, OrderStatus.UNTREATED.toString());
				ps.setInt(2, orderId);
				ps.executeUpdate();		
				
				ps = connection.prepareStatement(query3);				
				ps.setString(1, "-");
				ps.setInt(2, orderId);
				ps.executeUpdate();	
			}
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception ee) {
				LOG.error(ee);
			}
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT);		
		} finally {
			try {connection.close();} catch (Exception e) {	LOG.error(e);}
			try {ps.close();} catch (Exception e) {	LOG.error(e);}
		}
	}

	@Override
	public void deleteAccountById (int account_id) throws DBException {
		
		String query = "DELETE FROM `account` WHERE `id`= ?;";
		
		LOG.info("deleteAccountById start");
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, account_id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error("ERROR in deleteAccountById ", e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_DELETE_ACCOUNT, e);
		}		
	}
	
	@Override
	public Account getAccountById (int account_id) throws DBException {
		
		LOG.info("get AccountById start");
		
		String query = "SELECT * FROM `account` WHERE id = ?;";
		Account account = new Account();
		ResultSet rs = null;
		int counter = 0;
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setInt(1, account_id);
			ps.execute();
			rs = ps.getResultSet();
			
			while (rs.next()) {
				if (++counter > 1) {
					throw new SQLException("Must be one account");
				}
				account.setId(rs.getInt(1));
				account.setAccountForRent(rs.getInt(2));
				account.setAccountForRepair(rs.getInt(3));
				account.setAccountRentPaid(Boolean.valueOf(rs.getString(4)));
				account.setAccountRepairPaid(Boolean.valueOf(rs.getString(5)));
			}			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ACCOUNT_BY_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ACCOUNT_BY_ID, e);
		}
		return account;
	}

	@Override
	public List<Account> getAccountsByRepairPaid (boolean state) throws DBException {
	
		LOG.info("getAccountsByRepairPaid start");
		
		String query = "SELECT * FROM account WHERE `accountRepairPaid` = ?";
		List <Account> accounts = new ArrayList<>();
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, String.valueOf(state));
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			while (rs.next()) {
				Account account = new Account();
				
				account.setId(rs.getInt(1));
				account.setAccountForRent(rs.getInt(2));
				account.setAccountForRepair(rs.getInt(3));
				account.setAccountRentPaid(Boolean.valueOf(rs.getString(4)));
				account.setAccountRepairPaid(Boolean.valueOf(rs.getString(5)));
				
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ACCOUNTS_WITH_UNPAID_REPAIRS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_ACCOUNTS_WITH_UNPAID_REPAIRS, e);
		}
		return accounts;
		
	}
	
	@Override
	public void updateAccountForRent(Account newAccountForRent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateAccountForRepair(Account newAccountForRepair) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateAccountRentPaid(Account newAccountRentPaid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateAccountRepairPaid(Account newAccountRepairPaid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateAccountForRepairAndRepairPaidByOrderId(int orderId, int priceForRepair, boolean repairPaid)
			throws DBException {
		
		LOG.info("updateAccountForRepairAndRepairPaidByOrderId start");
		
		String query = "UPDATE `account` SET `accountForRepair`= ?, `accountRepairPaid` = ? "
				+ "WHERE `id` = (SELECT `account_id` FROM `order` WHERE `id` = ?);";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, priceForRepair);
			ps.setString(2, String.valueOf(repairPaid));
			ps.setInt(3, orderId);
			ps.execute();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT_FOR_REPAIR_AND_REPAIR_PAID_BY_ORDER_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_ACCOUNT_FOR_REPAIR_AND_REPAIR_PAID_BY_ORDER_ID, e);
		}		
	}

		
}


































