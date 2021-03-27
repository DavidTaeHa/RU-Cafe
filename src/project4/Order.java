package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents an Order container that contains instances of MenuItems classes
 *
 * @author David Ha, Andrew McAvoy
 */
public class Order implements Customizable{
    private ObservableList<MenuItem> items;
    private double subtotal;
    private double taxTotal;
    private double total;
    private static int orderNumber = 1;

    final static double TAX = 0.06625;

    /**
     * Constructor for the Order class
     */
    public Order(){
        this.items = FXCollections.observableArrayList();
        subtotal = 0;
    }

    /**
     * Setter method for order number
     *
     * @param orderNumber number to be incremented
     */
    public static void setOrderNumber(int orderNumber) {
        Order.orderNumber = orderNumber;
    }

    /**
     * Adds items from the menu to the order container and adds to subtotal
     *
     * @param obj menu item to be added to the order
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem){
            if(!items.contains(obj)){
                items.add((MenuItem)obj);
                subtotal = subtotal + ((MenuItem) obj).getItemPrice();
                taxTotal = subtotal * TAX;
                total = subtotal + taxTotal;
            }
            else if (items.contains(obj)){
                MenuItem temp = items.get(items.indexOf(obj));
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
        if(obj instanceof Donut){
            items.remove(obj);
            subtotal = subtotal - ((MenuItem) obj).getItemPrice();
            taxTotal = subtotal * TAX;
            total = subtotal + taxTotal;
            return true;
        }
        return false;
    }
}
