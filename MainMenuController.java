import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private void goCustomer() {
        try {
            Stage st = new Stage();
            st.setScene(new Scene(FXMLLoader.load(getClass().getResource("CustomerLoginView.fxml"))));
            st.setTitle("Customer Login");
            st.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void goEmployee() {
        try {
            Stage st = new Stage();
            st.setScene(new Scene(FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"))));
            st.setTitle("Employee Login");
            st.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}