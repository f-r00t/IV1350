package main.java.se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cash register, includes logic to control the balance of the cash register
 */

public class CashRegister {
    private float balance;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Adds money to the cash register
     *
     * @param amountPaid  The amount of money paid by the customer.
     */
    public void addMoney(float amountPaid) {
        balance += amountPaid;
        notifyObservers();
    }

    /** 
     * @return the current balance.
     */
    public float getBalance() {
        return balance;
    }

    /** 
     * Notifies the revenue observers
     */
    private void notifyObservers() {
        for (RevenueObserver obs : revenueObservers) {
            obs.newBalance(balance);
        }
    }

    /**
     * Adds money to the cash register
     *
     * @param amountPaid  The amount of money paid by the customer.
     */
    public void addRevenueObservers(ArrayList<RevenueObserver> obs) {
        revenueObservers = obs;
    }
}
