package main.java.se.kth.iv1350.pos.model;

import java.util.HashMap;
import java.util.Map;

import main.java.se.kth.iv1350.pos.integration.ItemDTO;

/**
 * Represents a single sale transaction, including the items sold, their quantities,
 * the total price, and the total VAT.
 */
public class Sale {

    private Map<ItemDTO, Integer> items = new HashMap<>();
    private float totalPrice;
    private float totalVAT;

    /**
     * Adds an item and its quantity to the current sale.
     * Updates the total price and VAT accordingly.
     *
     * @param item     The {@code ItemDTO} representing the item to add.
     * @param quantity The number of units of the item to add.
     */
    public void addItem(ItemDTO item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        totalPrice += item.getPrice() * quantity;
        totalVAT += item.getPrice() * item.getTaxRate() * quantity;
    }

    /**
     * Applies a discount amount to the total price of the sale.
     *
     * @param discount The amount to substract from the total price.
     */
    public void applyDiscount(float discount) {
        totalPrice -= discount;
    }

    /**
     * Returns a map of all items in the sale along with their quantities.
     *
     * @return A {@code Map<ItemDTO, Integer>} containing items and their quantities.
     */
    public Map<ItemDTO, Integer> getItems() {
        return items;
    }

    /**
     * Returns the total VAT amount for the sale.
     *
     * @return The total VAT as a float.
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * Returns the total price of the sale, including VAT and any applied discounts.
     *
     * @return The total price as a float.
     */
    public float getTotalPrice() {
        return totalPrice;
    }
}
