package project4;
import java.util.ArrayList;

/**
 * This class extends the MenuItem class and contains common attributes and operations specific to an
 * instance of a coffee
 *
 * @author David Ha, Andrew McAvoy
 */
public class Coffee extends MenuItem implements Customizable {
    private int size;
    private ArrayList<String> addIn;
    private int addInQuantity;

    final static int SHORT = 1;
    final static int TALL = 2;
    final static int GRANDE = 3;
    final static int VENTI = 4;

    final static double SHORT_PRICE = 1.99;
    final static double TALL_PRICE = 2.49;
    final static double GRANDE_PRICE = 2.99;
    final static double VENTI_PRICE = 3.49;
    final static double ADDIN_PRICE = 0.20;

    public Coffee(int quantity, int size) {
        super("Coffee", quantity);
        this.size = size;
        this.addIn = new ArrayList<>();
        this.addInQuantity = 0;
    }

    /**
     * Calculates the price of an instance of a coffee
     */
    @Override
    public void calculateItemPrice() {
        switch (size) {
            case SHORT:
                super.setItemPrice(SHORT_PRICE + (addInQuantity * ADDIN_PRICE));
                break;
            case TALL:
                super.setItemPrice(TALL_PRICE + (addInQuantity * ADDIN_PRICE));
                break;
            case GRANDE:
                super.setItemPrice(GRANDE_PRICE + (addInQuantity * ADDIN_PRICE));
                break;
            case VENTI:
                super.setItemPrice(VENTI_PRICE + (addInQuantity * ADDIN_PRICE));
        }
    }

    /**
     * Adds a coffee add-in to the addIn container
     *
     * @param obj addIn to be added to the coffee
     * @return true if successfully added; false if otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof String) {
            addIn.add((String)obj);
            addInQuantity++;
            return true;
        }
        return false;
    }

    /**
     * Removes a coffee add-In from the addIn container
     *
     * @param obj addIn to be removed from the coffee
     * @return true of successfully removed; false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof String) {
            addIn.remove(obj);
            addInQuantity--;
            return true;
        }
        return false;
    }

    /**
     * Prints instance of this class in the following format
     * Coffee::quantity::size::addins
     *
     * @return textual representation of an instance of the coffee class
     */
    @Override
    public String toString() {
        String addins = "";
        if(addIn.isEmpty()){
            addins = "None";
        }
        else{
            for(String add: addIn){
                addins = addins + add;
            }
        }
        switch (size) {
            case SHORT:
                return super.getName() + " (" + super.getQuantity() + "), Short" + ", [" + addins + "]";
            case TALL:
                return super.getName() + " (" + super.getQuantity() + "), Tall" + ", [" + addins + "]";
            case GRANDE:
                return super.getName() + " (" + super.getQuantity() + "), Grande" + ", [" + addins + "]";
            case VENTI:
                return super.getName() + " (" + super.getQuantity() + "), Venti" + ", [" + addins + "]";
        }
        return "ERROR";
    }
}
