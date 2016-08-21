package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
		
		String query = "INSERT INTO `summary_task4_car_rental`.`user` "
				+ "(passSeries, passNumber, passName, passSurname, passPatronomic,"
				+ " passDateOfBirth, sex, userBlocking, password, email, role, language) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		int block = 0;
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			LOG.info("insertion of new User start");	
			
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
			ps.setString(12, "ru");			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_USER, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_INSERT_NEW_USER, e);
		}
	}

	@Override
	public void removeParticularUser(User userToRemove) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getAllUsersFromDB() throws DBException {
		
		String query = "SELECT * FROM `user`;";
		List <User> users = new ArrayList<>();
 		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)) {
			
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			
			while (rs.next()) {
				User user = new User ();
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
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_USERS, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_GET_ALL_USERS, e);
		}		
		return users;
	}
	
	@Override
	public boolean findSpecifiedUserInDB(User specifiedUser) {
		String query = "SELECT `user`.`passSeries`, `user`.`passNumber`, `user`.`email` "
				+ "FROM `user` WHERE passSeries = ? AND passNumber = ? AND email = ?";
		
		boolean flag = false;
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {
			LOG.info("findSpecifiedUserInDB start");
			
			ps.setString(1, specifiedUser.getUserPassSeries());
			ps.setInt(2, specifiedUser.getUserPassNumber());
			ps.setString(3, specifiedUser.getUserEmail());
			ps.execute();
			
			ResultSet rs = ps.getResultSet(); 
			int i = 0;
			while (rs.next()) {
				++i;
			}
			if (i == 0) {
				LOG.info("no specified User In DB " + i);
				flag = true;
			} else {
				LOG.info("specified User exist in DB " + i);
				flag = false;
			}			
		} catch (SQLException e) {
			LOG.error("Exception in MySQLUserDAO.insertNewUser: " + e);
		}		
		return flag;		
	}
	
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		
		/*
		String query = "SELECT `user`.`email`, `user`.`password` "
				+ "FROM `user` WHERE email = ? AND password = ?";
		*/
		String query = "SELECT * FROM `user` WHERE email = ? AND password = ?";
		
		User user = new User();
		int accounter = 0;
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);) {
			LOG.info(" findUserByEmailAndPassword start");
			
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
				
			}
			
			if (accounter == 0) {
				LOG.info("no specified User with email: " + email + " and password: " + password + " in DB");
				return null;
			} else if (accounter == 1) {
				LOG.info("specified User with email: " + email + " and password: " + password + " exist in DB");
				return user;
			} else {
				LOG.info("more than one user in DB!!!");
				return null;
			}
		} catch (SQLException e) {
			LOG.error("Exception in MySQLUserDAO.findUserByEmailAndPassword: " + e);
		}		
		return user;
	}	

	@Override
	public void updateUserPassSeries(String newUserPassSeries) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserPassNumber(int newUserPassNumber) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserPassSurname(String newUserPassSurname) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserPassName(String newUserPassName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserPassPatronomic(String newUserPassPatronomic) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserPassDateOfBirth(Date newUserPassDateOfBirth) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserSex(Sex newUserSex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserBlockingById (int userId, boolean newUserBlocking) throws DBException {
			
		String query = "UPDATE `user` SET `userBlocking`= ? WHERE `id`= ?;";
		
		try (Connection connection = DAOFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(query)){
			
			ps.setString(1, String.valueOf(newUserBlocking));
			ps.setInt(2, userId);
			ps.executeUpdate();		
			
		} catch (SQLException e) {
			LOG.error(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER_BLOCKING, e);
			throw new DBException(ExceptionMessages.EXCEPTION_CAN_NOT_UPDATE_USER_BLOCKING, e);
		}
	}

	@Override
	public void updateUserPassword(String newUserPassword) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserEmail(String newUserEmail) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserRole(UserRole newUserRole) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUserLanguage(String newUserLanguage) {
		throw new UnsupportedOperationException();
	}

	

}
