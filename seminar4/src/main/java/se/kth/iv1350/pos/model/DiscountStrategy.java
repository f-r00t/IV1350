package main.java.se.kth.iv1350.pos.model;

public interface DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue);
}
