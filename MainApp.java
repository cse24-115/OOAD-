import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
        stage.setTitle("Gotlhelelele Banking");
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}