package ua.nure.yushin.SummaryTask4.db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DAOFactory {

	private static DataSource dataSource = getDataSource();

	private static Connection connection;

	public static DataSource getDataSource() {

		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/SummaryTask4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	public static Connection getConnection() {

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	
	public abstract ICarDAO getCarDAO ();
	
	public abstract IUserDAO getUserDAO ();
	
	public abstract IOrderDAO getOrderDAO ();
	
	public abstract IAccountDAO getAccountDAO ();
}
