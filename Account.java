import java.util.Date;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Date openedDate;

    public Account(String accountNumber, double balance, String branch, Date openedDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.openedDate = openedDate;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance()        { return balance; }
    public void setBalance(double b)  { balance = b; }
    public String getBranch()         { return branch; }
    public Date getOpenedDate()       { return openedDate; }

    public void deposit(double amt) {
        if (amt > 0) balance += amt;
    }

    public abstract boolean withdraw(double amt);

    public abstract double calculateMonthlyInterest();

    public void updateOverdraftLimit(double limit) { /* default empty */ }

    public String toString() {
        return "Account No: " + accountNumber +
                "\nBalance: P" + balance +
                "\nBranch: " + branch +
                "\nOpened: " + openedDate;
    }
}