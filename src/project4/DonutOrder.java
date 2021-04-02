package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents a Donut container that contains instances of the Donut class
 *
 * @author David Ha, Andrew McAvoy
 */
public class DonutOrder implements Customizable{
    private ObservableList<String> donutType;
    private ObservableList<String> yeastList;
    private ObservableList<String> cakeList;
    private ObservableList<String> holeList;
    private ObservableList<Donut> donutOrder;
    private double donutSubtotal;

    /**
     * Constructor for the DonutOrder class
     */
    public DonutOrder(){
        this.donutType = FXCollections.observableArrayList("Yeast", "Cake", "Hole");
        this.yeastList = FXCollections.observableArrayList("Original", "Glazed", "Strawberry");
        this.cakeList = FXCollections.observableArrayList("Chocolate", "Cider", "Maple-bacon");
        this.holeList = FXCollections.observableArrayList("Chocolate", "Jelly", "Powder");
        this.donutOrder = FXCollections.observableArrayList();
        this.donutSubtotal = 0;
    }

    /**
     * Getter method for donut subtotal for items within donut order
     *
     * @return subtotal of donuts
     */
    public double getDonutSubtotal() {
        return donutSubtotal;
    }

    /**
     * Getter method for list of different donut types
     *
     * @return list of donut types
     */
    public ObservableList<String> getDonutType() {
        return donutType;
    }

    /**
     * Getter method for the list of yeast donuts
     *
     * @return list of yeast donuts
     */
    public ObservableList<String> getYeastList() {
        return yeastList;
    }

    /**
     * Getter method for the list of cake donuts
     *
     * @return list of cake donuts
     */
    public ObservableList<String> getCakeList() {
        return cakeList;
    }

    /**
     * Getter method for the list of hole donuts
     *
     * @return list of yeast donuts
     */
    public ObservableList<String> getHoleList() {
        return holeList;
    }

    /**
     * Getter method for the list of donuts in the order
     *
     * @return donut order list
     */
    public ObservableList<Donut> getDonutOrder() {
        return donutOrder;
    }

    /**
     * Sets donut subtotal to zero
     */
    public void reset(){
        donutSubtotal = 0;
    }

    /**
     * Adds donuts to the donut order container and adds to the donut subtotal
     *
     * @param obj donut to be added to donut order
     * @return true if successfully added; false if otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Donut){
            if(!hasItem((Donut) obj)){
                donutOrder.add((Donut)obj);
                ((Donut) obj).calculateItemPrice();
                donutSubtotal = donutSubtotal + ((Donut) obj).getItemPrice();
            }
            else if (hasItem((Donut) obj)){
                Donut temp = donutOrder.get(getItem((Donut) obj));
                temp.setQuantity(((Donut) obj).getQuantity() + temp.getQuantity());
                ((Donut) obj).calculateItemPrice();
                donutSubtotal = donutSubtotal + ((Donut) obj).getItemPrice();
            }
            return true;
        }
        return false;
    }

    /**
     * Removes donuts form the donut order container subtracts from the donut subtotal
     *
     * @param obj donut to be removed from the donut order
     * @return true if successfully removed; false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Donut){
            donutOrder.remove(obj);
            ((Donut) obj).calculateItemPrice();
            donutSubtotal = donutSubtotal - ((Donut) obj).getItemPrice();
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
    private boolean hasItem(Donut donut){
        for(Donut temp:donutOrder){
            if(temp.getName().equals(donut.getName()) && temp.getDonutType() == donut.getDonutType()){
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to get an index of a donut within the donut order
     *
     * @param donut donut to be found within the donut order
     * @return true if it exists; false if otherwise
     */
    private int getItem(Donut donut){
        int index = 0;
        for(Donut temp:donutOrder){
            if(temp.getName().equals(donut.getName()) && temp.getDonutType() == donut.getDonutType()){
                return index;
            }
            index++;
        }
        return -1;
    }

}
