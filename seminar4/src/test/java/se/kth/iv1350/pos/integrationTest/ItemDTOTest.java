package integrationTest;

import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {

    @Test
    public void testItemDTOInitialization() {
        int expectedId = 42;
        String expectedName = "Test Item";
        float expectedPrice = 19.99f;
        float expectedTax = 0.25f;
        String expectedDescription = "This is a test item.";

        ItemDTO item = new ItemDTO(expectedId, expectedName, expectedPrice, expectedTax, expectedDescription);

        assertEquals(expectedId, item.getIdentifier());
        assertEquals(expectedName, item.getName());
        assertEquals(expectedPrice, item.getPrice(), 0.001);
        assertEquals(expectedTax, item.getTaxRate(), 0.001);
        assertEquals(expectedDescription, item.getDescription());
    }
}
