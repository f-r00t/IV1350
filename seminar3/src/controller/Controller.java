package controller;

import integration.AccountingHandler;
import integration.DiscountHandler;
import integration.InventoryHandler;
import integration.ReceiptPrinter;

public class Controller {
    InventoryHandler inventoryHandler;
    AccountingHandler accountingHandler;
    ReceiptPrinter receiptPrinter;
    DiscountHandler discountHandler;

    public Controller() {
        inventoryHandler = new InventoryHandler();
        accountingHandler = new AccountingHandler();
        receiptPrinter = new ReceiptPrinter();
        discountHandler = new DiscountHandler();
    }
}