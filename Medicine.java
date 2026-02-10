public class Medicine {
    // NO private, NO getters/setters
    
    // Reference types
   private String name;
   private String batchId;

    // Primitive types
    double price;
    int quantity;
    boolean requiresPrescription;

    public String getName() {
        return name;
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public String getBatchId() {
        return batchId;
    }

    // public void setBatchId(String batchId) {
    //     this.batchId = batchId;
    // }

    public double getPrice() {
        return price;
    }

    // public void setPrice(double price) {
    //     this.price = price;
    // }

    public int getQuantity() {
        return quantity;
    }

    // public void setQuantity(int quantity) {
    //     this.quantity = quantity;
    // }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    // public void setRequiresPrescription(boolean requiresPrescription) {
    //     this.requiresPrescription = requiresPrescription;
    // }

    public Medicine(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.requiresPrescription = false;
        this.batchId = "BATCH-001"; // Default
    }

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + "}";
    }
}
