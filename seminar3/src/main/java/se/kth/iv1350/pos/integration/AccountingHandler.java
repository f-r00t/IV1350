package main.java.se.kth.iv1350.pos.integration;

import main.java.se.kth.iv1350.pos.model.Sale;

/**
 * Handles the external accounting system.
 * Contains methods for registering a new sale in the accounting system.
 */

public class AccountingHandler {
    
    /**
     * Scans an item and adds it to the current sale.
     *
     * @param sale The sale to be recorded in the accounting system
     */
    public void registerSale(Sale sale) {

        System.out.println("Sale registered in accounting system!");
        
    }
}
