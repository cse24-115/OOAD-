import java.util.Date;

public class Individual extends Customer {
    private Date dateOfBirth;

    public Individual(String id, String phone, String email, Date dob) {
        super(id, phone, email);
        dateOfBirth = new Date(dob.getTime());
        Registry.save(id, this);   // auto-register for simplicity
    }

    public Date getDateOfBirth() { return new Date(dateOfBirth.getTime()); }

    public void openAccount() {
        System.out.println("Personal account opened for " + getIdNumber());
    }

    public double viewBalance(String acc) {
        System.out.println("Viewing balance for account " + acc);
        return 0.0;
    }
}