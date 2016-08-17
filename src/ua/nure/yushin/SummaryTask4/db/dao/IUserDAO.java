package ua.nure.yushin.SummaryTask4.db.dao;

import java.util.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.entity.UserRole;

public interface IUserDAO {

	void insertNewUser (User newUser);
	
	void removeParticularUser (User userToRemove);
	
	List <User> getAllUserssFromDB ();
	
	boolean findSpecifiedUserInDB (User specifiedUser);
	
	User findUserByEmailAndPassword (String email, String password);
	
	void updateUserPassSeries (String newUserPassSeries);
	
	void updateUserPassNumber (int newUserPassNumber);
	
	void updateUserPassSurname (String newUserPassSurname);
	
	void updateUserPassName (String newUserPassName);
	
	void updateUserPassPatronomic (String newUserPassPatronomic);
	
	void updateUserPassDateOfBirth(Date newUserPassDateOfBirth);
	
	void updateUserSex (Sex newUserSex);
	
	void updateUserBlocking (boolean newUserBlocking);
	
	void updateUserPassword (String newUserPassword);
	
	void updateUserEmail (String newUserEmail);
	
	void updateUserRole (UserRole newUserRole);
	
	void updateUserLanguage (String newUserLanguage);
}
