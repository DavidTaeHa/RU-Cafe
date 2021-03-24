package project4;

/**
 * This class represents an instance of an item on the menu and defines common attributes for all MenuItem subclasses
 *
 * @author David Ha, Andrew McAvoy
 */
public class MenuItem {
    private String name;
    private int quantity;
    private double itemPrice;

    /**
     * Constructor for MenuItem class
     *
     * @param name Name of the item
     */
    public MenuItem(String name){
        this.name = name;
        this.quantity = 0;
        this.itemPrice = 0;
    }

    /**
     * Setter method for item price
     *
     * @param itemPrice Price of an item
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Setter method for quantity
     *
     * @param quantity Number of an item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method for the subclasses to implement and override for calculating item price
     */
    public void calculateItemPrice(){
        //Overriden by subclasses
    }
}
