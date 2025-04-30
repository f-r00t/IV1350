package controller;

import integration.AccountingHandler;
import integration.DiscountHandler;
import integration.InventoryHandler;
import integration.ReceiptPrinter;
import integration.ItemDTO;
import model.CashRegister;
import model.Sale;
import model.Receipt;

public class Controller {
    InventoryHandler inventoryHandler;
    AccountingHandler accountingHandler;
    ReceiptPrinter receiptPrinter;
    DiscountHandler discountHandler;
    CashRegister cashRegister;
    private Sale currentSale;

    public Controller() {
        inventoryHandler = new InventoryHandler();
        accountingHandler = new AccountingHandler();
        receiptPrinter = new ReceiptPrinter();
        discountHandler = new DiscountHandler();
        cashRegister = new CashRegister();
    }

    public ItemDTO scanItem(int identifier) {
        return scanItem(identifier, 1);
    }

    public ItemDTO scanItem(int identifier, int quantity) {
        ItemDTO item = inventoryHandler.getItem(identifier);
        this.currentSale.addItem(item, quantity);
        return item;
    }

    public float getTotalPrice() {
        return currentSale.getTotalPrice();
    }

    public float getTotalVAT() {
        return currentSale.getTotalVAT();
    }
    
    public void addDiscount(int customerId) {
        float discount = discountHandler.getDiscount(customerId, this.currentSale);
        this.currentSale.applyDiscount(discount);
    }    

    public void startSale() {
        this.currentSale = new Sale();
    }

    public Receipt endSale(float amountPaid) {
        this.accountingHandler.registerSale(currentSale);
        this.cashRegister.addMoney(currentSale.getTotalPrice());
        this.inventoryHandler.updateInventory(currentSale.getItems());
        Receipt receipt = new Receipt(currentSale, amountPaid);
        this.receiptPrinter.printReciept(receipt); // or return to View
        this.currentSale = null;
        return receipt;
    }

}