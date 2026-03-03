package other;
import user.ManagerStaff;

public class Medicine {
    // NO private, NO getters/setters
    
    // Reference types
    ManagerStaff manager; // Single Object
   private String name;
   private String batchId;
   private String password;

    // Primitive types
  
    private Integer quantity;
    private Double price;


      

    public Medicine(String name, String batchId, Integer quantity, Double price) {
        this.name = name;
        this.batchId = batchId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBatchId() {
        return batchId;
    }

    public Double getPrice() {
        return price;
    }


    public Integer getQuantity() {
        return quantity;
    }
     
    public boolean isAvailable() {
        return quantity > 0;
    }
    public int getStock() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + "}";
    }

  
}
