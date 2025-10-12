import java.util.Date;

public class Individual extends Customer {
    private Date dateOfBirth;

    public Individual(String idNumber, String phone, String email, Date dateOfBirth) {
        super(idNumber, phone, email);
        this.dateOfBirth = new Date(dateOfBirth.getTime()); // Use copy for immutability
    }

    public Date getDateOfBirth() { return new Date(dateOfBirth.getTime()); } // Return copy for immutability
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = new Date(dateOfBirth.getTime()); }

    @Override
    public void openAccount() {
        System.out.println("Personal account opened for " + getIdNumber() + ".");
    }

    @Override
    public double viewBalance(AccountController accountController, String accountNumber) {
        if (accountController != null && accountNumber != null) {
            return accountController.getBalance(accountNumber);
        }
        return 0.0; // Return 0 if accountController or accountNumber is invalid
    }
}