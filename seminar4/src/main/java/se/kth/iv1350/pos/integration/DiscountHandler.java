package main.java.se.kth.iv1350.pos.integration;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import main.java.se.kth.iv1350.pos.model.Sale;

/**
 * Handles the discount database.
 * Contains methods for retrieving information from the database.
 */

public class DiscountHandler {

    Map<Integer, Float> discounts = new HashMap<>();
    Map<Integer, String> discountTypes = new HashMap<>();

    /**
     * Constructs and initializes {@code DiscountHandler}
     *
     */

    public DiscountHandler() {

        discounts.put(1, 0.15f);
        discountTypes.put(1, "PERCENTAGE");

        discounts.put(2, 0.1f);
        discountTypes.put(2, "PERCENTAGE");

        discounts.put(3, 10f);
        discountTypes.put(3, "BONUS");

        discounts.put(4, 15f);
        discountTypes.put(4, "BONUS");
    
    }

    /**
     * Retrieves the discount percentage/bonus for a given customer ID.
     *
     * @param customerID The customer ID.
     * @return The discount value for the customer.
     */
    public float getDiscountValue(int customerID) {
        return discounts.get(customerID);
    }

    /**
     * Retrieves the discount type (percentage or bonus check) for a given customer ID.
     *
     * @param customerID The customer ID.
     * @return The discount type for the customer.
     */
    public String getDiscountType(int customerID) {
        return discountTypes.get(customerID);
    }
}
