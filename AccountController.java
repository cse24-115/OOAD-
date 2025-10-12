import java.util.ArrayList;
import java.util.List;

public class AccountController implements Withdraw {
    private List<Account> accounts;

    public AccountController() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public double getBalance(String accountNumber) {
        Account account = getAccount(accountNumber);
        return (account != null) ? account.getBalance() : 0.0;
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        return processWithdrawal(accountNumber, amount);
    }

    public void addMonthlyInterest(String accountNumber) {
        Account account = getAccount(accountNumber);
        if (account instanceof InterestBearing) {
            ((InterestBearing) account).depositMonthlyInterest();
        }
    }

    @Override
    public boolean processWithdrawal(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account == null || amount <= 0) {
            System.out.println("Withdrawal failed: Invalid account or amount.");
            return false;
        }
        
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("Withdrawn " + amount + " from " + accountNumber + ". New balance: " + account.getBalance());
        } else {
            System.out.println("Withdrawal failed for " + accountNumber + ": Insufficient funds or restrictions.");
        }
        return success;
    }
}