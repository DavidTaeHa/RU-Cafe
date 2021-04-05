package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
     * Getter method for order list
     *
     * @return order list
     */
    public ObservableList<Order> getStoreOrders() {
        return ordersList;
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

    /**
     * Exports and writes orders within the database to a text file
     *
     * @throws IOException
     */
    public void exportDatabase() throws IOException {
        File file = new File("export.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for(Order order : ordersList){
            writer.println("Order " + order.getOrderNumber() + ":");
            for(MenuItem item : order.getItems()){
                writer.println(item);
            }
            writer.println("------------------------------------------");
        }
        writer.close();
    }
}
