package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ItemCartPageController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextArea taInformation;
    @FXML
    private Button continueButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label estimatedTaxLabel;
    @FXML
    private Label deliveryLabel;
    @FXML
    private Label totalLabel;

    public void handleBackButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderBeverage.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handlecontinueButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("paymentPagr.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
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
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }



    public void initialize(URL location, ResourceBundle resources){
        readRecord();
       // int random = ThreadLocalRandom.current().nextInt(3,30);
        subtotalLabel.setText(readPriceRecord());
        double estimatedTax = Double.parseDouble(subtotalLabel.getText()) * 0.06;
        String estiTaxString = String.format("%.2f", estimatedTax);
        double total = Double.parseDouble(subtotalLabel.getText()) + estimatedTax + 5;
        estimatedTaxLabel.setText((estiTaxString));
        deliveryLabel.setText("5.00");
        totalLabel.setText(String.format("%.2f",total));

    }

    public void readRecord(){
        File file = null;
        Scanner input = null;

        try{
            file = new File("sale.txt");
            input = new Scanner(file);

            while(input.hasNextLine()){
                String str = input.nextLine();
                taInformation.appendText(str + '\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(input != null){
                input.close();
            }
        }
    }

    public String readPriceRecord(){
        File file = null;
        Scanner input = null;
        double price = 0.0;
        try{
            file = new File("calPrice.txt");
            input = new Scanner(file);
            while(input.hasNextLine()){
                price += Double.parseDouble(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(input != null){
                input.close();
            }
        }
        return "" + price;
    }

}
