import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomerLoginController {
    @FXML private TextField tfId;
    @FXML private PasswordField tfPw;
    @FXML private TextArea taLog;

    @FXML
    private void handleLogin() {
        String id = tfId.getText().trim();
        String pw = tfPw.getText().trim();
        Customer c = Registry.find(id);
        if (c == null) { log("Unknown customer ID."); return; }
        boolean ok = c.getLoginInstance().authenticate(id, pw);
        if (ok) {
            log("Customer logged in.");
            openBankingWindow(c);
        } else {
            log("Wrong password.");
        }
    }

    private void openBankingWindow(Customer c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Banking.fxml"));
            Stage st = new Stage();
            st.setScene(new Scene(loader.load()));
            BankingController ctrl = loader.getController();
            ctrl.setCustomer(c);   // hand the customer to the banking screen
            st.setTitle("Customer Banking");
            st.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void log(String m) { taLog.appendText(m + "\n"); }
}