public class Customer {
    protected String idNumber;
    protected String phone;
    protected String email;
    protected boolean login;
    protected Login loginInstance;

    // Constructor with password
    public Customer(String id, String phone, String email, String pw) {
        this.idNumber = id;
        this.phone = phone;
        this.email = email;
        this.login = false;
        this.loginInstance = new Login(id, pw, this);
    }

    // Optional: default constructor
    public Customer(String id, String phone, String email) {
        this(id, phone, email, "");
    }

    public String getIdNumber() { return idNumber; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public boolean isLogin() { return login; }
    protected void setLogin(boolean b) { login = b; }
    public Login getLoginInstance() { return loginInstance; }

    public void openAccount() { /* implement */ }
    public double viewBalance(String acc) { return 0; }
    public boolean depositFunds(Account acc, double amt) {
        if (isLogin() && acc != null && amt > 0) { acc.deposit(amt); return true; }
        return false;
    }
    public boolean withdrawFunds(Account acc, double amt) {
        if (isLogin() && acc != null && amt > 0) return acc.withdraw(amt);
        return false;
    }
}
