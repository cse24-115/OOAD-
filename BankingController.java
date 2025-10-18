import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class BankingController {

    /* ---------- FXML controls ---------- */
    @FXML private ComboBox<String> cbType;
    @FXML private TextField tfBranch, tfAmount;
    @FXML private Label lblBalance;
    @FXML private TextArea taLog;
    @FXML private Button btnOpen, btnDeposit, btnWithdraw, btnInterest;

    /* ---------- state ---------- */
    private Customer customer;
    private Account account;

    /* ---------- initialization ---------- */
    @FXML
    private void initialize() {
        cbType.getItems().addAll("CHEQUE", "INVESTMENT");
        cbType.getSelectionModel().selectFirst();
        setAccountMode(false);   // no account yet → disable action buttons
    }

    /* ---------- called by CustomerLoginController ---------- */
    public void setCustomer(Customer c) {
        this.customer = c;
        log("Customer " + c.getIdNumber() + " logged in.");
    }

    /* ---------- open account ---------- */
    @FXML
    private void handleOpen() {
        if (customer == null) { log("No customer logged in."); return; }

        String branch = tfBranch.getText().trim();
        if (branch.isEmpty()) { log("Enter branch."); return; }

        String accNo = "ACC-" + System.currentTimeMillis();
        String type  = cbType.getValue();

        if ("CHEQUE".equals(type)) {
            account = new ChequeAccount(accNo, 0, branch, new Date(),
                    "Gotlhelelele Bank", branch);
        } else {
            account = new InvestmentAccount(accNo, 0, branch, new Date(), 0.05);
        }

        customer.openAccount();
        log("Opened " + type + " account " + accNo);
        setAccountMode(true);
        updateBalance();
    }

    /* ---------- deposit ---------- */
    @FXML
    private void handleDeposit() {
        double amt = readAmount();
        if (amt <= 0) return;
        boolean ok = customer.depositFunds(account, amt);
        log(ok ? "Deposited P" + amt : "Deposit failed.");
        updateBalance();
    }

    /* ---------- withdraw ---------- */
    @FXML
    private void handleWithdraw() {
        double amt = readAmount();
        if (amt <= 0) return;
        boolean ok = customer.withdrawFunds(account, amt);
        log(ok ? "Withdrew P" + amt : "Withdraw failed.");
        updateBalance();
    }

    /* ---------- monthly interest (Investment only) ---------- */
    @FXML
    private void handleMonthlyInterest() {
        if (account instanceof InvestmentAccount inv) {
            inv.depositMonthlyInterest();
            updateBalance();
        } else {
            log("Interest only available on Investment accounts.");
        }
    }

    /* ---------- helpers ---------- */
    private double readAmount() {
        try {
            return Double.parseDouble(tfAmount.getText().trim());
        } catch (NumberFormatException e) {
            log("Amount must be numeric.");
            return -1;
        }
    }

    private void updateBalance() {
        lblBalance.setText(String.format("Balance: P%.2f", account.getBalance()));
    }

    private void setAccountMode(boolean hasAccount) {
        btnDeposit.setDisable(!hasAccount);
        btnWithdraw.setDisable(!hasAccount);
        btnInterest.setDisable(!hasAccount);
        btnOpen.setDisable(hasAccount);   // disable re-opening
    }

    private void log(String msg) { taLog.appendText(msg + "\n"); }
}