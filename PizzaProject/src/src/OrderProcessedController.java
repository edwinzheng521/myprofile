package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class OrderProcessedController {

    @FXML
    private Button homepageButton;
    @FXML
    private Button createNewButton;
    @FXML
    private Button printReceiptButton;

    public void handleHomeButton() throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) homepageButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleCreateButton() throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource("OrderPizza.fxml"));
        Stage window = (Stage) createNewButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void printReceiptButton() throws IOException {

    }
}
