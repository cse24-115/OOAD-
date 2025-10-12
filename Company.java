public class Company extends Customer {
    private String companyName;
    private String registrationNumber;

    public Company(String idNumber, String phone, String email, String companyName, String registrationNumber) {
        super(idNumber, phone, email);
        this.companyName = companyName;
        this.registrationNumber = registrationNumber;
    }

    public String getCompanyName() { return companyName; }
    public String getRegistrationNumber() { return registrationNumber; }

    public void openAccount() {
        System.out.println("Company account opened for " + companyName);
    }

    public double viewBalance(AccountController accountController, String accountNumber) {
        return accountController.getBalance(accountNumber);
    }
}