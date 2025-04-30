package model;

import java.util.HashMap;
import java.util.Map;
import integration.ItemDTO;

public class Sale {

    // private int timestamp;
    private Map<ItemDTO, Integer> items = new HashMap<>();
    private float totalPrice;
    private float totalVAT;

    public void addItem(ItemDTO item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        totalPrice += item.getPrice() * quantity;
        totalVAT += item.getPrice() * item.getTaxRate() * quantity;
    }

    public void applyDiscount(float discount) {
        totalPrice = totalPrice * discount;
    }

    public Map<ItemDTO, Integer> getItems() {
        return items;
    }

    public float getTotalVAT() {
        return totalVAT;
    }
    
    public float getTotalPrice() {
        return totalPrice;
    }
}
