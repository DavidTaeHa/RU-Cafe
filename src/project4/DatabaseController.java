package project4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Class that will handle all the user input and output made in the database menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class DatabaseController {

    @FXML
    private ListView<Order> order;

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
        //orderList = controller.getOrderList();
        //orderNumber.setItems(controller.getOrderList().getStoreOrders());
        //orderNumber.getSelectionModel().selectFirst();
        //order.setItems(orderNumber.getItems());
        //total.setText(orderNumber);
    }

}