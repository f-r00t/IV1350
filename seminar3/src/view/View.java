package view;

import java.util.Map;

import controller.Controller;
import integration.ItemDTO;
import model.Sale;
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
            if (itemInput == "") {
                activeSale = false;
                System.out.printf("Total cost is %.0f SEK %n%nEnter amount receieved: %n", controller.currentSale.getTotalPrice());
                String paymentInput = scanner.nextLine();
                float amountReceived = Float.valueOf(paymentInput);
                float change = controller.makePayment(amountReceived);
                System.out.printf("Please return %.0f kr to the customer. %n", change);
                printReciept(controller.currentSale);
                controller.endSale();
            }
            else {
                int itemID = Integer.valueOf(itemInput);
                ItemDTO currentItem = controller.scanItem(itemID);
                printItem(currentItem);
            }
        }
    }
    
    private void printReciept(Sale currSale) {

        System.out.println("------------- Begin Receipt -------------");
        for(Map.Entry<ItemDTO, Integer> entry : currSale.getItems().entrySet()) {
            ItemDTO thisItem = entry.getKey();
            Integer thisQuantity = entry.getValue();
            System.out.printf(thisQuantity + "x " + thisItem.getName() + " Total price: %.2f SEK%n", thisItem.getPrice() * thisQuantity);
        }
        System.out.println("------------- End Receipt ---------------");
    }

    private void printItem(ItemDTO item) {
        
        System.out.println("");
        System.out.println("Add 1 item with item id " + item.getIdentifier() + " :");
        System.out.println("Item ID : " + item.getIdentifier());
        System.out.println("Item name : " + item.getName());
        System.out.println("Item cost : " + item.getPrice() + " SEK");
        System.out.println("VAT : " + item.getTaxRate() * 100 + "%");
        System.out.println("Item description: " + item.getDescription());
        System.out.println("");
    
        System.out.printf("Total cost: %.2f SEK%n", controller.currentSale.getTotalPrice());
        System.out.printf("Total VAT: %.2f SEK%n", controller.currentSale.getTotalVAT());
        System.out.println("");

        // 12 Add 1 item with item id abc123 :
        // 13 Item ID : abc123
        // 14 Item name : BigWheel Oatmeal
        // 15 Item cost : 29:90 SEK
        // 16 VAT : 6%
        // 17 Item description : BigWheel Oatmeal 500 ml , whole grain oats ,
        // 18 high fiber , gluten free
        // 19
        // 20 Total cost ( incl VAT ): 59:80 SEK
        // 21 Total VAT : 3:38 SEK

    }
}
