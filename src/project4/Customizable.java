package project4;

/**
 * This interface will be implemented by classes that will involve an addition or removal of an instance
 * of an object
 *
 * @author David Ha, Andrew McAvoy
 */
public interface Customizable {
    /**
     * Adds instance of an object to a container
     *
     * @param obj object to be added to container
     * @return true if successfully added; false if otherwise
     */
    boolean add(Object obj);

    /**
     * Removes instance of an object from a container
     *
     * @param obj object to be removed from container
     * @return true if successfully removed; false if otherwise
     */
    boolean remove(Object obj);
}
