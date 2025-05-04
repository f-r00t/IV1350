package modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.InventoryHandler;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    private Sale mockSale;
    private Receipt receipt;
    private InventoryHandler inventoryHandler;

    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        mockSale = new Sale();

        ItemDTO apple = inventoryHandler.getItem(1);
        ItemDTO pear = inventoryHandler.getItem(2);

        mockSale.addItem(apple, 2);
        mockSale.addItem(pear, 3);

        receipt = new Receipt(mockSale, 100.0f);
    }

    @Test
    public void testSalePopulation() {
        assertEquals(2, mockSale.getItems().get(inventoryHandler.getItem(1)), "There should be 2 Granny Smith Apples.");
        assertEquals(3, mockSale.getItems().get(inventoryHandler.getItem(2)), "There should be 3 Conference Pears.");
    }

    @Test
    public void testReceiptChange() {
        float expectedChange = 100.0f - (2 * 9.99f + 3 * 14.99f);
        assertEquals(expectedChange, receipt.getChange(), "Change should be calculated correctly.");
    }
}
