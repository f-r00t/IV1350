package main.java.se.kth.iv1350.pos.view;

import main.java.se.kth.iv1350.pos.controller.Controller;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.model.Receipt;

import java.util.Scanner;


/**
 * View class, handles interaction with the user.
 */

public class View {

    private Controller controller;
    private Boolean activeSale;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs and initializes the {@code View} aswell as starts a new sale.
     * @param contr The controller to interact with application logic.
     */

    public View(Controller contr) {

        this.controller = contr;
        this.activeSale = true;
        controller.startSale();

    }

    /**
     * Simulates the process of scanning items via user input.
     * The user enters item identifiers one at a time. An empty entry finalize the sale, asks for payment and prints a receipt.
     */

    public void scanItems() {

        while (activeSale) {

            System.out.print("Enter item identifier: ");
            String itemInput = scanner.nextLine();
            if (itemInput.isEmpty()) {
                activeSale = false;
                System.out.printf("Total cost is %.0f SEK %n%nEnter amount receieved: %n", controller.getTotalPrice());
                String paymentInput = scanner.nextLine();
                float amountReceived = Float.valueOf(paymentInput);
                controller.endSale(amountReceived);
            }
            else {
                int itemID = Integer.valueOf(itemInput);
                ItemDTO thisItem = controller.scanItem(itemID);
                System.out.println("Add 1 item with item id " + thisItem.getIdentifier() + " :");
                thisItem.print();
                System.out.printf("Total cost: %.2f SEK%n", controller.getTotalPrice());
                System.out.printf("Total VAT: %.2f SEK%n", controller.getTotalVAT());
            }
        }
    }

}
