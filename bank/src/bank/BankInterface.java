package bank;

public interface BankInterface {
	public void addAccount(Account account);
	public void printBalance();
	public Account getAccount(String accountNo);
	public void getBalanceAccount();
	public double getReserved();
	public double geBalance();
	public void addTotalBalance(double amount);
	public void subTotalBalance(double amount); 
}