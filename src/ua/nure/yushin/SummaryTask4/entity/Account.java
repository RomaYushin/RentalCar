package ua.nure.yushin.SummaryTask4.entity;

public class Account extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1008601002561988952L;
	
	private int accountForRent;
	private int accountForRepair;
	private boolean accountRentPaid;
	private boolean accountRepairPaid;
	
	public Account() {
		super();
	}

	public Account(int accountForRent) {
		super();
		this.accountForRent = accountForRent;
		this.accountForRepair = 0;
		this.accountRentPaid = false;
		this.accountRepairPaid = true;
	}
	
	public int getAccountForRent() {
		return accountForRent;
	}
	
	public void setAccountForRent(int accountForRent) {
		this.accountForRent = accountForRent;
	}
	
	public int getAccountForRepair() {
		return accountForRepair;
	}
	
	public void setAccountForRepair(int accountForRepair) {
		this.accountForRepair = accountForRepair;
	}
	
	public boolean isAccountRentPaid() {
		return accountRentPaid;
	}
	
	public void setAccountRentPaid(boolean accountRentPaid) {
		this.accountRentPaid = accountRentPaid;
	}
	
	public boolean isAccountRepairPaid() {
		return accountRepairPaid;
	}
	
	public void setAccountRepairPaid(boolean accountRepairPaid) {
		this.accountRepairPaid = accountRepairPaid;
	}

	@Override
	public String toString() {
		return "Account [accountForRent=" + accountForRent + ", accountForRepair=" + accountForRepair
				+ ", accountRentPaid=" + accountRentPaid + ", accountRepairPaid=" + accountRepairPaid + "]";
	}
	
	
	
}
