package modelTest;

import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testAddItemAndTotals() {
        Sale sale = new Sale();
        ItemDTO apple = new ItemDTO(1, "Granny Smith Apple", 10.0f, 0.12f, "Fresh apple");

        sale.addItem(apple, 3);

        assertEquals(3, sale.getItems().get(apple));
        assertEquals(30.0f, sale.getTotalPrice(), 0.001);
        assertEquals(3.6f, sale.getTotalVAT(), 0.001);
    }

    @Test
    public void testApplyDiscount() {
        Sale sale = new Sale();
        ItemDTO banana = new ItemDTO(2, "Banana", 5.0f, 0.12f, "Cool banana");

        sale.addItem(banana, 2);

        sale.applyDiscount(0.8f);

        assertEquals(8.0f, sale.getTotalPrice(), 0.001);
    }
}
