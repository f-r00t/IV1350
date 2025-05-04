package integration;
import java.util.HashMap;
import java.util.Map;
import model.Sale;

/**
 * Handles the discount database.
 * Contains methods for retrieving information from the database.
 */

public class DiscountHandler {

    Map<Integer, Float> discounts = new HashMap<>();

    /**
     * Constructs and initializes {@code DiscountHandler}
     *
     */

    public DiscountHandler() {

        discounts.put(1, 0.15f);
        discounts.put(2, 0.1f);
        discounts.put(3, 0.5f);
    
    }

    /**
     * Retrieves the discount percentage for a given customer ID.
     *
     * @param customerID The customer ID.
     * @return The discount percentage for the customer.
     */

    public float getDiscount(int customerID) {
        return discounts.get(customerID);
    }
}
