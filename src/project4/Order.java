package project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents an Order container that contains instances of MenuItems classes
 *
 * @author David Ha, Andrew McAvoy
 */
public class Order implements Customizable {
    private ObservableList<MenuItem> items;
    private double subtotal;
    private double taxTotal;
    private double total;
    private int orderNumber;

    private static int orderNumberTracker = 1;

    final static double TAX = 0.06625;

    /**
     * Constructor for the Order class
     */
    public Order() {
        this.items = FXCollections.observableArrayList();
        this.subtotal = 0;
        this.taxTotal = 0;
        this.total = 0;
        this.orderNumber = orderNumberTracker;
        orderNumberTracker++;
    }

    /**
     * Getter method for list of items
     *
     * @return list if items within the order
     */
    public ObservableList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds items from the menu to the order container and adds to subtotal
     *
     * @param obj menu item to be added to the order
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            if (!hasItem((MenuItem) obj)) {
                items.add((MenuItem) obj);
                subtotal = subtotal + ((MenuItem) obj).getItemPrice();
                taxTotal = subtotal * TAX;
                total = subtotal + taxTotal;
            } else if (hasItem((MenuItem) obj)) {
                MenuItem temp = items.get(getItem((MenuItem) obj));
                temp.setQuantity(((MenuItem) obj).getQuantity() + temp.getQuantity());
                subtotal = subtotal + ((MenuItem) obj).getItemPrice();
                taxTotal = subtotal * TAX;
                total = subtotal + taxTotal;
            }
            return true;
        }
        return false;
    }

    /**
     * Removes items from the order container
     *
     * @param obj item to be removed from order
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Donut) {
            items.remove(obj);
            subtotal = subtotal - ((MenuItem) obj).getItemPrice();
            taxTotal = subtotal * TAX;
            total = subtotal + taxTotal;
            return true;
        }
        return false;
    }

    /**
     * Helper method to check if the donut order already contains an instance of a donut
     * to be added to the order
     *
     * @param donut donut to be checked within the donut order
     * @return true if it exists; false if otherwise
     */
    private boolean hasItem(MenuItem item) {
        if (item instanceof Donut) {
            for (MenuItem temp : items) {
                if (temp instanceof Donut && temp.getName().equals(item.getName()) &&
                        ((Donut) temp).getDonutType() == ((Donut) item).getDonutType()) {
                    return true;
                }
            }
        } else if (item instanceof Coffee) {

        }
        return false;
    }

    /**
     * Helper method to get an index of a menuitem within the order
     *
     * @param item menu item to be found within the order
     * @return true if it exists; false if otherwise
     */
    private int getItem(MenuItem item) {
        int index = 0;
        if (item instanceof Donut) {
            for (MenuItem temp : items) {
                if (temp instanceof Donut && temp.getName().equals(item.getName()) &&
                        ((Donut) temp).getDonutType() == ((Donut) item).getDonutType()) {
                    return index;
                }
                index++;
            }
        } else if (item instanceof Coffee) {

        }
        return -1;
    }
}
