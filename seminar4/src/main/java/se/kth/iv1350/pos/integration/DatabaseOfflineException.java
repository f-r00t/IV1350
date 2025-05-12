package main.java.se.kth.iv1350.pos.integration;

import main.java.se.kth.iv1350.pos.util.LogHandler;

/**
 * Thrown when the database cannot be contacted.
 */
public class DatabaseOfflineException extends Exception {
    /**
     * Creates a new instance with a specified message.
     *
     */
    public DatabaseOfflineException(LogHandler logger) {
        super("The inventory database could not be contacted.");
        logger.log(this.getMessage());
    }
}
