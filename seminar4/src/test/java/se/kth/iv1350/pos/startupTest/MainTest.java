package test.java.se.kth.iv1350.pos.startupTest;


import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.startup.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void testMainCorrectlyStartsOutput() throws Exception {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception ignored) {
            }
        });

        Thread.sleep(100); 

        future.cancel(true);
        executor.shutdownNow();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("Enter item identifier:"), "Expected prompt not found in output.");
    }
}
