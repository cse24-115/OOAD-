import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeePortalController {

    @FXML private TextField tfId, tfPhone, tfEmail;
    @FXML private PasswordField tfPw;
    @FXML private TextArea taLog;

    @FXML private TableView<Customer> tblCustomers;
    @FXML private TableColumn<Customer, String> colId;
    @FXML private TableColumn<Customer, String> colPhone;
    @FXML private TableColumn<Customer, String> colEmail;

    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdNumber()));
        colPhone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhone()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        refreshCustomers();
    }

    @FXML
    private void registerCustomer() {
        String id = tfId.getText().trim();
        String phone = tfPhone.getText().trim();
        String email = tfEmail.getText().trim();
        String pw = tfPw.getText().trim();

        if (id.isEmpty() || phone.isEmpty() || email.isEmpty() || pw.isEmpty()) {
            log("⚠️ All fields are required.");
            return;
        }

        if (Registry.find(id) != null) {
            log("❌ Customer with ID '" + id + "' already exists.");
            return;
        }

        Customer c = new Customer(id, phone, email, pw);
        Registry.save(id, c);

        log("✅ Registered customer " + id);

        clearFields();
        refreshCustomers();
    }

    @FXML
    private void refreshCustomers() {
        customerList = FXCollections.observableArrayList(Registry.getAllCustomers());
        tblCustomers.setItems(customerList);
    }

    private void clearFields() {
        tfId.clear();
        tfPhone.clear();
        tfEmail.clear();
        tfPw.clear();
    }

    private void log(String msg) {
        taLog.appendText(msg + "\n");
    }
}
