package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.entity.UserRole;
import ua.nure.yushin.SummaryTask4.exception.DBException;
import ua.nure.yushin.SummaryTask4.exception.ExceptionMessages;

public class MySQLUserDAO implements IUserDAO {

	private static final Logger LOG = Logger.getLogger(MySQLUserDAO.class);

	@Override
	public void insertNewUser(User newUser) throws DBException {

		LOG.info("insertion of new User start");
		
		String query = "INSERT INTO `user` " + "(passSeries, passNumber, passName, passSurname, passPatronomic,"
				+ " passDateOfBirth, sex, userBlocking, password, email, role, language, confirmation) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, newUser.getUserPassSeries());
			ps.setInt(2, newUser.getUserPassNumber());
			ps.setString(3, newUser.getUserPassName());
			ps.setString(4, newUser.getUserPassSurname());
			ps.setString(5, newUser.getUserPassPatronomic());
			ps.setDate(6, newUser.getUserPassDateOfBirth());
			ps.setString(7, newUser.getUserSex().toString());
			ps.setString(8, String.valueOf(newUser.isUserBlocking()));
			ps.setString(9, newUser.getUserPassword());
			ps.setString(10, newUser.getUserEmail());
			ps.setString(11, newUser.getUserRole().toString());
			ps.setString(12, newUser.getUserLanguage());
			ps.setString(13, String.valueOf(false));
			ps.executeUpdate();

		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_USER, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_USER, e);
		}
	}

	@Override
	public void removeParticularUser(User userToRemove) throws DBException {

		LOG.info(" remove User start");
		
		String query = "DELETE FROM `user` WHERE `id`= ?;";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.setInt(1, userToRemove.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_REMOVE_USER);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_REMOVE_USER);
		}		
	}

	@Override
	public List<User> getAllUsersFromDB() throws DBException {

		String query = "SELECT * FROM `user`;";
		List<User> users = new ArrayList<>();

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.executeQuery();
			ResultSet rs = ps.getResultSet();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserPassSeries(rs.getString(2));
				user.setUserPassNumber(rs.getInt(3));
				user.setUserPassSurname(rs.getString(4));
				user.setUserPassName(rs.getString(5));
				user.setUserPassPatronomic(rs.getString(6));
				user.setUserPassDateOfBirth(rs.getDate(7));
				user.setUserSex(Sex.getByName(rs.getString(8)));
				user.setUserBlocking(Boolean.valueOf(rs.getString(9)));
				user.setUserPassword(rs.getString(10));
				user.setUserEmail(rs.getString(11));
				user.setUserRole(UserRole.getByName(rs.getString(12)));
				user.setUserLanguage(rs.getString(13));
				user.setUserConfirmation(Boolean.valueOf(rs.getString(14)));

				users.add(user);
			}

		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_USERS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_USERS, e);
		}
		return users;
	}

	@Override
	public void checkIsUserAlreadyInDBByEmail(String userEmail) throws DBException {

		LOG.info("getUserByEmailPassNumPassSeries start");

		String query = "SELECT `email` FROM `user` WHERE email = ?";

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {

			ps.setString(1, userEmail);
			ps.execute();

			ResultSet rs = ps.getResultSet();
			int i = 0;
			while (rs.next()) {
				++i;
			}
			if (i != 0) {
				LOG.error(ExceptionMessages.EXCEPTION_USER_ALREADY_EXIST);
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new DBException(ExceptionMessages.EXCEPTION_USER_ALREADY_EXIST);
		}
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws DBException {

		LOG.info("get User by email and password start");

		String query = "SELECT * FROM `user` WHERE email = ? AND password = ?";
		User user = new User();
		int accounter = 0;

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {
			LOG.info(" getUserByEmailAndPassword  start");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.execute();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				++accounter;

				user.setId(rs.getInt(1));
				user.setUserPassSeries(rs.getString(2));
				user.setUserPassNumber(rs.getInt(3));
				user.setUserPassSurname(rs.getString(4));
				user.setUserPassName(rs.getString(5));
				user.setUserPassPatronomic(rs.getString(6));
				user.setUserPassDateOfBirth(rs.getDate(7));
				user.setUserSex(Sex.getByName(rs.getString(8)));
				user.setUserBlocking(Boolean.parseBoolean(rs.getString(9)));
				user.setUserPassword(rs.getString(10));
				user.setUserEmail(rs.getString(11));
				user.setUserRole(UserRole.getByName(rs.getString(12)));
				user.setUserLanguage(rs.getString(13));
				user.setUserConfirmation(Boolean.valueOf(rs.getString(14)));
			}
			if (accounter > 1) {
				throw new SQLException("More than one user in DB!!! ");
			} else if (accounter == 0) {
				throw new SQLException("No such user in DB with email: \"" + email + "\" and password: **....");
			}
		} catch (SQLException e) {
			LOG.error("Exception in MySQLUserDAO.getUserByEmailAndPassword: " + e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_USER_BY_EMAIL_AND_PASSWORD + e, e);
		}
		return user;
	}

	@Override
	public User getUserById(int userId) throws DBException {

		LOG.info("getUserById start");

		String query = "SELECT * FROM `user` WHERE id = ?;";
		ResultSet rs = null;
		User user = new User();
		int counter = 0;

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setInt(1, userId);
			ps.execute();
			rs = ps.getResultSet();

			while (rs.next()) {
				if (++counter > 1) {
					throw new SQLException("Must be one user");
				}

				user.setId(rs.getInt(1));
				user.setUserPassSeries(rs.getString(2));
				user.setUserPassNumber(rs.getInt(3));
				user.setUserPassSurname(rs.getString(4));
				user.setUserPassName(rs.getString(5));
				user.setUserPassPatronomic(rs.getString(6));
				user.setUserPassDateOfBirth(rs.getDate(7));
				user.setUserSex(Sex.getByName(rs.getString(8)));
				user.setUserBlocking(Boolean.parseBoolean(rs.getString(9)));
				user.setUserPassword(rs.getString(10));
				user.setUserEmail(rs.getString(11));
				user.setUserRole(UserRole.getByName(rs.getString(12)));
				user.setUserLanguage(rs.getString(13));
				user.setUserConfirmation(Boolean.valueOf(rs.getString(14)));
			}
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_USER_BY_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_USER_BY_ID, e);
		}
		return user;
	}

	@Override
	public void updateUserById(User user) throws DBException {

		String query = "UPDATE `user` SET `passSeries`= ?, `passNumber`= ?, `passSurname`= ?, `passName`= ?, " 
				+ "`passPatronomic`= ?, `passDateOfBirth`= ?, `sex`= ?, `userBlocking`= ?, " 
				+ "`password`= ?, `email`= ?, `role`= ?, `language`= ?, `confirmation`= ? WHERE `id`= ?;";

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			LOG.info("update of User start");

			
			ps.setString(1, user.getUserPassSeries());
			ps.setInt(2, user.getUserPassNumber());
			ps.setString(3, user.getUserPassName());
			ps.setString(4, user.getUserPassSurname());
			ps.setString(5, user.getUserPassPatronomic());
			ps.setDate(6, user.getUserPassDateOfBirth());
			ps.setString(7, user.getUserSex().toString());
			ps.setString(8, String.valueOf(user.isUserBlocking()));
			ps.setString(9, user.getUserPassword());
			ps.setString(10, user.getUserEmail());
			ps.setString(11, user.getUserRole().toString());
			ps.setString(12, user.getUserLanguage());
			ps.setString(13,  String.valueOf(user.isUserConfirmation()));
			ps.setInt(14, user.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER, e);
		}
	}

	@Override
	public User getUserByEmail(String userEmail) throws DBException {

		LOG.info("getUserById start");

		String query = "SELECT * FROM `user` WHERE email = ?;";
		ResultSet rs = null;
		User user = new User();
		int counter = 0;

		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {

			ps.setString(1, userEmail);
			ps.execute();
			rs = ps.getResultSet();

			while (rs.next()) {
				if (++counter > 1) {
					throw new SQLException("Must be one user");
				}

				user.setId(rs.getInt(1));
				user.setUserPassSeries(rs.getString(2));
				user.setUserPassNumber(rs.getInt(3));
				user.setUserPassSurname(rs.getString(4));
				user.setUserPassName(rs.getString(5));
				user.setUserPassPatronomic(rs.getString(6));
				user.setUserPassDateOfBirth(rs.getDate(7));
				user.setUserSex(Sex.getByName(rs.getString(8)));
				user.setUserBlocking(Boolean.parseBoolean(rs.getString(9)));
				user.setUserPassword(rs.getString(10));
				user.setUserEmail(rs.getString(11));
				user.setUserRole(UserRole.getByName(rs.getString(12)));
				user.setUserLanguage(rs.getString(13));
				user.setUserConfirmation(Boolean.valueOf(rs.getString(14)));
			}
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_USER_BY_ID, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_USER_BY_ID, e);
		}
		return user;
	}

	/*
	 * @Override public void updateUserPassSeries(String newUserPassSeries) {
	 * throw new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserPassNumber(int newUserPassNumber) { throw
	 * new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserPassSurname(String newUserPassSurname) {
	 * throw new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserPassName(String newUserPassName) { throw
	 * new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserPassPatronomic(String
	 * newUserPassPatronomic) { throw new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserPassDateOfBirth(Date
	 * newUserPassDateOfBirth) { throw new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserSex(Sex newUserSex) { throw new
	 * UnsupportedOperationException(); }
	 * 
	 * 
	 * 
	 * @Override public void updateUserBlockingById (int userId, boolean
	 * newUserBlocking) throws DBException {
	 * 
	 * String query = "UPDATE `user` SET `userBlocking`= ? WHERE `id`= ?;";
	 * 
	 * try (Connection connection = DAOFactory.getConnection();
	 * PreparedStatement ps = connection.prepareStatement(query)){
	 * 
	 * ps.setString(1, String.valueOf(newUserBlocking)); ps.setInt(2, userId);
	 * ps.executeUpdate();
	 * 
	 * } catch (SQLException e) {
	 * LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER_BLOCKING, e);
	 * throw new
	 * DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER_BLOCKING, e);
	 * } }
	 * 
	 * 
	 * 
	 * @Override public void updateUserPassword(String newUserPassword) { throw
	 * new UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserEmail(String newUserEmail) { throw new
	 * UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserRole(UserRole newUserRole) { throw new
	 * UnsupportedOperationException(); }
	 * 
	 * @Override public void updateUserLanguage(String newUserLanguage) { throw
	 * new UnsupportedOperationException(); }
	 * 
	 */

}
