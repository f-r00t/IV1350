package test.java.se.kth.iv1350.pos.integrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.DiscountHandler;
import main.java.se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountHandlerTest {

    private DiscountHandler discountHandler;
    private Sale dummySale;

    @BeforeEach
    public void setUp() {
        discountHandler = new DiscountHandler();
        dummySale = new Sale();
    }

    @Test
    public void testGetDiscountKnownCustomer() {
        float discount = discountHandler.getDiscountValue(1);
        assertEquals(0.15f, discount, 0.0001);
    }

    @Test
    public void testGetDiscountMultipleKnownCustomers() {
        assertEquals(0.1f, discountHandler.getDiscountValue(2), 0.0001);
        assertEquals(10f, discountHandler.getDiscountValue(3), 0.0001);
    }

    @Test
    public void testGetDiscountTypeKnownCustomer() {
        String discountType = discountHandler.getDiscountType(1);
        assertEquals("PERCENTAGE", discountType);
    }

    @Test
    public void testGetDiscountTypeMultipleKnownCustomers() {
        assertEquals("PERCENTAGE", discountHandler.getDiscountType(2));
        assertEquals("BONUS", discountHandler.getDiscountType(3));
    }
}
