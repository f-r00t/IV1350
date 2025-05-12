package main.java.se.kth.iv1350.pos.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.platform.reporting.shadow.org.opentest4j.reporting.events.core.Data;

import main.java.se.kth.iv1350.pos.integration.AccountingHandler;
import main.java.se.kth.iv1350.pos.integration.DatabaseOfflineException;
import main.java.se.kth.iv1350.pos.integration.DiscountHandler;
import main.java.se.kth.iv1350.pos.integration.InventoryHandler;
import main.java.se.kth.iv1350.pos.integration.ItemDTO;
import main.java.se.kth.iv1350.pos.integration.ItemNotFoundException;
import main.java.se.kth.iv1350.pos.integration.ReceiptPrinter;
import main.java.se.kth.iv1350.pos.model.CashRegister;
import main.java.se.kth.iv1350.pos.model.Receipt;
import main.java.se.kth.iv1350.pos.model.RevenueObserver;
import main.java.se.kth.iv1350.pos.model.Sale;
import main.java.se.kth.iv1350.pos.controller.ScanItemException;

/**
 * Controller class, handles communication between view and model.
 */

public class Controller {
    InventoryHandler inventoryHandler;
    AccountingHandler accountingHandler;
    ReceiptPrinter receiptPrinter;
    DiscountHandler discountHandler;
    CashRegister cashRegister;
    private Sale currentSale;
    private ArrayList<RevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Constructs and initializes the {@code Controller}.
     */

    public Controller() {
        inventoryHandler = new InventoryHandler();
        accountingHandler = new AccountingHandler();
        receiptPrinter = new ReceiptPrinter();
        discountHandler = new DiscountHandler();
        cashRegister = new CashRegister();
    }

    public ItemDTO scanItem(int identifier) throws ScanItemException{
        return scanItem(identifier, 1);
    }

    /**
     * Scans an item and adds it to the current sale.
     *
     * @param identifier the ID for the scanned item
     * @param quantity the quantity of the scannned item
     * @return the scanned item
     */

    public ItemDTO scanItem(int identifier, int quantity) throws ScanItemException {
        try {
            ItemDTO item = inventoryHandler.getItem(identifier);
            this.currentSale.addItem(item, quantity);
            return item;
        } catch (ItemNotFoundException e) {
            throw new ScanItemException(e);
        } catch (DatabaseOfflineException e) {
            throw new ScanItemException(e);
        }
    }

    /**
     * @return The total price (excluding VAT)
     */

    public float getTotalPrice() {
        return currentSale.getTotalPrice();
    }

    /**
     * @return The total VAT
     */

    public float getTotalVAT() {
        return currentSale.getTotalVAT();
    }
    
    /**
     * Applies a discount to the current sale if the specified customer is eligible.
     *
     * @param customerID the ID of the current customer
     */

    public void addDiscount(int customerId) {
        float discount = discountHandler.getDiscount(customerId);
        this.currentSale.applyDiscount(discount);
    }   

    /**
     * Starts a new sale by creating a new Sale instance
     */

    public void startSale() {
        this.currentSale = new Sale();
        cashRegister.addRevenueObservers(revenueObservers);
    }

    /**
     * Ends the sale by updating the external systems (inventory and accounting), printing a receipt and clearing the current sale
     *
     * @param amountPaid The amount paid by the customer.
     * @return A receipt representing the completed transaction.
     */

    public Receipt endSale(float amountPaid) {
        this.accountingHandler.registerSale(currentSale);
        this.cashRegister.addMoney(currentSale.getTotalPrice());
        this.inventoryHandler.updateInventory(currentSale.getItems());
        Receipt receipt = new Receipt(currentSale, amountPaid);
        this.receiptPrinter.printReciept(receipt); // or return to View
        this.currentSale = null;
        return receipt;
    }

    public void addRevenueObserver(RevenueObserver obs) {
        revenueObservers.add(obs);
    }

}