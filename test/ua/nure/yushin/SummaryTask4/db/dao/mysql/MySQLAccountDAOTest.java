package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.yushin.SummaryTask4.db.dao.DAOFactory;
import ua.nure.yushin.SummaryTask4.db.dao.DatabaseTypes;
import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.entity.Account;

public class MySQLAccountDAOTest {
	
	private static MySQLAccountDAO mysqlAccountDAO;
	private static Account account;

	@BeforeClass
	public static void setUpBeforeClass () {
		DAOFactory daoFactory = DAOFactory.getFactoryByType(DatabaseTypes.MYSQL);
		IAccountDAO iAccountDAO = daoFactory.getAccountDAO();
		mysqlAccountDAO = (MySQLAccountDAO) iAccountDAO;
	}
	
	@AfterClass
	public static void tearDownAfterClass () throws Exception {
		// удалить из базы то, что добавили
		//ass
	}

	@Before 
	public void setUp () throws Exception {
		Account account = new Account();
	}
	
	@After
	public void tearDown () throws Exception {
		
	}
	
	
	@Test
	public final void testInsertNewAccount() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountForRentByOrderId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountForRepairByOrderId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteAccountById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAccountById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAccountsByRepairPaid() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountForRent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountForRepair() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountRentPaid() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountRepairPaid() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateAccountForRepairAndRepairPaidByOrderId() {
		fail("Not yet implemented"); // TODO
	}

}
