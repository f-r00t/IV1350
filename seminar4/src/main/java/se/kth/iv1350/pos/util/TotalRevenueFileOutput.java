package main.java.se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.java.se.kth.iv1350.pos.model.RevenueObserver;

public class TotalRevenueFileOutput implements RevenueObserver {
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(
            new FileWriter("RevenueLog.txt"), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    @Override
    public void newBalance(float total) {
        logStream.println("The new total revenue is: " + total);
    }
}
