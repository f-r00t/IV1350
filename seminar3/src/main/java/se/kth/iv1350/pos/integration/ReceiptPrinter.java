package main.java.se.kth.iv1350.pos.integration;

import java.util.Map;

import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.model.Sale;


/**
 * Handles the external system receipt printer.
 * Contains methods for printing the receipt.
 */
public class ReceiptPrinter {
    /**
     * Prints the receipt.
     * @param receipt The receipt to be printed
     */
    public void printReciept(Receipt receipt) {

        Sale sale = receipt.getSale();
        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (receipt.getTimestamp()));
        System.out.println("------------- Begin Receipt -------------");
        for(Map.Entry<ItemDTO, Integer> entry : sale.getItems().entrySet()) {
            ItemDTO thisItem = entry.getKey();
            Integer thisQuantity = entry.getValue();
            System.out.printf("%dx %-25s %2dx %6.2f - %6.2f SEK%n",
                thisQuantity,
                thisItem.getName(),
                thisQuantity,
                thisItem.getPrice(),
                thisItem.getPrice() * thisQuantity
            );

        }
        System.out.println("-----------------------------------------");
        System.out.printf("Total price: %.2f SEK%n", sale.getTotalPrice());
        System.out.printf("Total VAT: %.2f SEK%n", sale.getTotalVAT());
        System.out.println(" ");
        System.out.printf("Cash: %.2f SEK%n", receipt.getAmountPaid());
        System.out.printf("Change: %.0f SEK%n", receipt.getChange());
        System.out.println(" ");
        System.out.println("Time of sale: " + date);
        System.out.println("------------- End Receipt ---------------");
        
    }
}
