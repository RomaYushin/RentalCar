package ua.nure.yushin.SummaryTask4.db.dao;


import ua.nure.yushin.SummaryTask4.entity.Account;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface IAccountDAO {
	
	void insertNewAccount (Account newAccount);	
	
	void deleteAccountById (int account_id);
	
	Account getAccountById (int account_id) throws DBException;
	
	void updateAccountForRentByOrderId(int orderId, boolean value) throws DBException;
	
	void updateAccountForRent (Account newAccountForRent);
	
	void updateAccountForRepair (Account newAccountForRepair);
	
	void updateAccountRentPaid (Account newAccountRentPaid);
	
	void updateAccountRepairPaid (Account newAccountRepairPaid);

}
