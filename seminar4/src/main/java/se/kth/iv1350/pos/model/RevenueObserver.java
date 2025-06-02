package main.java.se.kth.iv1350.pos.model;

public interface RevenueObserver {
    void newSaleWasMade(float priceOfTheSaleThatWasJustMade);
}
