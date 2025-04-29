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
    public Sale currentSale;

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
    
    public void addDiscount(int customerId) {
        float discount = discountHandler.getDiscount(customerId, this.currentSale);
        this.currentSale.applyDiscount(discount);
    }

    public float makePayment(float amountPaid) {
        Receipt currentReciept = new Receipt(currentSale);
        
        this.accountingHandler.registerSale(currentSale);
        this.receiptPrinter.printReciept(currentReciept);
        this.cashRegister.addMoney(currentSale.getTotalPrice());
        this.inventoryHandler.updateInventory(currentSale.getItems());

        float change = amountPaid - currentSale.getTotalPrice();
        
        return change;
    }
    

    public void startSale() {
        this.currentSale = new Sale();
    }

    public void endSale() {
        Sale thisSale = currentSale;
        currentSale = null;
    }

}