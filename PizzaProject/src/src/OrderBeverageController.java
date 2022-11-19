package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderBeverageController {
    @FXML
    Button backButton;
    @FXML
    Button homeButton;
    @FXML
    Button continueButton;
    @FXML
    private ToggleGroup PepsiGroup, MtnGroup, DietpGroup, AquafinaGroup, SierraGroup;
    @FXML
    private RadioButton PepsiSmallButton;
    @FXML
    private RadioButton PepsiMediumButton;
    @FXML
    private RadioButton PepsiLargeButton;
    @FXML
    private RadioButton MtnSmallButton;
    @FXML
    private RadioButton MtnMediumButton;
    @FXML
    private RadioButton MtnLargeButton;
    @FXML
    private RadioButton DietSmallButton;
    @FXML
    private RadioButton DietMediumButton;
    @FXML
    private RadioButton DietLargeButton;
    @FXML
    private RadioButton AquaSmallButton;
    @FXML
    private RadioButton AquaMediumButton;
    @FXML
    private RadioButton AquaLargeButton;
    @FXML
    private RadioButton SierraSmallButton;
    @FXML
    private RadioButton SierraMediumButton;
    @FXML
    private RadioButton SierraLargeButton;
    @FXML
    private Button addPepsiButton;
    @FXML
    private Button addMtnButton;
    @FXML
    private Button addDietButton;
    @FXML
    private Button addAquaButton;
    @FXML
    private Button addSierraButton;
    @FXML
    private TextArea taInformation;

    private static double beveragePrice = 0.0;

    public void handleBackButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderPizza.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleContinueButton() throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("ItemCartPage.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }
    public void handleAddPepsiButtonAction(javafx.event.ActionEvent actionEvent) {
        double price = 0.0;
        Beverage beverage = new Beverage();
        if (PepsiSmallButton.isSelected()) {
            beverage.setSize("12oz.");
            beverage.setName("Pepsi-cola");
            beverage.setPrice(2);
            taInformation.appendText("A 12oz. Pepsi has been added to the cart.\n");
        }else if (PepsiMediumButton.isSelected()) {
            beverage.setSize("20oz.");
            beverage.setName("Pepsi-cola");
            beverage.setPrice(3);
            taInformation.appendText("A 20oz. Pepsi has been added to the cart.\n");
        }else if (PepsiLargeButton.isSelected()) {
            beverage.setSize("2-liter");
            beverage.setName("Pepsi-cola");
            beverage.setPrice(6);
            taInformation.appendText("A 2-liter Pepsi has been added to the cart.\n");
        }else{
            taInformation.appendText("PLEASE SELECT SIZE\n");
            return;
        }
        beveragePrice += beverage.getPrice();
        writeRecord(beverage);
        writePriceRecord(beverage);
    }

    public void handleAddMtnButtonAction(javafx.event.ActionEvent actionEvent) {
        double price = 0.0;
        Beverage beverage = new Beverage();
        if (MtnSmallButton.isSelected()) {
            beverage.setSize("12oz.");
            beverage.setName("Mtn Dew");
            beverage.setPrice(2);
            taInformation.appendText("A 12oz. Mtn Dew has been added to the cart.\n");
        }else if (MtnMediumButton.isSelected()) {
            beverage.setSize("20oz.");
            beverage.setName("Mtn Dew");
            beverage.setPrice(3);
            taInformation.appendText("A 20oz. Mtn Dew has been added to the cart.\n");
        }else if (MtnLargeButton.isSelected()) {
            beverage.setSize("2-liter");
            beverage.setName("Mtn Dew");
            beverage.setPrice(6);
            taInformation.appendText("A 2-liter Mtn Dew has been added to the cart.\n");
        }else{
            taInformation.appendText("PLEASE SELECT SIZE\n");
            return;
        }
        beveragePrice += beverage.getPrice();
        writeRecord(beverage);
        writePriceRecord(beverage);
    }

    public void handleAddDietButtonAction(javafx.event.ActionEvent actionEvent) {
        double price = 0.0;
        Beverage beverage = new Beverage();
        if (DietSmallButton.isSelected()) {
            beverage.setSize("12oz.");
            beverage.setName("Diet Pepsi");
            beverage.setPrice(2);
            taInformation.appendText("A 12oz. Diet Pepsi has been added to the cart.\n");
        }else if (DietMediumButton.isSelected()) {
            beverage.setSize("20oz.");
            beverage.setName("Diet Pepsi");
            beverage.setPrice(3);
            taInformation.appendText("A 20oz. Diet Pepsi has been added to the cart.\n");
        }else if (DietLargeButton.isSelected()) {
            beverage.setSize("2-liter");
            beverage.setName("Diet Pepsi");
            beverage.setPrice(6);
            taInformation.appendText("A 2-liter Diet Pepsi has been added to the cart.\n");
        }else{
            taInformation.appendText("PLEASE SELECT SIZE\n");
            return;
        }
        beveragePrice += beverage.getPrice();
        writeRecord(beverage);
        writePriceRecord(beverage);
    }

    public void handleAddAquaButtonAction(javafx.event.ActionEvent actionEvent) {
        double price = 0.0;
        Beverage beverage = new Beverage();
        if (AquaSmallButton.isSelected()) {
            beverage.setSize("12oz.");
            beverage.setName("Aquafina");
            beverage.setPrice(2);
            taInformation.appendText("A 12oz. Aquafina has been added to the cart.\n");
        }else if (AquaMediumButton.isSelected()) {
            beverage.setSize("20oz.");
            beverage.setName("Aquafina");
            beverage.setPrice(3);
            taInformation.appendText("A 20oz. Aquafina has been added to the cart.\n");
        }else if (AquaLargeButton.isSelected()) {
            beverage.setSize("2-liter");
            beverage.setName("Aquafina");
            beverage.setPrice(6);
            taInformation.appendText("A 2-liter Aquafina has been added to the cart.\n");
        }else{
            taInformation.appendText("PLEASE SELECT SIZE\n");
            return;
        }
        beveragePrice += beverage.getPrice();
        writeRecord(beverage);
        writePriceRecord(beverage);
    }

    public void handleAddSierraButtonAction(javafx.event.ActionEvent actionEvent) {
        double price = 0.0;
        Beverage beverage = new Beverage();
        if (SierraSmallButton.isSelected()) {
            beverage.setSize("12oz.");
            beverage.setName("Sierra Mist");
            beverage.setPrice(2);
            taInformation.appendText("A 12oz. Sierra Mist has been added to the cart.\n");
        }else if (SierraMediumButton.isSelected()) {
            beverage.setSize("20oz.");
            beverage.setName("Sierra Mist");
            beverage.setPrice(3);
            taInformation.appendText("A 20oz. Sierra Mist has been added to the cart.\n");
        }else if (SierraLargeButton.isSelected()) {
            beverage.setSize("2-liter");
            beverage.setName("Sierra Mist");
            beverage.setPrice(6);
            taInformation.appendText("A 2-liter Sierra Mist has been added to the cart.\n");
        }else{
            taInformation.appendText("PLEASE SELECT SIZE\n");
            return;
        }
        beveragePrice += beverage.getPrice();
        writeRecord(beverage);
        writePriceRecord(beverage);
    }

    public void writeRecord(Beverage beverage){
        FileWriter file = null;
        PrintWriter output = null;
        try{
            file = new FileWriter("sale.txt", true);

            output = new PrintWriter(file);

            output.println("1 x " + beverage.getSize() + " " + beverage.getName() + "   $" + beverage.getPrice());

            output.checkError();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(output != null){
                output.close();
            }
        }
    }

    public void writePriceRecord(Beverage beverage){
        FileWriter file = null;
        PrintWriter output = null;
        try{
            file = new FileWriter("calPrice.txt", true);

            output = new PrintWriter(file);

            output.println(beverage.getPrice());

            output.checkError();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(output != null){
                output.close();
            }
        }
    }
}
