package main.java.se.kth.iv1350.pos.controller;

import main.java.se.kth.iv1350.pos.integration.DatabaseOfflineException;
import main.java.se.kth.iv1350.pos.integration.ItemNotFoundException;

/**
 * Thrown when an item fails to scan.
 */
public class ScanItemException extends Exception{

    /**
    * Creates a new instance with a message according to the cause of the exception
    * @param cause the exception that caused this exception
    */
    public ScanItemException(Throwable cause) {
        super(generateMessage(cause), cause);
    }
    
    /**
     * Generates an appropriate message according to the original exception
     * 
     * @param cause The original cause of this exception
     * @return the message to display to the user
     */
    private static String generateMessage(Throwable cause) {
        if (cause instanceof ItemNotFoundException) {
            return "Item not found. Please try again or check the item ID.";
        } else if (cause instanceof DatabaseOfflineException) {
            return "Unable to connect to inventory database. Please try again later.";
        } else {
            return "Unknown scanning error. Please contact support.";
        }
    }
    
}