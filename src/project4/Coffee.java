package project4;

/**
 * This class extends the MenuItem class and contains common attributes and operations specific to an
 * instance of a coffee
 *
 * @author David Ha, Andrew McAvoy
 */
public class Coffee extends MenuItem implements Customizable {
    private int size;
    private boolean[] addIn;
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

    final static int CREAM = 0;
    final static int SYRUP = 1;
    final static int MILK = 2;
    final static int CARAMEL = 3;
    final static int WHIPPED_CREAM = 4;

    final static int NUM_OF_ADDINS = 5;
    final static int INVALID = -1;

    public Coffee(String name, int size) {
        super(name);
        this.size = size;
        this.addIn = new boolean[NUM_OF_ADDINS];
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
        int index;
        if (obj instanceof String) {
            index = getIndex((String) obj);
            if ((index > INVALID) && (addIn[index] != true)) {
                addIn[index] = true;
                return true;
            }
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
        int index;
        if (obj instanceof String) {
            index = getIndex((String) obj);
            if ((index > INVALID) && (addIn[index] != false)) {
                addIn[index] = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to aid in retrieval of index of the add-in component relative to the addIn container
     *
     * @param obj Add-in to be added to the coffee
     * @return index of the add-in in the addIn container
     */
    private int getIndex(String obj) {
        int index = INVALID;
        switch (obj) {
            case "Cream":
                index = CREAM;
                break;
            case "Syrup":
                index = SYRUP;
                break;
            case "Milk":
                index = MILK;
                break;
            case "Caramel":
                index = CARAMEL;
                break;
            case "Whipped Cream":
                index = WHIPPED_CREAM;
        }
        return index;
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
        if (addIn[CREAM]) {
            addins = addins + "Cream ";
        }
        if (addIn[SYRUP]) {
            addins = addins + "Syrup ";
        }
        if (addIn[MILK]) {
            addins = addins + "Milk ";
        }
        if (addIn[CARAMEL]) {
            addins = addins + "Caramel ";
        }
        if (addIn[WHIPPED_CREAM]) {
            addins = addins + "Whipped Cream ";
        }
        if (addins.isEmpty()) {
            addins = addins + "None";
        }
        switch (size) {
            case SHORT:
                return "Coffee " + "::Quantity " + super.getQuantity() + "::Size Short" + "::Addins " + addins;
            case TALL:
                return "Coffee " + "::Quantity " + super.getQuantity() + "::Size Tall" + "::Addins " + addins;
            case GRANDE:
                return "Coffee " + "::Quantity " + super.getQuantity() + "::Size Grande" + "::Addins " + addins;
            case VENTI:
                return "Coffee " + "::Quantity " + super.getQuantity() + "::Size Venti" + "::Addins " + addins;
        }
        return "ERROR";
    }
}
