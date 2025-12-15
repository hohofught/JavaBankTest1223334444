package bank;

public interface AccountOperationInterface {
	 public String getAccountNo();
	 public void deposit(double amount);
	 public void withdraw(double amount);
	 public void transfer(Account yours, double amount);
	 public double getBalance();	 
}