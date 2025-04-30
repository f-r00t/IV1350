package controllerTest;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        controller.startSale();
    }

    @Test
    public void testScanSingleItem() {
        ItemDTO item = controller.scanItem(1);
        assertNotNull(item);
        assertEquals(1, item.getIdentifier());
        assertTrue(controller.getTotalPrice() > 0);
    }

    @Test
    public void testScanMultipleItems() {
        controller.scanItem(1, 2); // 2 apples at 9.99 each
        controller.scanItem(2);    // 1 pear at 14.99
        float expectedTotal = (9.99f * 2) + 14.99f;
        float actualTotal = controller.getTotalPrice();
        assertEquals(expectedTotal, actualTotal, 0.01f);
    }

    @Test
    public void testScanSameItemTwiceIncreasesQuantity() {
        controller.startSale();
        controller.scanItem(1);
        controller.scanItem(1);
        float expectedTotal = 9.99f * 2;
        float actualTotal = controller.getTotalPrice();
        assertEquals(expectedTotal, actualTotal, 0.01f);
    }


    @Test
    public void testAddDiscount() {
        controller.scanItem(3, 2);
        float totalBefore = controller.getTotalPrice();
        controller.addDiscount(1);
        float totalAfter = controller.getTotalPrice();
        assertTrue(totalAfter < totalBefore);
        assertEquals(totalBefore * 0.15f, totalAfter, 0.01);
    }

    @Test
    public void testEndSaleCalculatesCorrectChange() {
        controller.scanItem(1);
        float total = controller.getTotalPrice();
        float paid = total + 10;
        Receipt receipt = controller.endSale(paid);
        assertNotNull(receipt);
        assertEquals(10, receipt.getChange(), 0.01);
    }

    @Test
    public void testStartNewSaleResetsState() {
        controller.scanItem(1);
        float oldTotal = controller.getTotalPrice();
        controller.endSale(oldTotal + 5);
        controller.startSale();
        assertEquals(0.0f, controller.getTotalPrice(), 0.01);
    }
}
