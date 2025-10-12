import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Individual individual;
    private Company company;
    private AccountController accountController;
    private Scanner scanner;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.accountController = new AccountController();

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.set(1990, Calendar.JANUARY, 1, 0, 0, 0);
        Date dateOfBirth = dobCalendar.getTime();
        this.individual = new Individual("789012", "987-654-3210", "alice.smith@email.com", dateOfBirth);
        this.company = new Company("456789", "123-456-7890", "techcorp@email.com", "TechCorp", "REG123");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.OCTOBER, 11, 6, 57, 0);
        Date currentDate = calendar.getTime();

        Account individualAcc = new IndividualAccount("IND001", 1000.0, "Central Branch", currentDate);
        Account companyAcc = new CompanyAccount("COM001", 2000.0, "Central Branch", currentDate, 500.0);
        Account investmentAcc = new InvestmentAccount("INV001", 2000.0, "Central Branch", currentDate);
        Account savingsAcc = new SavingsAccount("SAV001", 500.0, "Central Branch", currentDate);
        Account cheque = new ChequeAccount("CHQ001", 2000.0, "Central Branch", currentDate, "XYZ Ltd", "456 Corporate Ave");

        accountController.addAccount(individualAcc);
        accountController.addAccount(companyAcc);
        accountController.addAccount(investmentAcc);
        accountController.addAccount(savingsAcc);
        accountController.addAccount(cheque);

        if (cheque instanceof ChequeAccount) {
            ((ChequeAccount) cheque).updateOverdraftLimit(200.0);
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Login as Individual");
            System.out.println("2. Login as Company");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 3.");
                scanner.nextLine();
                continue;
            }

            if (choice == 3) break;

            Customer customer = (choice == 1) ? individual : company;
            LoginController loginController = new LoginController(customer);
            Controller controller = new Controller(customer);

            System.out.println("\n=== Login as " + (choice == 1 ? "Individual" : "Company") + " ===");
            System.out.print("Enter username (ID Number): ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            if (loginController.login(username, password)) {
                Date lastLogin = loginController.getLastLoginTime();
                System.out.println("Last login time: " + (lastLogin != null ? lastLogin : "N/A"));

                if (controller.isLoggedIn()) {
                    System.out.println("Login verified by Controller.");
                    System.out.println("Login successful! Welcome, " + (choice == 1 ? individual.getIdNumber() : company.getCompanyName()) + ".");
                    customer.openAccount();
                    controller.displayCustomerInfo();

                    while (true) {
                        System.out.println("\n=== Account Operations ===");
                        System.out.println("1. View Balance");
                        System.out.println("2. Deposit Funds");
                        System.out.println("3. Withdraw Funds");
                        System.out.println("4. Add Monthly Interest");
                        System.out.println("5. Logout");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Choose an option (1-6): ");

                        int subChoice = 0;
                        try {
                            subChoice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number between 1 and 6.");
                            scanner.nextLine();
                            continue;
                        }

                        if (subChoice == 6) break;

                        String accountNumber = (choice == 1) ? "IND001" : (choice == 2 ? "COM001" : "CHQ001");
                        System.out.print("Enter account number (" + accountNumber + "): ");
                        accountNumber = scanner.nextLine().trim().toUpperCase();

                        double amount = 0.0;
                        switch (subChoice) {
                            case 1:
                                double balance = customer.viewBalance(accountController, accountNumber);
                                System.out.println(accountNumber + " Balance: " + balance);
                                break;
                            case 2:
                                if (controller.isLoggedIn()) {
                                    try {
                                        System.out.print("Enter amount to deposit: ");
                                        amount = scanner.nextDouble();
                                        scanner.nextLine();
                                        if (amount > 0 && customer.depositFunds(accountController, accountNumber, amount)) {
                                            System.out.println("Deposited " + amount + " to " + accountNumber + ". New balance: " + 
                                                              accountController.getBalance(accountNumber));
                                        } else {
                                            System.out.println("Deposit failed: Invalid amount or account issue.");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid amount! Please enter a positive number.");
                                        scanner.nextLine();
                                    }
                                } else {
                                    System.out.println("Please log in to deposit funds.");
                                }
                                break;
                            case 3:
                                if (controller.isLoggedIn()) {
                                    try {
                                        System.out.print("Enter amount to withdraw: ");
                                        amount = scanner.nextDouble();
                                        scanner.nextLine();
                                        Account account = accountController.getAccount(accountNumber);
                                        if (account != null && amount > 0 && account instanceof Withdraw) {
                                            if (((Withdraw) account).processWithdrawal(accountNumber, amount)) {
                                                System.out.println("Withdrawn " + amount + " from " + accountNumber + ". New balance: " + 
                                                                  accountController.getBalance(accountNumber));
                                            } else {
                                                System.out.println("Withdrawal failed: Insufficient funds, minimum balance, or account issue.");
                                            }
                                        } else {
                                            System.out.println("Account not found, invalid amount, or does not support withdrawal.");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid amount! Please enter a positive number.");
                                        scanner.nextLine();
                                    }
                                } else {
                                    System.out.println("Please log in to withdraw funds.");
                                }
                                break;
                            case 4:
                                accountController.addMonthlyInterest(accountNumber);
                                System.out.println("Monthly interest added to " + accountNumber + ". New balance: " + 
                                                  accountController.getBalance(accountNumber));
                                break;
                            case 5:
                                loginController.logout();
                                System.out.println("Logged out successfully.");
                                break;
                            default:
                                System.out.println("Invalid option! Please select a number between 1 and 6.");
                        }
                    }
                } else {
                    System.out.println("Login verification failed via Controller!");
                }
            } else {
                System.out.println((choice == 1 ? "Individual" : "Company") + " login failed!");
            }
        }
        scanner.close();
        System.out.println("Thank you for using the banking system.");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
