public class Controller {
    private Customer customer;

    public Controller(Customer customer) {
        this.customer = customer;
    }

    public boolean isLoggedIn() {
        return customer != null && customer.isLogin();
    }

    public void displayCustomerInfo() {
        if (customer != null) {
            System.out.println("Customer ID: " + customer.getIdNumber() + ", Phone: " + customer.getPhone() + ", Email: " + customer.getEmail());
        } else {
            System.out.println("No customer information available.");
        }
    }
}