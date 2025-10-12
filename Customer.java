public abstract class Customer {
    protected String idNumber;
    protected String phone;
    protected String email;
    protected boolean login;
    protected Login loginInstance;

    public Customer(String idNumber, String phone, String email) {
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
        this.login = false;
        this.loginInstance = new Login(idNumber, "defaultpassword", this);
    }

    public String getIdNumber() { return idNumber; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public boolean isLogin() { return login; }
    protected void setLogin(boolean login) { this.login = login; }
    public Login getLoginInstance() { return loginInstance; }

    public abstract void openAccount();
    public abstract double viewBalance(AccountController accountController, String accountNumber);

    public boolean depositFunds(AccountController accountController, String accountNumber, double amount) {
        if (isLogin() && amount > 0) {
            accountController.deposit(accountNumber, amount);
            return true;
        }
        return false;
    }

    public boolean withdrawFunds(AccountController accountController, String accountNumber, double amount) {
        if (isLogin() && amount > 0) {
            return accountController.withdraw(accountNumber, amount);
        }
        return false;
    }
} 