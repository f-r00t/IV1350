package main.java.se.kth.iv1350.pos.model;

/**
 * Implementation of the discount calculation strategy
 * that calculates bases on a percentage discount
 * 
 */
public class PercentageStrategy implements DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue) {
        return sale.getTotalPrice() * discountValue;
    }
}
