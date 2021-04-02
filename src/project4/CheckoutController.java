package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.text.NumberFormat;

/**
 * Class that will handle all the user input and output made in the checkout menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class CheckoutController {
    @FXML
    private ListView<MenuItem> cart;

    @FXML
    private TextField subtotal, tax, total;

    @FXML
    private Button removeButton, orderButton;

    private MainMenuController controller;
    private Order order;

    /**
     * Gets reference of the main controller to share data betweeen the Donut Controller and
     * the main controller
     *
     * @param controller main controller
     */
    public void setMainMenuController(MainMenuController controller){
        this.controller = controller;
        this.order = controller.getOrder();
        cart.setItems(order.getItems());
        order.calculateTax();
        order.calculateTotal();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.appendText(formatter.format(order.getSubtotal()));
        tax.appendText(formatter.format(order.getTaxTotal()));
        total.appendText(formatter.format(order.getTotal()));
    }

    @FXML
    void placeOrder(ActionEvent event) {

    }

    /**
     * Removes items from the cart
     *
     * @param event
     */
    @FXML
    void removeFromCart(ActionEvent event) {
        if(cart.getSelectionModel().getSelectedItem() == null){
            showAlert("Please select an item to remove");
            return;
        }
        order.remove(cart.getSelectionModel().getSelectedItem());
        subtotal.clear();
        tax.clear();
        total.clear();
        order.calculateTax();
        order.calculateTotal();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.appendText(formatter.format(order.getSubtotal()));
        tax.appendText(formatter.format(order.getTaxTotal()));
        total.appendText(formatter.format(order.getTotal()));
        if(order.getItems().isEmpty()){
            removeButton.setDisable(true);
        }
    }

    /**
     * Helper method to aid in creating an error box
     *
     * @param message text to be said within the error box
     */
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

}
