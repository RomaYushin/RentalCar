package ua.nure.yushin.SummaryTask4.db.dao;


import java.util.List;

import ua.nure.yushin.SummaryTask4.entity.Account;
import ua.nure.yushin.SummaryTask4.exception.DBException;

public interface IAccountDAO {
	
	void insertNewAccount (Account newAccount);	
	
	void deleteAccountById (int account_id) throws DBException;
	
	Account getAccountById (int account_id) throws DBException;
	
	List <Account> getAccountsByRepairPaid (boolean state) throws DBException;
	
	void updateAccountForRentByOrderId(int orderId, boolean value) throws DBException;
	
	void updateAccountForRepairByOrderId(int orderId, boolean value) throws DBException;
	
	void updateAccountForRepairAndRepairPaidByOrderId (int orderId, 
			int priceForRepair, boolean repairPaid) throws DBException;
	
	
	
	
	
	
	void updateAccountForRent (Account newAccountForRent);
	
	void updateAccountForRepair (Account newAccountForRepair);
	
	void updateAccountRentPaid (Account newAccountRentPaid);
	
	void updateAccountRepairPaid (Account newAccountRepairPaid);

}
