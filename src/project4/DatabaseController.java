package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * Class that will handle all the user input and output made in the database menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class DatabaseController {

    @FXML
    private ListView<MenuItem> order;

    @FXML
    private Button cancelOrderButton, exportButton;

    @FXML
    private ComboBox<Order> orderNumber;

    @FXML
    private TextField total;

    private MainMenuController controller;
    private StoreOrders orderList;

    /**
     * Gets reference of the main controller to share data betweeen the Donut Controller and
     * the main controller
     *
     * @param controller main controller
     */
    public void setMainMenuController(MainMenuController controller) {
        try {
            this.controller = controller;
            orderList = controller.getOrderList();
            orderNumber.setItems(controller.getOrderList().getStoreOrders());
            orderNumber.getSelectionModel().selectFirst();
            order.setItems(orderNumber.getValue().getItems());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            total.setText(formatter.format(orderNumber.getValue().getTotal()));
            if (orderList.getStoreOrders().isEmpty()) {
                exportButton.setDisable(true);
                cancelOrderButton.setDisable(true);
            } else {
                exportButton.setDisable(false);
                cancelOrderButton.setDisable(false);
            }
        } catch (NullPointerException e) {
            showAlert("The database currently has no store orders.");
        }
    }

    /**
     * Removes an order from the order list
     *
     * @param event
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        orderList.remove(orderNumber.getValue());
        if (!orderList.getStoreOrders().isEmpty()) {
            orderNumber.getSelectionModel().selectFirst();
            order.setItems(orderNumber.getValue().getItems());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            total.setText(formatter.format(orderNumber.getValue().getTotal()));
        } else {
            order.getItems().clear();
            total.clear();
        }
        if (orderList.getStoreOrders().isEmpty()) {
            exportButton.setDisable(true);
            cancelOrderButton.setDisable(true);
        } else {
            exportButton.setDisable(false);
            cancelOrderButton.setDisable(false);
        }
        order.refresh();
        showMessage("Order has been canceled");
    }

    /**
     * Changes the list of items whenever the order number is changed
     *
     * @param event
     */
    @FXML
    void changeOrder(ActionEvent event) {
        if (orderNumber.getValue() != null) {
            order.setItems(orderNumber.getValue().getItems());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            total.setText(formatter.format(orderNumber.getValue().getTotal()));
        }
    }

    /**
     * Exports the orders onto a text file
     *
     * @param event
     */
    @FXML
    void exportOrders(ActionEvent event) throws IOException {
        orderList.exportDatabase();
        showMessage("Orders successfully exported.");
    }

    /**
     * Helper method to aid in creating a message box
     *
     * @param message text to be said within the error box
     */
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    /**
     * Helper method to aid in creating an warning box
     *
     * @param message text to be said within the error box
     */
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

}