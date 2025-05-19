package main.java.se.kth.iv1350.pos.model;

public class BonusCheckStrategy implements DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue) {
        return discountValue;
    }
}
