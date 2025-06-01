package test.java.se.kth.iv1350.pos.integrationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.integration.ReceiptPrinter;
import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.model.Sale;

public class ReceiptPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Sale sale;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        Locale.setDefault(Locale.US); // Ensure consistent decimal format
        System.setOut(new PrintStream(outContent));

        // Set up a sale with one item
        ItemDTO apple = new ItemDTO(1, "Granny Smith Apple", 10.00f, 0.12f, "The freshest apple.");
        sale = new Sale();
        sale.addItem(apple, 2); // Add 2 apples

        receipt = new Receipt(sale, 50.00f); // Assume customer paid 50
    }

    @Test
    public void testReceiptIsPrintedCorrectly() {
        ReceiptPrinter printer = new ReceiptPrinter();
        printer.printReciept(receipt);

        String output = outContent.toString();

        assertTrue(output.contains("Begin Receipt"), "Should contain receipt header.");
        assertTrue(output.contains("Granny Smith Apple"), "Should contain item name.");
        assertTrue(output.contains("2x"), "Should contain item quantity.");
        assertTrue(output.contains("Total price: 20.00 SEK"), "Should show total price.");
        assertTrue(output.contains("Cash: 50.00 SEK"), "Should show amount paid.");
        assertTrue(output.contains("Change: 30 SEK"), "Should show correct change.");
        assertTrue(output.contains("Time of sale:"), "Should print timestamp.");
        assertTrue(output.contains("End Receipt"), "Should contain receipt footer.");
    }

}
