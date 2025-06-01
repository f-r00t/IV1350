package test.java.se.kth.iv1350.pos.viewTest;

import main.java.se.kth.iv1350.pos.view.TotalRevenueView;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private TotalRevenueView totalView = new TotalRevenueView();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testRegisterSale_PrintsConfirmationMessage() {
        
        TotalRevenueView accountingHandler = new TotalRevenueView();
        float sum = 1000;

        totalView.newBalance(sum);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Total revenue is now: 1000"), 
                   "Output should return new total revenue.");
    }
}
