package ua.nure.yushin.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface IUserDAO {

	void insertNewUser (User newUser) throws DBException;
	
	void removeParticularUser (User userToRemove) throws DBException;
	
	List <User> getAllUsersFromDB () throws DBException;
	
	User getUserByEmail (String userEmail)throws DBException;
	
	User getUserByEmailAndPassword (String email, String password) throws DBException;
	
	User getUserById (int userId) throws DBException;
	
	void checkIsUserAlreadyInDBByEmail (String userEmail) throws DBException;
	
	void updateUserById (User user) throws DBException;
	
	/*
	void updateUserPassSeries (String newUserPassSeries);
	
	void updateUserPassNumber (int newUserPassNumber);
	
	void updateUserPassSurname (String newUserPassSurname);
	
	void updateUserPassName (String newUserPassName);
	
	void updateUserPassPatronomic (String newUserPassPatronomic);
	
	void updateUserPassDateOfBirth(Date newUserPassDateOfBirth);
	
	void updateUserSex (Sex newUserSex);
	
	void updateUserBlockingById (int userId, boolean newUserBlocking) throws DBException;
	
	void updateUserPassword (String newUserPassword);
	
	void updateUserEmail (String newUserEmail);
	
	void updateUserRole (UserRole newUserRole);
	
	void updateUserLanguage (String newUserLanguage);
	*/
}
