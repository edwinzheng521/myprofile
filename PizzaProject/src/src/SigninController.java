package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SigninController{

    @FXML
    Button submitButton;

    public void handlesubmitButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) submitButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

}
