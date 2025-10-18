import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EmployeeLoginController {
    @FXML private TextField tfId;
    @FXML private PasswordField tfPw;
    @FXML private TextArea taLog;

    /* hard-coded employee credentials */
    private static final String EMP_ID = "emp001";
    private static final String EMP_PW = "Tshiamo445";

    @FXML
    private void handleLogin() {
        String id = tfId.getText().trim();
        String pw = tfPw.getText().trim();
        if (EMP_ID.equals(id) && EMP_PW.equals(pw)) {
            log("Employee logged in.");
            openEmployeePortal();
        } else {
            log("Invalid employee credentials.");
        }
    }

    private void openEmployeePortal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeePortal.fxml"));
            Stage st = new Stage();
            st.setScene(new Scene(loader.load()));
            st.setTitle("Employee Portal");
            st.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void log(String m) { taLog.appendText(m + "\n"); }
}

