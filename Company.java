public class Company extends Customer {
    private String companyName;
    private String registrationNumber;

    public Company(String id, String phone, String email,
                   String companyName, String regNo) {
        super(id, phone, email);
        this.companyName = companyName;
        this.registrationNumber = regNo;
    }

    public String getCompanyName()       { return companyName; }
    public String getRegistrationNumber(){ return registrationNumber; }

    public void openAccount() {
        System.out.println("Company account opened for " + companyName);
    }

    public double viewBalance(String acc) {
        System.out.println("Viewing balance for company account " + acc);
        return 0.0;
    }
}