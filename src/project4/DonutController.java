package project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

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
    ObservableList<String> test = FXCollections.observableArrayList("yeast", "cake", "hole");

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
            case "yeast":
                donutFlavor.setItems(order.getYeastList());
                break;
            case "cake":
                donutFlavor.setItems(order.getCakeList());
                break;
            case "hole":
                donutFlavor.setItems(order.getHoleList());
                break;
        }
    }
}
