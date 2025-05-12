package main.java.se.kth.iv1350.pos.integration;

import main.java.se.kth.iv1350.pos.util.LogHandler;

/**
 * Thrown when an item identifier does not exist in the inventory.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Creates a new instance with a specified message.
     *
     * @param itemId The identifier that could not be found.
     */
    public ItemNotFoundException(int itemId, LogHandler logger) {
        super("Item with ID " + itemId + " was not found in the inventory.");
        logger.log(this.getMessage());
    }
}
