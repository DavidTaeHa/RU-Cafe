package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
        this.controller = controller;
        orderList = controller.getOrderList();
        orderNumber.setItems(controller.getOrderList().getStoreOrders());
        orderNumber.getSelectionModel().selectFirst();
        order.setItems(orderNumber.getValue().getItems());
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        total.setText(formatter.format(orderNumber.getValue().getTotal()));
    }

    /**
     * Removes an order from the order list
     *
     * @param event
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        orderList.remove(orderNumber.getValue());
        if(!orderList.getStoreOrders().isEmpty()) {
            orderNumber.getSelectionModel().selectFirst();
            order.setItems(orderNumber.getValue().getItems());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            total.setText(formatter.format(orderNumber.getValue().getTotal()));
        }
        else {
            order.getItems().clear();
            total.clear();
        }
        order.refresh();
    }

    /**
     * Changes the list of items whenever the order number is changed
     *
     * @param event
     */
    @FXML
    void changeOrder(ActionEvent event) {
        if(orderNumber.getValue() != null) {
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
    void exportOrders(ActionEvent event) {

    }

}