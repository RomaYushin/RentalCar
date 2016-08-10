package ua.nure.yushin.SummaryTask4.db.dao;


import ua.nure.yushin.SummaryTask4.entity.Account;

public interface IAccountDAO {
	
	void insertNewAccount (Account newAccount);
	
	void updateAccountForRent(Account newAccountForRent);
	
	void updateAccountForRepair(Account newAccountForRepair);
	
	void updateAccountRentPaid (Account newAccountRentPaid);
	
	void updateAccountRepairPaid (Account newAccountRepairPaid);

}
