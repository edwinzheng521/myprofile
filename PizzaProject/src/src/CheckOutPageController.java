package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckOutPageController implements Initializable {
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button cancelOrderButton;
    @FXML
    private TextArea subTotalArea;
    @FXML
    private TextArea taxArea;
    @FXML
    private TextArea deliveryArea;
    @FXML
    private TextArea totalArea;
    public void initialize(URL location, ResourceBundle resources){
        subTotalArea.setText("sad");

    }

    public void handlePlaceButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderProcessedPage.fxml"));
        Stage window = (Stage) placeOrderButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleCancelButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) cancelOrderButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }
}
