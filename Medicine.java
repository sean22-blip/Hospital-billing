import java.lang.reflect.Array;
import java.util.ArrayList;

public class Medicine {
    // NO private, NO getters/setters
    
    // Reference types
   private String name;
   private String batchId;

    // Primitive types
  
    private Integer quantity;
    private Boolean requiresPrescription;
    private Double price;


      

    public Medicine(String name, String batchId, Integer quantity, Boolean requiresPrescription, Double price) {
        this.name = name;
        this.batchId = batchId;
        this.quantity = quantity;
        this.requiresPrescription = requiresPrescription;
        this.price = price;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    // public void setQuantity(int quantity) {
    //     this.quantity = quantity;
    // }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }   

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + "}";
    }

  
}
