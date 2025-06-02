package main.java.se.kth.iv1350.pos.view;

import main.java.se.kth.iv1350.pos.util.RevenueObserverTemplate;

public class TotalRevenueView extends RevenueObserverTemplate {
    /**
    * Shows total income in stdout
    */
    @Override 
    protected void doShowTotalIncome() throws Exception {
        System.out.printf("Total revenue: %.2f SEK%n", getTotalRevenue());
    }
    /**
    * Handles any errors with printing to stdout
    */
    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Could not show total revenue: " + e.getMessage());
    }
}
