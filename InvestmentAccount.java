import java.util.Date;

public class InvestmentAccount extends Account implements InterestBearing, Withdraw {
    private double interestRate;

    public InvestmentAccount(String acc, double bal, String branch, Date opened, double rate) {
        super(acc, bal, branch, opened);
        interestRate = rate;
    }

    public double getInterestRate() { return interestRate; }

    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= getBalance()) {
            setBalance(getBalance() - amt);
            System.out.println("Withdrawn P" + amt + " from " + getAccountNumber());
            return true;
        }
        System.out.println("Withdrawal failed: insufficient funds");
        return false;
    }

    @Override
    public double calculateMonthlyInterest() {
        return 0;
    }

    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    public void depositMonthlyInterest() {
        double interest = calculateInterest();
        deposit(interest);
        System.out.println("Monthly interest P" + interest + " added to " + getAccountNumber());
    }

    public boolean processWithdrawal(String acc, double amt) {
        return getAccountNumber().equals(acc) && withdraw(amt);
    }

    public String toString() {
        return "Investment Account [" + getAccountNumber() + "]" +
                "\nBalance: P" + getBalance() +
                "\nInterest Rate: " + (interestRate * 100) + "%" +
                "\nOpened: " + getOpenedDate();
    }
}