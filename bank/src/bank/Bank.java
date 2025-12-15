package bank;
import java.util.ArrayList;

public class Bank implements BankInterface {
    private ArrayList<Account> accounts = new ArrayList<>();
    private double totalCapital; 
    private final double RESERVED_FUND = 1000.0; 

    public Bank() {
        this.totalCapital = RESERVED_FUND;
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // [추가 기능] 계좌 해지 (평가항목 5번)
    public boolean deleteAccount(String accountNo) {
        Account target = getAccount(accountNo);
        if (target != null) {
            accounts.remove(target);
            return true;
        }
        return false;
    }

    @Override
    public Account getAccount(String accountNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accountNo)) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public void printBalance() {
        for (Account acc : accounts) {
            System.out.println(acc.getAccountNo() + " 계좌잔고: " + acc.getBalance());
        }
    }

    @Override
    public void getBalanceAccount() {
        printBalance();
    }

    @Override
    public double getReserved() {
        return RESERVED_FUND;
    }

    @Override
    public double gebalance() {
        return totalCapital;
    }

    @Override
    public void addTotalBalance(double amount) {
        this.totalCapital += amount;
    }

    @Override
    public void subTotalBalance(double amount) {
        this.totalCapital -= amount;
        // [추가 기능] 지급준비금 체크 (평가항목 6번)
        if (this.totalCapital < RESERVED_FUND) {
            System.out.println("[Bank 경고] 은행 자본금이 지급준비금(" + RESERVED_FUND + ")보다 낮아졌습니다.");
        }
    }
    
    public int getAccountCount() {
        return accounts.size();
    }
}