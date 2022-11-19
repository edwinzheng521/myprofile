package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderPizzaController {

    @FXML
    private Button continueButton, backButton, homeButton;
    @FXML
    private Button addToCartButton;
    @FXML
    private TextArea taInformation;
    @FXML
    private RadioButton sizeSmallButton, sizeMediumButton, sizeLargeButton, sizeExtraLargeButton;
    @FXML
    private RadioButton crustClassicButton, crustThinButton, crustDeepdishButton, crustHandButton;
    @FXML
    private ToggleGroup pizzaSizeGroup;
    @FXML
    private ToggleGroup pizzaCrustGroup;
    @FXML
    private ImageView image;
    @FXML
    private CheckBox pepperoniCheckBox, sausageCheckBox, tomatoCheckBox, pineappleCheckBox;
    @FXML
    private CheckBox greenPepperCheckBox, mushroomCheckBox, hamCheckBox, onionCheckBox;



    private static double pizzaPrice = 0.0;

    public void handleContinueButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderBeverage.fxml"));
        Stage window = (Stage) continueButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleBackButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleHomeButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(root,700,400));
    }

    public void handleAddButtonAction(javafx.event.ActionEvent actionEvent) {
        int selectedToppings = 0;
        double price = 0.0;
        Pizza pizza = new Pizza();
        String sizeAndName  ="";
        String topping = "";

        if (sizeSmallButton.isSelected()) {
            price = 2;
            pizza.setSize("Small");
            sizeAndName += "Small";
            taInformation.appendText("A " + sizeSmallButton.getText() + " ");
        }
        if (sizeMediumButton.isSelected()) {
            price = 3;
            pizza.setSize("Medium");
            sizeAndName += "Medium";
            taInformation.appendText("A " + sizeMediumButton.getText() + " ");
        }
        if (sizeLargeButton.isSelected()) {
            price = 4;
            pizza.setSize("Large");
            sizeAndName += "Large";
            taInformation.appendText("A " + sizeLargeButton.getText() + " ");
        }
        if (sizeExtraLargeButton.isSelected()) {
            price = 5;
            pizza.setSize("Extra Large");
            sizeAndName += "Extra Large";
            taInformation.appendText("An " + sizeExtraLargeButton.getText() + " ");
        }
        if (crustClassicButton.isSelected()) {
            pizza.setName("Classic");
            sizeAndName += " Classic";
            taInformation.appendText("Classic Pizza with topping\n");
        }
        if (crustThinButton.isSelected()) {
            pizza.setName("Thin");
            sizeAndName += " Thin";
            taInformation.appendText("Thin Pizza with topping\n");
        }
        if (crustDeepdishButton.isSelected()) {
            pizza.setName("Deep dish");
            sizeAndName += " Deep Dish";
            taInformation.appendText("Deep dish Pizza with topping\n");
        }
        if (crustHandButton.isSelected()) {
            pizza.setName("Hand-Tossed");
            sizeAndName += " Hand-Tossed";
            taInformation.appendText("Hand-Tossed Pizza with topping\n");
        }


        if (pepperoniCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - Pepperoni");
            topping += "   - Pepperoni\n";
            taInformation.appendText("  - Pepperoni\n");
        }
        if (mushroomCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - mushroom");
            topping += "   - Mushroon\n";
            taInformation.appendText("  - Mushroom\n");
        }
        if (tomatoCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - tomato");
            topping += "   - Tomato\n";
            taInformation.appendText("  - Tomato\n");
        }
        if (pineappleCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - pineapple");
            topping += "   - Pineapple\n";
            taInformation.appendText("  - Pineapple\n");
        }
        if (sausageCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - sausage");
            topping += "   - Sauage\n";
            taInformation.appendText("  - Sauage\n");
        }
        if (hamCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - ham");
            topping += "   - Ham\n";
            taInformation.appendText("  - Ham\n");
        }
        if (onionCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - onion");
            topping += "   - Onion\n";
            taInformation.appendText("  - Onion\n");
        }
        if (greenPepperCheckBox.isSelected()) {
            selectedToppings++;
            price += 0.5;
            pizza.setName(pizza.getName() + "\n  - greenpepper");
            topping += "   - Greenpepper\n";
            taInformation.appendText("  - GreenPepper\n");
        }
        if(selectedToppings == 0){
            taInformation.setText("PLEASE SELECT AT LEAST ONE TOPPING\n");
            return;
        }
        pizzaPrice += price;
        pizza.setPrice(price);
        pizza.setName( sizeAndName + ".........." + "$" + pizza.getPrice() + "\n" + topping);
        writeRecord(pizza);
        writePriceRecord(pizza);
        taInformation.appendText("has been added to Cart\n");
        taInformation.appendText("__________________________________________\n");
    }

    public void writeRecord(Pizza pizza){
        FileWriter file = null;
        PrintWriter output = null;
        try{
            file = new FileWriter("sale.txt", true);

            output = new PrintWriter(file);

            output.println("1 x " + pizza.getName());

            output.checkError();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(output != null){
                output.close();
            }
        }
    }

    public void writePriceRecord(Pizza pizza){
        FileWriter file = null;
        PrintWriter output = null;
        try{
            file = new FileWriter("calPrice.txt", true);

            output = new PrintWriter(file);

            output.println(pizza.getPrice());

            output.checkError();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(output != null){
                output.close();
            }
        }
    }



    //haven't finish yet
    public void upTo4Topping(javafx.event.ActionEvent actionEvent) {
        int selectedToppings = 0;
        if (pepperoniCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (mushroomCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (tomatoCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (pineappleCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (sausageCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (hamCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (onionCheckBox.isSelected()) {
            selectedToppings++;
        }
        if (greenPepperCheckBox.isSelected()) {
            selectedToppings++;
        }
        taInformation.appendText("SelectedToppings: " + selectedToppings + "\n");
        if(selectedToppings == 4){
            pepperoniCheckBox.setDisable(!pepperoniCheckBox.isSelected());
            mushroomCheckBox.setDisable(!mushroomCheckBox.isSelected());
            tomatoCheckBox.setDisable(!tomatoCheckBox.isSelected());
            pineappleCheckBox.setDisable(!pineappleCheckBox.isSelected());
            sausageCheckBox.setDisable(!sausageCheckBox.isSelected());
            hamCheckBox.setDisable(!hamCheckBox.isSelected());
            onionCheckBox.setDisable(!onionCheckBox.isSelected());
            greenPepperCheckBox.setDisable(!greenPepperCheckBox.isSelected());
        }else{
            pepperoniCheckBox.setDisable(false);
            mushroomCheckBox.setDisable(false);
            tomatoCheckBox.setDisable(false);
            pineappleCheckBox.setDisable(false);
            sausageCheckBox.setDisable(false);
            hamCheckBox.setDisable(false);
            onionCheckBox.setDisable(false);
            greenPepperCheckBox.setDisable(false);
        }

            /*

          if(selectedToppings == 4){
                pepperoniCheckBox.setDisable(!pepperoniCheckBox.isSelected());
                mushroomCheckBox.setDisable(!mushroomCheckBox.isSelected());
                tomatoCheckBox.setDisable(!tomatoCheckBox.isSelected());
                pepperoniCheckBox.setDisable(!pepperoniCheckBox.isSelected());
                sausageCheckBox.setDisable(!sausageCheckBox.isSelected());
                hamCheckBox.setDisable(!hamCheckBox.isSelected());
                onionCheckBox.setDisable(!onionCheckBox.isSelected());
                greenPepperCheckBox.setDisable(!greenPepperCheckBox.isSelected());
            }else{
                selectedToppings--;
                pepperoniCheckBox.setDisable(false);
                mushroomCheckBox.setDisable(false);
                tomatoCheckBox.setDisable(false);
                pepperoniCheckBox.setDisable(false);
                sausageCheckBox.setDisable(false);
                hamCheckBox.setDisable(false);
                onionCheckBox.setDisable(false);
                greenPepperCheckBox.setDisable(false);
            }
        }

        */

        }

}
