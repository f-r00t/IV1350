package integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.InventoryHandler;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;

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
        ItemDTO item = inventory.getItem(1);
        assertNotNull(item);
        assertEquals("Granny Smith Apple", item.getName());
        assertEquals(9.99f, item.getPrice(), 0.001);
    }

    @Test
    public void testUpdateInventoryReducesStock() {
        ItemDTO item = inventory.getItem(1);
        int originalStock = inventory.getStockLevel(1);

        Map<ItemDTO, Integer> soldItems = new HashMap<>();
        soldItems.put(item, 3);

        inventory.updateInventory(soldItems);

        assertEquals(originalStock - 3, inventory.getStockLevel(1));
    }
}
