package main.java.se.kth.iv1350.pos.model;

/**
 * Defines the discount calculation
 * 
 */
public interface DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue);
}
