import java.util.Date;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Date openedDate;

    public Account(String accountNumber, double balance, String branch, Date openedDate) {
        this.accountNumber = accountNumber;
        this.balance = (balance >= 0) ? balance : 0.0;
        this.branch = branch;
        this.openedDate = new Date(openedDate.getTime());
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    protected void setBalance(double balance) { this.balance = (balance >= 0) ? balance : 0.0; }
    public String getBranch() { return branch; }
    public Date getOpenedDate() { return new Date(openedDate.getTime()); }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public abstract void updateOverdraftLimit(double limit);
}