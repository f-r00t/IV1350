package main.java.se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {
    private float balance;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();

    public void addMoney(float amountPaid) {
        balance += amountPaid;
        notifyObservers();
    }

    public float getBalance() {
        return balance;
    }

    private void notifyObservers() {
        for (RevenueObserver obs : revenueObservers) {
            obs.newBalance(balance);
        }
    }

    public void addRevenueObservers(ArrayList<RevenueObserver> obs) {
        revenueObservers = obs;
    }
}
