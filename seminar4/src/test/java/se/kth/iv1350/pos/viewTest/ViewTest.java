package test.java.se.kth.iv1350.pos.viewTest;

import main.java.se.kth.iv1350.pos.controller.Controller;
import main.java.se.kth.iv1350.pos.view.View;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Controller controller = new Controller();;
    private View view = new View(controller);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    private void provideInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream((input + System.lineSeparator()).getBytes());
        System.setIn(inContent);
    }

    @Test
    void testCorrectSaleOutput() {
        provideInput(String.join(System.lineSeparator(),
            "1",    // item ID
            "",     // end scanning
            "0",    // discount ID
            "100"   // payment
        ));

        view.scanItems();

        String output = outContent.toString();
        assertTrue(output.contains("Enter item identifier:"), "Should prompt for item identifier.");
        assertTrue(output.contains("Add 1 item with item id 1"), "Should print correct item ID and quantity.");
        assertTrue(output.contains("Item ID: 1"), "Should print correct item ID.");
        assertTrue(output.contains("Item name: Granny Smith Apple"), "Should print correct item name.");
        assertTrue(output.contains("Item cost: 9.99 SEK"), "Should print correct price.");
        assertTrue(output.contains("VAT: 12.0%"), "Should print correct VAT rate.");
        assertTrue(output.contains("Item description: The freshest apple known to man."), "Should print correct item description.");
        assertTrue(output.contains("Total cost: 9,99 SEK"), "Should print correct sale total.");
        assertTrue(output.contains("Total VAT: 1,20 SEK"), "Should print correct tax cost.");
        assertTrue(output.contains("Enter customer ID for discount (or 0 to skip):"), "Should print customer ID propmt.");
        assertTrue(output.contains("Total cost is 10 SEK"), "Should print correct price at end of sale.");
        assertTrue(output.contains("Enter amount receieved:"), "Should print monet received prompt.");
    }

    @Test
    void testWrongItemIDOutput() {
        provideInput(String.join(System.lineSeparator(),
            "4",    // invalid item ID
            "",     // end scanning
            "0",    // discount ID
            "100"   // payment
        ));
        view.scanItems();

        String output = outContent.toString();
        assertTrue(output.contains("Item not found. Please try again or check the item ID."),
            "Should show error message for invalid item ID.");
    }

    @Test
    void testDatabaseOfflineMessage() {
        provideInput(String.join(System.lineSeparator(),
            "404",    // item triggeres db error simulation
            "",     // end scanning
            "0",    // discount ID
            "100"   // payment
        ));
        view.scanItems();

        String output = outContent.toString();
        assertTrue(output.contains("Unable to connect to inventory database. Please try again later."),
            "Should show error message for offline DB.");
    }

}
