import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class EmployeePortalController {
    @FXML private TextField tfId, tfPhone, tfEmail;
    @FXML private PasswordField tfPw;
    @FXML private TextArea taLog;

    @FXML
    private void registerCustomer() {
        String id    = tfId.getText().trim();
        String phone = tfPhone.getText().trim();
        String email = tfEmail.getText().trim();
        String pw    = tfPw.getText().trim();

        if (id.isEmpty() || phone.isEmpty() || email.isEmpty() || pw.isEmpty()) {
            log("All fields required."); return;
        }

        BankEmployee emp = new BankEmployee("emp", "", "");
        Customer c = emp.registerCustomer(id, phone, email, new Date(), pw);
        log("Registered customer " + c.getIdNumber() + " with password " + pw);
    }

    private void log(String m) { taLog.appendText(m + "\n"); }
}