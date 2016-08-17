package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.db.dao.ICarBusyDates;
import ua.nure.yushin.SummaryTask4.db.dao.ICarDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IOrderDAO;
import ua.nure.yushin.SummaryTask4.db.dao.IUserDAO;

public class MySQLDAOFactory extends DAOFactory {

	private static final MySQLDAOFactory MYSQL_DAO_FACTORY_INSTANCE = new MySQLDAOFactory();
	
	public static MySQLDAOFactory getMySQLDAOFactoryInstance() {
		return MYSQL_DAO_FACTORY_INSTANCE;
	}

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

	@Override
	public ICarBusyDates getCarBusyDatesDAO() {
		return new MySQLCarBusyDatesDAO();
	}

}
