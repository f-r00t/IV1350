package integration;

public class ItemDTO {
    private int identifier;
    private String name;
    private float price;
    private float taxRate;
    private String description;

    public ItemDTO(int identifier, String name, float price, float taxRate, String description) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate; 
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public String getName() {
        return name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }
}
