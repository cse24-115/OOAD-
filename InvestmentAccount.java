import java.util.Date;

public class InvestmentAccount extends Account implements InterestBearing, Withdraw {
    private double interestRate;

    public InvestmentAccount(String accountNumber, double balance, String branch, Date openedDate) {
        super(accountNumber, balance, branch, openedDate);
        this.interestRate = 0.05; // Default 5% interest rate
    }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        if (interestRate >= 0) {
            this.interestRate = interestRate;
        } else {
            System.out.println("Interest rate cannot be negative!");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() - 500.0) { // Minimum balance of 500
            return super.withdraw(amount);
        }
        System.out.println("Withdrawal failed: Minimum balance of 500 required!");
        return false;
    }

    @Override
    public double calculateMonthlyInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void depositMonthlyInterest() {
        double interest = calculateMonthlyInterest();
        deposit(interest);
    }

    @Override
    public void updateOverdraftLimit(double limit) {
        // Investment accounts typically don't support overdrafts; do nothing or log
        System.out.println("Overdraft limit not applicable for InvestmentAccount.");
    }

    @Override
    public boolean processWithdrawal(String accountNumber, double amount) {
        if (accountNumber == null || amount <= 0) {
            System.out.println("Invalid account number or amount for withdrawal!");
            return false;
        }
        // Assuming this is the current account; adjust if account lookup is needed
        if (getAccountNumber().equals(accountNumber)) {
            return withdraw(amount);
        }
        System.out.println("Account number mismatch for withdrawal!");
        return false;
    }
}