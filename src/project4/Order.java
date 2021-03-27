package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents an Order container that contains instances of MenuItems classes
 *
 * @author David Ha, Andrew McAvoy
 */
public class Order {
    private ObservableList<MenuItem> items;
    private static int orderNumber = 1;

    final static double TAX = 0.06625;

    /**
     * Constructor for the Order class
     */
    public Order(){
        this.items = FXCollections.observableArrayList();
    }
}
