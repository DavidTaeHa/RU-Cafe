package project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents a Donut container that contains instances of the Donut class
 *
 * @author David Ha, Andrew McAvoy
 */
public class DonutOrder implements Customizable{
    private ObservableList<String> yeastList;
    private ObservableList<String> cakeList;
    private ObservableList<String> holeList;
    private ObservableList<Donut> donutOrder;
    private double donutSubtotal;

    /**
     * Constructor for the DonutOrder class
     */
    public DonutOrder(){
        this.yeastList = FXCollections.observableArrayList("original", "glazed", "strawberry");
        this.cakeList = FXCollections.observableArrayList("chocolate", "cider", "maple-bacon");
        this.holeList = FXCollections.observableArrayList("chocolate", "jelly", "powder");
        this.donutOrder = FXCollections.observableArrayList();
        this.donutSubtotal = 0;
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
            if(!donutOrder.contains(obj)){
                donutOrder.add((Donut)obj);
                donutSubtotal = donutSubtotal + ((Donut) obj).getItemPrice();
            }
            else if (donutOrder.contains(obj)){
                Donut temp = donutOrder.get(donutOrder.indexOf(obj));
                temp.setQuantity(((Donut) obj).getQuantity() + temp.getQuantity());
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
            donutSubtotal = donutSubtotal - ((Donut) obj).getItemPrice();
            return true;
        }
        return false;
    }

}
