public class Login {
    private String username;
    private String password;
    private Customer customebr;

    public Login(String username, String password, Customer customer) {
        this.username = username.toLowerCase();
        this.password = password.toLowerCase();
        this.customer = customer;
    }

    public boolean authenticate(String username, String password) {
        if (username == null || password == null) return false;
        return this.username.equals(username.toLowerCase()) && this.password.equals(password.toLowerCase());
    }
}