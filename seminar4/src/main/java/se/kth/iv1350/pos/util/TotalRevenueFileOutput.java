package main.java.se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs total revenue to a file using the Template Method pattern.
 */
public class TotalRevenueFileOutput extends RevenueObserverTemplate {
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("RevenueLog.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    @Override
    protected void doShowTotalIncome() throws IOException {
        if (logStream != null) {
            logStream.println("The new total revenue is: " + getTotalRevenue() + " SEK");
        } else {
            throw new IOException("Log stream is not initialized.");
        }
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Failed to log revenue to file: " + e.getMessage());
    }
}
