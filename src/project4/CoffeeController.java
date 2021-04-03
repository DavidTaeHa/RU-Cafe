package project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
        quantity.getSelectionModel().select(NONE);
        coffee = new Coffee(NONE, INVALID);
        coffee.setQuantity(quantity.getValue().intValue());
    }

    /**
     * Adds the coffee to the cart
     *
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event) {
        controller.addItems(coffee);
        Coffee temp = coffee;
        showMessage("Coffee has been added to the cart.");
        coffee = new Coffee(quantity.getValue().intValue(), temp.getSize());
        coffee.calculateItemPrice();
    }

    /**
     * Event listener for whenever the quantity is changed; Changes subtotal accordingly
     *
     * @param event
     */
    @FXML
    void quantityChange(ActionEvent event) {
            coffee.setQuantity(quantity.getValue().intValue());
            coffee.calculateItemPrice();
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
            coffee.add("Whipped-Cream");
        }
        else if(!whipped.isSelected()){
            coffee.remove("Whipped-Cream");
        }
        coffee.calculateItemPrice();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(coffee.getItemPrice()));
    }

    /**
     * Retrieves the size input for the coffee; also enables the add to order button
     *
     * @param event
     */
    @FXML
    void sizeAction (ActionEvent event){
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
        addCartButton.setDisable(false);
    }

    /**
     * Helper method to aid in creating a message box
     *
     * @param message text to be said within the error box
     */
    private void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

}
