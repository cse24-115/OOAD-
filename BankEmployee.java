import java.util.Date;

public class BankEmployee extends Customer {
    public BankEmployee(String id, String phone, String email) {
        super(id, phone, email);
    }

    /* employees can register new customers */
    public Customer registerCustomer(String id, String phone, String email,
                                     Date dob, String password) {
        Individual newbie = new Individual(id, phone, email, dob);
        /* override the login object with the chosen password */
        newbie.loginInstance = new Login(id, password, newbie);
        Registry.save(id, newbie);          // store so they can log in later
        return newbie;
    }

    @Override
    public void openAccount() {
        System.out.println("Employee cannot open consumer accounts.");
    }
    @Override
    public double viewBalance(String acc) { return 0.0; }
}