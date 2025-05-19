package main.java.se.kth.iv1350.pos.model;

/**
 * Implementation of the discount calculation strategy
 * that uses a flat amount to be discounted
 * 
 */
public class BonusCheckStrategy implements DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue) {
        return discountValue;
    }
}
