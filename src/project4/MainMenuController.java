package project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that will handle all the user input and output made in the Main Menu
 *
 * @author David Ha, Andrew McAvoy
 */
public class MainMenuController implements Initializable {

    private Order order;
    private StoreOrders orderList;

    /**
     * This method runs when instance of the donut order menu is opened
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        order = new Order();
        orderList = new StoreOrders();
    }

    @FXML
    void openCartMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
            Parent root = loader.load();
            CheckoutController checkoutController = loader.getController();
            checkoutController.setMainMenuController(this);
            Stage cartWindow = new Stage();
            cartWindow.setTitle("Cart");
            cartWindow.setScene(new Scene(root));
            cartWindow.initModality(Modality.APPLICATION_MODAL);
            cartWindow.show();
        }
        catch(IOException e){
            return;
        }
    }

    @FXML
    void openCoffeeMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoffeeSelect.fxml"));
            Parent root = loader.load();
            CoffeeController coffeeController = loader.getController();
            coffeeController.setMainMenuController(this);
            Stage coffeeWindow = new Stage();
            coffeeWindow.setTitle("Coffee Order Menu");
            coffeeWindow.setScene(new Scene(root));
            coffeeWindow.initModality(Modality.APPLICATION_MODAL);
            coffeeWindow.show();
        }
        catch(IOException e){
            return;
        }
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
            DonutController donutController = loader.getController();
            donutController.setMainMenuController(this);
            Stage donutWindow = new Stage();
            donutWindow.setTitle("Donut Order Menu");
            donutWindow.setScene(new Scene(root));
            donutWindow.initModality(Modality.APPLICATION_MODAL);
            donutWindow.show();
        }
        catch(IOException e){
            return;
        }
    }

    @FXML
    void openPastOrderMenu(ActionEvent event) {

    }

    /**
     * Adds menu items (Coffee) to the current order list
     *
     * @param item item to be added
     */
    public void addItems(MenuItem item){
        order.add(item);
    }

    /**
     * Adds menu items (Donuts) to the current order list
     *
     * @param donuts item to be added
     */
    public void addItems(DonutOrder donuts) {
        for (Donut temp : donuts.getDonutOrder()) {
            order.add(temp);
        }
        for (MenuItem temp : order.getItems()) {
            System.out.println(temp);
        }
        System.out.println("Subtotal " + order.getSubtotal());
        System.out.println("--------------------");
    }

    /**
     * Getter method for the order
     *
     * @return order of menu items
     */
    public Order getOrder() {
        return order;
    }
}
