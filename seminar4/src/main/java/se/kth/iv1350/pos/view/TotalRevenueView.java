package main.java.se.kth.iv1350.pos.view;

import main.java.se.kth.iv1350.pos.model.RevenueObserver;

public class TotalRevenueView implements RevenueObserver {

    public void newBalance(float total) {
        System.out.println("Total revenue is now: " + total);
    }
    
}
