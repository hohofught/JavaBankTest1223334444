package bank;

public class MinusAccount extends Account {
    private double creditLimit;
    private int rating; // 신용등급

    // 생성자: 신용등급(rating) 포함
    public MinusAccount(String accountNo, Bank bank, double creditLimit, int rating) {
        super(accountNo, bank);
        this.creditLimit = creditLimit;
        this.rating = rating;
    }

    public boolean checkLimit(double amount) {
        return (balance + creditLimit) >= amount;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public void withdraw(double amount) {
        if (checkLimit(amount)) {
            this.balance -= amount;
            this.bank.subTotalBalance(amount);
        } else {
            System.out.println("한도 초과입니다. 잔액 " + this.balance);
        }
    }
}