package main.java.se.kth.iv1350.pos.integration;

/**
 * Represents a Data Transfer Object (DTO) for an item in the inventory.
 * Contains basic information such as name, price, tax rate, and description.
 */

public class ItemDTO {
    private int identifier;
    private String name;
    private float price;
    private float taxRate;
    private String description;

    /**
     * Constructs an {@code ItemDTO}.
     * Holds basic information about an item.
     * 
     * @param identifier Unique identifier for the item
     * @param name Name of the item
     * @param price Price of the item (exluding VAT)
     * @param taxRate VAT rate of item
     * @param description Description of item
     */

    public ItemDTO(int identifier, String name, float price, float taxRate, String description) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate; 
        this.description = description;
    }

    /** 
     * @return The price of the item (excluding VAT).
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return The VAT rate of the item.
     */
    public float getTaxRate() {
        return taxRate;
    }

    /**
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The item ID.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @return The item description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Prints the details of the item to the console.
     * Mainly intended for debugging or demonstration purposes.
     */
    public void print() {
        
        System.out.println("");
        System.out.println("Item ID : " + this.getIdentifier());
        System.out.println("Item name : " + this.getName());
        System.out.println("Item cost : " + this.getPrice() + " SEK");
        System.out.println("VAT : " + this.getTaxRate() * 100 + "%");
        System.out.println("Item description: " + this.getDescription());
        System.out.println("");
    
    }
}
