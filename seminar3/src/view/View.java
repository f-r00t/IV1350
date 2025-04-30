package view;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;

import java.util.Scanner;


public class View {

    private Controller controller;
    private Boolean activeSale;
    Scanner scanner = new Scanner(System.in);

    public View(Controller contr) {

        this.controller = contr;
        this.activeSale = true;
        controller.startSale();

    }

    public void scanItems() {

        while (activeSale) {

            System.out.print("Enter item identifier: ");
            String itemInput = scanner.nextLine();
            if (itemInput.isEmpty()) {
                activeSale = false;
                System.out.printf("Total cost is %.0f SEK %n%nEnter amount receieved: %n", controller.getTotalPrice());
                String paymentInput = scanner.nextLine();
                float amountReceived = Float.valueOf(paymentInput);
                Receipt thisReceipt = controller.endSale(amountReceived);
                thisReceipt.print();
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
