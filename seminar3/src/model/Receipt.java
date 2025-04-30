package model;

import java.util.HashMap;
import java.util.Map;

import integration.ItemDTO;


public class Receipt {

    private long timestamp;
    private Sale sale;
    private float amountPaid;
    private float change;

    public Receipt(Sale thisSale, float amountPaid) {

        this.sale = thisSale;
        this.timestamp = System.currentTimeMillis();
        this.amountPaid = amountPaid;
        this.change = amountPaid - sale.getTotalPrice();

    }

    public float getChange(){
        return change;
    }

    public void print() {
        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (timestamp));
        System.out.println("------------- Begin Receipt -------------");
        for(Map.Entry<ItemDTO, Integer> entry : sale.getItems().entrySet()) {
            ItemDTO thisItem = entry.getKey();
            Integer thisQuantity = entry.getValue();
            System.out.printf(thisQuantity + "x " + thisItem.getName() + " Total price: %.2f SEK%n", thisItem.getPrice() * thisQuantity);
        }
        System.out.printf("Total price: %.2f SEK%n", sale.getTotalPrice());
        System.out.printf("Total VAT: %.2f SEK%n", sale.getTotalVAT());
        System.out.printf("Cash: %.2f SEK%n", amountPaid);
        System.out.printf("Change: %.0f SEK%n", change);
        System.out.println("Time of sale: " + date);
        System.out.println("------------- End Receipt ---------------");
    }

}
