package bank;

public class Account implements AccountOperationInterface {
    protected String accountNo;
    protected double balance;
    protected Bank bank;

    public Account(String accountNo, Bank bank) {
        this.accountNo = accountNo;
        this.bank = bank;
        this.balance = 0.0;
        this.bank.addAccount(this); 
    }

    @Override
    public String getAccountNo() {
        return accountNo;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
        this.bank.addTotalBalance(amount);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
            this.bank.subTotalBalance(amount);
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    @Override
    public void transfer(Account yours, double amount) {
        if (this instanceof MinusAccount) {
             MinusAccount ma = (MinusAccount)this;
             if (ma.checkLimit(amount)) {
                 this.balance -= amount;
                 yours.deposit(amount); 
                 this.bank.subTotalBalance(amount); 
             } else {
                 System.out.println("한도 초과시 송금되지 않습니다."); 
             }
        } else {
            if (balance >= amount) {
                this.balance -= amount;
                yours.deposit(amount);
                this.bank.subTotalBalance(amount);
            } else {
                System.out.println("잔액이 부족합니다.");
            }
        }
    }
}