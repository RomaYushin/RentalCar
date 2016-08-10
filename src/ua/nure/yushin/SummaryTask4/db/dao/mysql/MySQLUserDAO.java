package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import java.util.Date;
import java.util.List;

import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;
import ua.nure.yushin.SummaryTask4.entity.Sex;
import ua.nure.yushin.SummaryTask4.entity.User;
import ua.nure.yushin.SummaryTask4.entity.UserRole;

public class MySQLUserDAO implements IUserDAO {

	@Override
	public void insertNewUser(User newUser) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeParticularUser(User userToRemove) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getAllUserssFromDB() {
		//throw new UnsupportedOperationException();
		return null;
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
	public void updateUserBlocking(boolean newUserBlocking) {
		throw new UnsupportedOperationException();
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
