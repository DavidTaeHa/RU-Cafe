package project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

/**
 * Class that will handle all the user input and output made in the Coffee menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class CoffeeController implements Initializable {

    @FXML
    private ComboBox<Integer> quantity;

    @FXML
    private TextField subtotal;

    @FXML
    private Button addCartButton;

    @FXML
    private ComboBox<String> size;

    @FXML
    private CheckBox cream, syrup, milk, caramel, whipped;

    private MainMenuController controller;

    final static int SHORT = 1;
    final static int TALL = 2;
    final static int GRANDE = 3;
    final static int VENTI = 4;
    final static int NONE = 0;
    final static int INVALID = -1;
    final static int ONE = 1;
    final static int TWO = 2;
    final static int THREE = 3;
    final static int FOUR = 4;
    final static int FIVE = 5;

    private Coffee coffee;

    /**
     * Gets reference of the main controller to share data betweeen the Donut Controller and
     * the main controller
     *
     * @param controller main controller
     */
    public void setMainMenuController(MainMenuController controller){
        this.controller = controller;
    }

    /**
     * This method runs when instance of the donut order menu is opened
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        size.setItems(FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti"));
        quantity.setItems(FXCollections.observableArrayList(ONE, TWO, THREE, FOUR, FIVE));
        coffee = new Coffee(NONE, INVALID);
    }

    /**
     * Adds the coffee to the cart
     *
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event) {
        coffee.calculateItemPrice();
        controller.addItems(coffee);
        showAlert("Coffee has been added to the cart.");
        cream.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        caramel.setSelected(false);
        whipped.setSelected(false);
    }

    /**
     * Enables the add button when sufficient information is available
     *
     * @param event
     */
    @FXML
    void enableAdd(KeyEvent event) {
            addCartButton.setDisable(false);
            coffee.setQuantity(quantity.getValue().intValue());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            subtotal.setText(formatter.format(coffee.getItemPrice()));
    }

    /**
     * Adds or removes addins for the coffee
     *
     * @param event
     */
    @FXML
    void addins(ActionEvent event){
        if(cream.isSelected()){
            coffee.add("Cream");
        }
        else if(!cream.isSelected()){
            coffee.remove("Cream");
        }
        if(syrup.isSelected()){
            coffee.add("Syrup");
        }
        else if(!syrup.isSelected()){
            coffee.remove("Syrup");
        }
        if(milk.isSelected()){
            coffee.add("Milk");
        }
        else if(!milk.isSelected()){
            coffee.remove("Milk");
        }
        if(caramel.isSelected()){
            coffee.add("Caramel");
        }
        else if(!caramel.isSelected()){
            coffee.remove("Caramel");
        }
        if(whipped.isSelected()){
            coffee.add("Whipped");
        }
        else if(!whipped.isSelected()){
            coffee.remove("Whipped");
        }
        coffee.calculateItemPrice();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(coffee.getItemPrice()));
    }

    /**
     * Enables the quantity text area once the size of the coffee is given
     *
     * @param event
     */
    @FXML
    void enableQuantity(ActionEvent event){
        switch (size.getValue()){
            case "Short":
                coffee.setSize(SHORT);
                break;
            case "Tall":
                coffee.setSize(TALL);
                break;
            case "Grande":
                coffee.setSize(GRANDE);
                break;
            case "Venti":
                coffee.setSize(VENTI);
                break;
        }
        coffee.calculateItemPrice();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(coffee.getItemPrice()));
        quantity.setDisable(false);
    }

    /**
     * Helper method to aid in creating an error box
     *
     * @param message text to be said within the error box
     */
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

}
