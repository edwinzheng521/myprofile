package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class paymentPageController implements Initializable {
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button cancelOrderButton;
    @FXML
    private Button backButton;
    @FXML
    private TextArea subTotalArea;
    @FXML
    private TextArea taxArea;
    @FXML
    private TextArea deliveryArea;
    @FXML
    private TextArea totalArea;
    public void initialize(URL location, ResourceBundle resources){

    }

    public void handleBackButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ItemCartPage.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }


    public void handlePlaceButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderProcessedPage.fxml"));
        Stage window = (Stage) placeOrderButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handlecancelButton() throws IOException {
        File file = new File("sale.txt");
        if(file.exists())
            file.delete();
        try{
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        file = new File("calPrice.txt");
        if(file.exists())
            file.delete();
        try{
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Stage window = (Stage) cancelOrderButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }
}
