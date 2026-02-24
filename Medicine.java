import java.lang.reflect.Array;
import java.util.ArrayList;

public class Medicine {
    // NO private, NO getters/setters
    
    // Reference types
   private String name;
   private String batchId;

    // Primitive types
  
    private int quantity;
    private boolean requiresPrescription;
    private Double price;

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

    public Double getPrice() {
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

    public Medicine(String name, Double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.requiresPrescription = false;
    }

    // public Medicine(String name, String batchId, Double price,
    //         Integer quantity, Boolean> requiresPrescription) {
    //     this.name = name;
    //     this.batchId = null;
    //     this.price = price;
    //     this.quantity = quantity;
    //     this.requiresPrescription = requiresPrescription;
    // }

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + "}";
    }
}
