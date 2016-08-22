package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.entity.Account;
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
			
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
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
	public boolean updateAccountForRentByOrderId(int orderId, boolean value) {
		
		String query = "UPDATE `account` SET `accountRentPaid`= ? WHERE `id` = "
				+ "(SELECT `order`.`account_id` FROM `order` WHERE id = ?);";

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setString(1, String.valueOf(value));
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error("ERROR in updateAccountForRentByOrderId");
			return false;
		}		
		return true;
	}

	@Override
	public void deleteAccountById (int account_id) {
		
		String query = "DELETE FROM `summary_task4_car_rental`.`account` WHERE `id`= ;";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, account_id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error("ERROR in deleteAccountById ");
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

	
	
}
