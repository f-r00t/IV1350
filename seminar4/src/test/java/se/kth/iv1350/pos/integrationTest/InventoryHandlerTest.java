package integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.DatabaseOfflineException;
import main.java.se.kth.iv1350.pos.integration.InventoryHandler;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.integration.ItemNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryHandlerTest {

    private InventoryHandler inventory;

    @BeforeEach
    public void setUp() {
        inventory = new InventoryHandler();
    }

    @Test
    public void testGetItemReturnsCorrectItem() {
        ItemDTO item = null;
        try {
            item = inventory.getItem(1);
        } catch (ItemNotFoundException e) {

        } catch (DatabaseOfflineException e) {

        }
        assertNotNull(item);
        assertEquals("Granny Smith Apple", item.getName());
        assertEquals(9.99f, item.getPrice(), 0.001);
    }

    @Test
    public void testNonExistingItem() {
        assertThrows(ItemNotFoundException.class, () -> {
            inventory.getItem(5);
        });
    }

    @Test
    public void testDataBaseNotFound() {
        assertThrows(DatabaseOfflineException.class, () -> {
            inventory.getItem(404);
        });
    }


    @Test
    public void testUpdateInventoryReducesStock() {
        ItemDTO item = null;
        try {
            item = inventory.getItem(1);
        } catch (ItemNotFoundException e) {

        } catch (DatabaseOfflineException e) {

        } 
        int originalStock = inventory.getStockLevel(1);

        Map<ItemDTO, Integer> soldItems = new HashMap<>();
        soldItems.put(item, 3);

        inventory.updateInventory(soldItems);

        assertEquals(originalStock - 3, inventory.getStockLevel(1));
    }
}
