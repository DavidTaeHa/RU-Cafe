package project4;

/**
 * This class extends the MenuItem class and contains common attributes and operations specific to an
 * instance of a donut
 *
 * @author David Ha, Andrew McAvoy
 */
public class Donut extends MenuItem {
    private int donutType;

    final static int YEAST_DONUT = 1;
    final static int CAKE_DONUT = 2;
    final static int DONUT_HOLE = 3;

    final static double YEAST_PRICE = 1.39;
    final static double CAKE_PRICE = 1.59;
    final static double HOLE_PRICE = 0.33;

    /**
     * Constructor for Donut class
     *
     * @param name      name of the donut
     * @param donutType type of donut
     */
    public Donut(String name, int quantity, int donutType) {
        super(name, quantity);
        this.donutType = donutType;
    }

    /**
     * Calculates the price of a donut
     */
    @Override
    public void calculateItemPrice() {
        switch (donutType) {
            case YEAST_DONUT:
                super.setItemPrice(YEAST_PRICE);
                break;
            case CAKE_DONUT:
                super.setItemPrice(CAKE_PRICE);
                break;
            case DONUT_HOLE:
                super.setItemPrice(HOLE_PRICE);
                break;
        }
    }

    /**
     * Prints instance of this class in the following form
     * name::quantity::donutType
     *
     * @return textual representation of an instance of a donut class
     */
    public String toString() {
        switch (donutType) {
            case YEAST_DONUT:
                return super.getName() + "::Quantity " + super.getQuantity() + "::Yeast Donut";
            case CAKE_DONUT:
                return super.getName() + "::Quantity " + super.getQuantity() + "::Cake Donut";
            case DONUT_HOLE:
                return super.getName() + "::Quantity " + super.getQuantity() + "::Donut Hole";
        }
        return "ERROR";
    }
}
