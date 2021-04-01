package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that will handle all the user input and output made in the Main Menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class MainMenuController {
    @FXML
    private Button donutButton, coffeeButton, pastOrderButton, cartButton;

    @FXML
    void openCartMenu(ActionEvent event) {

    }

    @FXML
    void openCoffeeMenu(ActionEvent event) {

    }

    /**
     * Opens the menu to add donuts to the cart
     *
     * @param event
     */
    @FXML
    void openDonutMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DonutSelect.fxml"));
            Stage donutWindow = new Stage();
            donutWindow.setTitle("Donut Order Menu");
            donutWindow.setScene(new Scene(root));
            donutWindow.show();
        }
        catch(IOException e){

        }
    }

    @FXML
    void openPastOrderMenu(ActionEvent event) {

    }
}
