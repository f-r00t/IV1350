package main.java.se.kth.iv1350.pos.model;

public class PercentageStrategy implements DiscountStrategy {
    public float calculateDiscount(Sale sale, float discountValue) {
        return sale.getTotalPrice() * discountValue;
    }
}
