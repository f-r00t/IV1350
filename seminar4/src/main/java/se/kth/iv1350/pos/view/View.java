package main.java.se.kth.iv1350.pos.view;

import main.java.se.kth.iv1350.pos.controller.Controller;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.util.TotalRevenueFileOutput;
import main.java.se.kth.iv1350.pos.controller.ScanItemException;
import main.java.se.kth.iv1350.pos.integration.DatabaseOfflineException;
import main.java.se.kth.iv1350.pos.integration.ItemNotFoundException;

import java.util.Scanner;


/**
 * View class, handles interaction with the user.
 */

public class View {

    private Controller controller;
    private Boolean activeSale;

    /**
     * Constructs and initializes the {@code View} aswell as starts a new sale.
     * @param contr The controller to interact with application logic.
     */

    public View(Controller contr) {

        this.controller = contr;
        contr.addRevenueObserver(new TotalRevenueView());
        contr.addRevenueObserver(new TotalRevenueFileOutput());
    }

    /**
     * Simulates the process of scanning items via user input.
     * The user enters item identifiers one at a time. An empty entry finalize the sale, asks for payment and prints a receipt.
     */

    public void scanItems() {
        Scanner scanner = new Scanner(System.in);
        this.activeSale = true;
        controller.startSale();

        while (activeSale) {

            System.out.print("Enter item identifier: ");
            String itemInput = scanner.nextLine();
            System.out.print("\n");
            if (itemInput.isEmpty()) {
                activeSale = false;
                System.out.printf("Enter customer ID for discount (or 0 to skip): %n");
                int discountID = Integer.valueOf(scanner.nextLine());
                if(discountID != 0) {
                    controller.addDiscount(discountID);
                }

                System.out.printf("Total cost is %.0f SEK %n%nEnter amount receieved: %n", controller.getTotalPrice());
                String paymentInput = scanner.nextLine();
                float amountReceived = Float.valueOf(paymentInput);
                controller.endSale(amountReceived);
            }
            else {
                String[] words = itemInput.split(" ");
                Integer itemID = Integer.parseInt(words[0]);
                Integer quantity = null;
                if (words.length == 2) {
                    quantity = Integer.parseInt(words[1]);
                }

                try {
                    ItemDTO thisItem = null;
                    if (quantity != null) {
                        thisItem = controller.scanItem(itemID, quantity);    
                    } else {
                        thisItem = controller.scanItem(itemID);
                    }
                    System.out.println("Add 1 item with item id " + thisItem.getIdentifier() + " :");
                    displayItemDetails(thisItem);
                    System.out.printf("Total cost: %.2f SEK%n", controller.getTotalPrice());
                    System.out.printf("Total VAT: %.2f SEK%n%n", controller.getTotalVAT());
                } catch (ScanItemException e) {
                    System.out.println(e.getMessage());
                }
        }}
    }
    /**
     * Prints information about an item.
     * @param item The item to be printed.
     */
    private void displayItemDetails(ItemDTO item) {
        System.out.println("\nItem ID: " + item.getIdentifier());
        System.out.println("Item name: " + item.getName());
        System.out.println("Item cost: " + item.getPrice() + " SEK");
        System.out.println("VAT: " + (item.getTaxRate() * 100) + "%");
        System.out.println("Item description: " + item.getDescription() + "\n");
    }

}
