package main.java.se.kth.iv1350.pos.model;

import java.util.Map;

import main.java.se.kth.iv1350.pos.integration.ItemDTO;

/**
 * Represents a receipt for a completed sale. Contains information about the sale,
 * the amount paid, change given, and timestamp of the transaction.
 */
public class Receipt {

    private long timestamp;
    private Sale sale;
    private float amountPaid;
    private float change;

    /**
     * Constructs a {@code Receipt} for a completed sale.
     *
     * @param thisSale    The {@code Sale} object representing the completed transaction.
     * @param amountPaid  The amount of money paid by the customer.
     */
    public Receipt(Sale thisSale, float amountPaid) {
        this.sale = thisSale;
        this.timestamp = System.currentTimeMillis();
        this.amountPaid = amountPaid;
        this.change = amountPaid - sale.getTotalPrice();
    }

    /**
     * Returns the amount of change to be given to the customer.
     *
     * @return The change as a float.
     */
    public float getChange() {
        return change;
    }

    /**
     * Returns the time of sale.
     *
     * @return The time of sale represented in a UNIX timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the sale the receipt represents.
     *
     * @return The sale the receipt represents.
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Returns the amount the customer paid.
     *
     * @return The amount paid by the customer.
     */
    public float getAmountPaid() {
        return amountPaid;
    }

}
