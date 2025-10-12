import java.util.Date;

public class ChequeAccount extends Account {
    private double overdraftLimit;
    private String bankName;
    private String branchAddress;

    public ChequeAccount(String accountNumber, double balance, String branch, Date openedDate, String bankName, String branchAddress) {
        super(accountNumber, balance, branch, openedDate);
        this.overdraftLimit = 0.0;
        this.bankName = bankName;
        this.branchAddress = branchAddress;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
    public String getBankName() { return bankName; }
    public String getBranchAddress() { return branchAddress; }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public void updateOverdraftLimit(double limit) {
        if (limit >= 0) {
            this.overdraftLimit = limit;
        }
    }
}