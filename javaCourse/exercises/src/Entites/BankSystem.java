package Entites;

public class BankSystem {

	private int accountID;
	private String userName;
	private double accountBalance;

	public BankSystem() {
	}

	public BankSystem(int accountID, String userName, double accountBalance) {
		this.accountID = accountID;
		this.userName = userName;
		this.accountBalance = accountBalance;
	}

	public BankSystem(int accountID, String userName) {
		this.accountID = accountID;
		this.userName = userName;
	}

	public int getAccountID() {
		return accountID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void depositeCash(double accountBalance) {
		this.accountBalance += accountBalance;
	}

	public void withdrawCash(double accountBalance) {
		this.accountBalance -= accountBalance;
	}
	
	public String toString() {
		return "Account: " + accountID + ", Holder: " + userName + ", Balance: R$" + String.format("%.2f", accountBalance);
	}

}
