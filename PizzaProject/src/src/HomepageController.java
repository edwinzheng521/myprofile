package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HomepageController {

    @FXML
    Button logoutButton;

    @FXML
    Button orderButton;

    public void handlelogoutButton() throws IOException {
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


        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleorderButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderPizza.fxml"));
        Stage window = (Stage) orderButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

}
