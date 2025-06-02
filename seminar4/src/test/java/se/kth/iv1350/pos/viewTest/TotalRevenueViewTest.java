package test.java.se.kth.iv1350.pos.viewTest;

import main.java.se.kth.iv1350.pos.view.TotalRevenueView;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private TotalRevenueView totalView = new TotalRevenueView();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void PrintsRevenue() {
        
        float sum = 1000;

        totalView.newSaleWasMade(sum);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Total revenue: 1000,00 SEK"), 
                   "Output should return new total revenue.");
    }
}
