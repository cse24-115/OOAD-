public abstract class Customer {
    protected String idNumber;
    protected String phone;
    protected String email;
    protected boolean login;
    protected Login loginInstance;

    public Customer(String id, String phone, String email) {
        this.idNumber = id;
        this.phone = phone;
        this.email = email;
        login = false;

        this.loginInstance = new Login(idNumber, "", this);
    }

    public String getIdNumber() { return idNumber; }
    public String getPhone()    { return phone; }
    public String getEmail()    { return email; }
    public boolean isLogin()    { return login; }
    protected void setLogin(boolean b) { login = b; }
    public Login getLoginInstance()    { return loginInstance; }

    public abstract void openAccount();
    public abstract double viewBalance(String acc);

    public boolean depositFunds(Account acc, double amt) {
        if (isLogin() && acc != null && amt > 0) {
            acc.deposit(amt);
            return true;
        }
        return false;
    }

    public boolean withdrawFunds(Account acc, double amt) {
        if (isLogin() && acc != null && amt > 0)
            return acc.withdraw(amt);
        return false;
    }
}