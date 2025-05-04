package main.java.se.kth.iv1350.pos.startup;

import main.java.se.kth.iv1350.pos.controller.Controller;
import main.java.se.kth.iv1350.pos.view.View;

/**
 * The entry point of the application. Initializes the controller and view,
 * and starts the interaction by simulating a sale.
 */

public class Main {

    /**
     * Starts the application by creating the controller and view,
     * then initiating the item scanning process.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Controller contr = new Controller(); 
        View view = new View(contr);

        view.scanItems();
    }
}
