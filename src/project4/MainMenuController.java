package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that will handle all the user input and output made in the Main Menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class MainMenuController {

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DonutSelect.fxml"));
            Parent root = loader.load();
            //DonutController donutController = loader.getController();
            //donutController.setMainMenuController(this);
            Stage donutWindow = new Stage();
            donutWindow.setTitle("Donut Order Menu");
            donutWindow.setScene(new Scene(root));
            donutWindow.initModality(Modality.APPLICATION_MODAL);
            donutWindow.show();
        }
        catch(IOException e){

        }
    }

    @FXML
    void openPastOrderMenu(ActionEvent event) {

    }
}
