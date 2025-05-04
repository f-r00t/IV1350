package main.java.se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the external inverntory system.
 * Contains methods for updating and retrieving information from the inventory database.
 */

public class InventoryHandler {

    private Map<Integer, ItemDTO> inventoryList = new HashMap<>();
    private Map<Integer, Integer> inventoryStock = new HashMap<>();

    /**
     * Constructs and initializes the {@code InventoryHandler}.
     */

    public InventoryHandler() {

        ItemDTO apple = new ItemDTO(1, "Granny Smith Apple", 9.99f, 0.12f, "The freshest apple known to man.");
        ItemDTO pear = new ItemDTO(2, "Conference Pear", 14.99f, 0.12f, "French and delicious, yum!");
        ItemDTO banana = new ItemDTO(3, "Cool Banana", 7.99f, 0.12f, "A cool banana.");
        inventoryList.put(1, apple);
        inventoryList.put(2, pear);
        inventoryList.put(3, banana);
        inventoryStock.put(1, 10);
        inventoryStock.put(2, 10);
        inventoryStock.put(3, 10);
    
    }

    /**
     * Retrieves item details from its identifier.
     *
     * @param identifier The item ID.
     * @return The {@code ItemDTO} for the specified item, or {@code null} if not found.
     */

    public ItemDTO getItem(int identifier) {
        return inventoryList.get(identifier);
    }
    
    /**
     * Updates the inventory stock by subtracting the sold quantities from current amount in stock.
     *
     * @param items A map of items and their sold quantities.
     */

    public void updateInventory(Map<ItemDTO, Integer> items) {
        for (Map.Entry<ItemDTO, Integer> entry : items.entrySet()) {
            ItemDTO item = entry.getKey();
            int quantity = entry.getValue();
            updateQuantity(item.getIdentifier(), quantity);
        }
    }

    /**
     * Decreases the stock amount for a specific item.
     *
     * @param identifier The item ID.
     * @param amount The quantity of the item.
     */

    private void updateQuantity(Integer identifier, int amount) {
        inventoryStock.put(identifier, inventoryStock.get(identifier) - amount);
    }

    /**
     * Retrieves the current stock level of an item.
     *
     * @param itemIdentifier The item ID.
     * @return The current quantity in stock.
     */

    public int getStockLevel(Integer itemIdentifier) {
        return inventoryStock.get(itemIdentifier);
    }

}
