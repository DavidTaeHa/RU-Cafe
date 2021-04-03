package project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

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
    private TextArea quantity;

    @FXML
    private Button addCartButton;

    @FXML
    private ComboBox<String> size;

    @FXML
    private CheckBox cream, syrup, milk, caramel, whipped;

    private MainMenuController controller;

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
    }

    /**
     * Adds the coffee to the cart
     *
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event) {


    }

}
