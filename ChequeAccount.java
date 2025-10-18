import java.util.Date;

public class ChequeAccount extends Account {
    private double overdraftLimit = 0.0;
    private String bankName;
    private String branchAddress;

    public ChequeAccount(String acc, double bal, String branch, Date opened,
                         String bankName, String branchAddress) {
        super(acc, bal, branch, opened);
        this.bankName = bankName;
        this.branchAddress = branchAddress;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
    public String getBankName()        { return bankName; }
    public String getBranchAddress()   { return branchAddress; }

    public boolean withdraw(double amt) {
        if (amt > 0 && (getBalance() + overdraftLimit) >= amt) {
            setBalance(getBalance() - amt);
            return true;
        }
        return false;
    }

    @Override
    public double calculateMonthlyInterest() {
        return 0;
    }

    public void updateOverdraftLimit(double limit) {
        if (limit >= 0) overdraftLimit = limit;
    }

    public String toString() {
        return "Cheque Account [" + bankName + "]" +
                "\nAccount No: " + getAccountNumber() +
                "\nBalance: P" + getBalance() +
                "\nOverdraft Limit: P" + overdraftLimit +
                "\nBranch: " + branchAddress +
                "\nOpened: " + getOpenedDate();
    }
}