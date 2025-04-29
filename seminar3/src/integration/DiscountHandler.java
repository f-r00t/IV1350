package integration;
import java.util.HashMap;
import java.util.Map;
import model.Sale;

public class DiscountHandler {

    Map<Integer, Float> discounts = new HashMap<>();

    public DiscountHandler() {

        discounts.put(1, 0.15f);
        discounts.put(2, 0.1f);
        discounts.put(3, 0.5f);
    
    }

    public float getDiscount(int customerID, Sale sale) {
        return discounts.get(customerID);
    }
}
