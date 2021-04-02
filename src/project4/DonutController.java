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
    private TextArea quantity;

    @FXML
    private Button addOrderButton, removeButton, addCartButton;

    DonutOrder order;
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
        //May need to add error handling for nonselected item
        order.remove(orderList.getSelectionModel().getSelectedItem());
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
     * Helper method to aid in creating a alert box
     *
     * @param message
     */
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
