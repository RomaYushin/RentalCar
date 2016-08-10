package ua.nure.yushin.SummaryTask4.db.dao.mysql;

import ua.nure.yushin.SummaryTask4.db.dao.IAccountDAO;
import ua.nure.yushin.SummaryTask4.entity.Account;

public class MySQLAccountDAO implements IAccountDAO {

	@Override
	public void insertNewAccount(Account newAccount) {
		throw new UnsupportedOperationException();
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
