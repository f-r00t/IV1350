package test.java.se.kth.iv1350.pos.integrationTest;

import main.java.se.kth.iv1350.pos.integration.AccountingHandler;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.model.Sale;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AccountingHandlerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testAccountingHandlerPrintout() {

        AccountingHandler accountingHandler = new AccountingHandler();
        Sale sale = new Sale();

        ItemDTO apple = new ItemDTO(1, "Granny Smith Apple", 9.99f, 0.12f, "The freshest apple known to man.");
        sale.addItem(apple, 1);

        accountingHandler.registerSale(sale);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Sale registered in accounting system!"), 
                   "Output should confirm that the sale was registered.");
    }
}
