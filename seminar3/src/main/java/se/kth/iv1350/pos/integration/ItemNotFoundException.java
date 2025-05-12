package main.java.se.kth.iv1350.pos.integration;

/**
 * Thrown when an item identifier does not exist in the inventory.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a specified message.
     *
     * @param itemId The identifier that could not be found.
     */
    public ItemNotFoundException(int itemId) {
        super("Item with ID " + itemId + " was not found in the inventory.");
    }
}
