public class Login {
    private String username;
    private String password;
    private Customer customer;

    public Login(String u, String p, Customer c) {
        username = u;
        password = p;
        customer = c;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Customer getCustomer() { return customer; }

    public boolean authenticate(String u, String p) {
        if (username.equals(u) && password.equals(p)) {
            customer.setLogin(true);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Login[username=" + username + ", customer=" + customer.getIdNumber() + "]";
    }
}