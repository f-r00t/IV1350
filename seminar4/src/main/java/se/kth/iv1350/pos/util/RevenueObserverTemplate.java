package main.java.se.kth.iv1350.pos.util;

import main.java.se.kth.iv1350.pos.model.RevenueObserver;

/**
 * Template method pattern for observing revenue.
 */
public abstract class RevenueObserverTemplate implements RevenueObserver {

    private float totalRevenue;

    /**
     * Template method that will be called when a new sale has been made.
     * @param priceOfTheSaleThatWasJustMade The price of the sale.
     */
    public void newSaleWasMade(float priceOfTheSaleThatWasJustMade) {
        calculateTotalIncome(priceOfTheSaleThatWasJustMade);
        showTotalIncome();
    }

    private void calculateTotalIncome(float priceOfTheSaleThatWasJustMade) {
        totalRevenue += priceOfTheSaleThatWasJustMade;
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected float getTotalRevenue() {
        return totalRevenue;
    }

    protected abstract void doShowTotalIncome() throws Exception;

    protected abstract void handleErrors(Exception e);
}
