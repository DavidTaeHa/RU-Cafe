package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents a StoreOrder container that will contain instances of various orders made
 * by the customers
 *
 * @author David Ha, Andrew McAvoy
 */
public class StoreOrders implements Customizable{
    private ObservableList<Order> ordersList;

    /**
     * Constructor for store order class
     */
    public StoreOrders(){
        this.ordersList = FXCollections.observableArrayList();
    }

    /**
     * Adds an order to a the list of orders
     *
     * @param obj order to be added to the list
     * @return true if successfully added; false if otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            ordersList.add((Order) obj);
        }
        return false;
    }

    /**
     * Removes instance of an order from the list
     *
     * @param obj order to be removed from the list
     * @return true if successfully removed; false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            ordersList.remove(obj);
        }
        return false;
    }
}
