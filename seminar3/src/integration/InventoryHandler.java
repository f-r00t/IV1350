package integration;

import java.util.HashMap;
import java.util.Map;

public class InventoryHandler {

    private Map<Integer, ItemDTO> inventoryList = new HashMap<>();
    private Map<Integer, Integer> inventoryStock = new HashMap<>();

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

    public ItemDTO getItem(int identifier) {
        return inventoryList.get(identifier);
    }
    
    public void updateInventory(Map<ItemDTO, Integer> items) {
        for (Map.Entry<ItemDTO, Integer> entry : items.entrySet()) {
            ItemDTO item = entry.getKey();
            int quantity = entry.getValue();
            updateQuantity(item.getIdentifier(), quantity);
        }
    }

    private void updateQuantity(Integer identifier, int amount) {
        inventoryStock.put(identifier, inventoryStock.get(identifier) - amount);
    }

    public int getStockLevel(Integer itemIdentifier) {
        return inventoryStock.get(itemIdentifier);
    }

}
