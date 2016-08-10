package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public ICarDAO getCarDAO() {
		return new MySQLCarDAO();
	}

	@Override
	public IUserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public IOrderDAO getOrderDAO() {
		return new MySQLOrderDAO();
	}

	@Override
	public IAccountDAO getAccountDAO() {
		return new MySQLAccountDAO();
	}

}
