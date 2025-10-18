import java.util.Date;

public class SavingsAccount extends Account implements InterestBearing {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, String branch, Date openedDate) {
        super(accountNumber, balance, branch, openedDate);
        this.interestRate = 0.0005;
    }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() - 100.0) { // Minimum balance of 100
            return false;
        }
        System.out.println("Withdrawal failed: Minimum balance of 100 required!");
        return false;
    }

    @Override
    public double calculateMonthlyInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public double calculateInterest() {
        return 0;
    }

    @Override
    public void depositMonthlyInterest() {
        double interest = calculateMonthlyInterest();
        deposit(interest);
    }

    @Override
    public void updateOverdraftLimit(double limit) {
        // Savings accounts typically do not support overdrafts
        System.out.println("Overdraft limit not applicable for SavingsAccount.");
    }
}