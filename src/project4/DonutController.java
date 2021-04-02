package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import java.text.NumberFormat;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that will handle all the user input and output made in the Donut Menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class DonutController implements Initializable {
    @FXML
    private ListView<Donut> orderList;

    @FXML
    private ComboBox<String> donutType, donutFlavor;

    @FXML
    private TextArea quantity, subtotal;

    @FXML
    private Button addOrderButton, addCartButton;

    private MainMenuController controller;
    private DonutOrder order;
    final static int MIN_DONUT = 1;
    final static int YEAST_DONUT = 1;
    final static int CAKE_DONUT = 2;
    final static int DONUT_HOLE = 3;

    /**
     * This method runs when instance of the donut order menu is opened
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        order = new DonutOrder();
        donutType.setItems(order.getDonutType());
        orderList.setItems(order.getDonutOrder());
    }

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
     * Adds items from the donut order list to the main cart
     *
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event){
        controller.addItems(order);
        controller.getOrder().setSubtotal(controller.getOrder().getSubtotal() + order.getDonutSubtotal());
        showMessage("Items have been added to the cart.");
        order.reset();
        order.getDonutOrder().clear();
        subtotal.clear();
        orderList.refresh();
        addCartButton.setDisable(true);
    }

    /**
     * Adds donuts to the donut order list
     *
     * @param event
     */
    @FXML
    void addToOrder(ActionEvent event) {
        try{
            int number = Integer.parseInt(quantity.getText());
            if(number < MIN_DONUT){
                throw new NumberFormatException("Error");
            }
            Donut donut = null;
            switch(donutType.getValue()){
                case "Yeast":
                    donut = new Donut (donutFlavor.getValue(), Integer.parseInt(quantity.getText()),
                            YEAST_DONUT);
                    break;
                case "Cake":
                    donut = new Donut (donutFlavor.getValue(), Integer.parseInt(quantity.getText()),
                            CAKE_DONUT);
                    break;
                case "Hole":
                    donut = new Donut (donutFlavor.getValue(), Integer.parseInt(quantity.getText()),
                            DONUT_HOLE);
                    break;
            }
            order.add(donut);
            orderList.refresh();
            subtotal.clear();
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            subtotal.appendText(formatter.format(order.getDonutSubtotal()));
            if(!order.getDonutOrder().isEmpty()){
                addCartButton.setDisable(false);
            }
        }
        catch(NumberFormatException e){
            showAlert("Input must be a whole number!");
        }
    }

    /**
     * Removes selected item from the list of donuts in the donut order menu and also disables add cart
     * button is the donut cart is empty
     *
     * @param event
     */
    @FXML
    void removeFromOrder(ActionEvent event){
        if(orderList.getSelectionModel().getSelectedItem() == null){
            showAlert("Please select an item to remove");
            return;
        }
        order.remove(orderList.getSelectionModel().getSelectedItem());
        subtotal.clear();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.appendText(formatter.format(order.getDonutSubtotal()));
        if(order.getDonutOrder().isEmpty()){
            addCartButton.setDisable(true);
        }
    }

    /**
     * Enables the donut flavor combo box and also changes the flavors based off of the
     * various types of donuts available
     *
     * @param event
     */
    @FXML
    void changeFlavor(ActionEvent event) {
        donutFlavor.setDisable(false);
        String donut = donutType.getValue();
        switch(donut){
            case "Yeast":
                donutFlavor.setItems(order.getYeastList());
                break;
            case "Cake":
                donutFlavor.setItems(order.getCakeList());
                break;
            case "Hole":
                donutFlavor.setItems(order.getHoleList());
                break;
        }
    }

    /**
     * Enables the text area and button to determine the quantity of a specific donut
     *
     * @param event
     */
    @FXML
    void enableQuantity(ActionEvent event) {
        quantity.setDisable(false);
        addOrderButton.setDisable(false);
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

    /**
     * Helper method to aid in creating a message box
     *
     * @param message text to be said within the message box
     */
    private void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
