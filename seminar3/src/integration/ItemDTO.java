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
