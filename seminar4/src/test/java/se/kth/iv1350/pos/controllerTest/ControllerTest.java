package test.java.se.kth.iv1350.pos.controllerTest;

import main.java.se.kth.iv1350.pos.controller.Controller;
import main.java.se.kth.iv1350.pos.integration.DatabaseOfflineException;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.integration.ItemNotFoundException;
import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.controller.ScanItemException;

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
    public void testScanSingleItem() throws ScanItemException{
        ItemDTO item = controller.scanItem(1);
        assertNotNull(item);
        assertEquals(1, item.getIdentifier());
        assertTrue(controller.getTotalPrice() > 0);
    }

    @Test
    public void testScanMultipleItems() throws ScanItemException{
        controller.scanItem(1, 2); // 2 apples at 9.99 each
        controller.scanItem(2);    // 1 pear at 14.99
        float expectedTotal = (9.99f * 2) + 14.99f;
        float actualTotal = controller.getTotalPrice();
        assertEquals(expectedTotal, actualTotal, 0.01f);
    }

    @Test
    public void testScanSameItemTwiceIncreasesQuantity() {
        controller.startSale();
        try {
            controller.scanItem(1);
            controller.scanItem(1);
        } catch (ScanItemException e) {
            fail("Exception was thrown: " + e.getMessage());
        }
        float expectedTotal = 9.99f * 2;
        float actualTotal = controller.getTotalPrice();
        assertEquals(expectedTotal, actualTotal, 0.01f);
    }


    @Test
    public void testAddDiscount() {
        try {
            controller.scanItem(2, 2);
        } catch (ScanItemException e) {
            fail("Exception was thrown: " + e.getMessage());
        }
        float totalBefore = controller.getTotalPrice();
        controller.addDiscount(2);
        float totalAfter = controller.getTotalPrice();
        assertTrue(totalAfter < totalBefore);
        assertEquals(totalBefore - 3f, totalAfter, 0.01);
    }

    @Test
    public void testEndSaleCalculatesCorrectChange() {
        try {
        controller.scanItem(1);
        } catch (ScanItemException e) {
            fail("Exception was thrown: " + e.getMessage());
        }
        float total = controller.getTotalPrice();
        float paid = total + 10;
        Receipt receipt = controller.endSale(paid);
        assertNotNull(receipt);
        assertEquals(10, receipt.getChange(), 0.01);
    }

    @Test
    public void testStartNewSaleResetsState() {
        try {
            controller.scanItem(1);
        } catch (ScanItemException e) {
            fail("Exception was thrown: " + e.getMessage());
        }
        float oldTotal = controller.getTotalPrice();
        controller.endSale(oldTotal + 5);
        controller.startSale();
        assertEquals(0.0f, controller.getTotalPrice(), 0.01);
    }

    @Test
    public void testDataBaseNotFound() {
        assertThrows(ScanItemException.class, () -> {
            controller.scanItem(404);
        });
    }

    @Test
    public void testItemNotFound() {
        assertThrows(ScanItemException.class, () -> {
            controller.scanItem(4);
        });
    }

}
