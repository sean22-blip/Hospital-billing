import java.util.ArrayList;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class Order {
    // References to Objects
  private Patient patient;
   private Pharmacist pharmacist;
     // Primitives
   private Integer itemCount;
   private Integer orderId;
   private Boolean isPaid;

    
    // SNAPSHOT DATA (Arrays to store history)
   private String itemNames;
   private Double snapshotPrices; // Primitive array stores price at moment of buy
    
     public Order(Patient patient, Pharmacist pharmacist, String itemNames,
            Double snapshotPrices, Integer itemCount, Integer orderId,
            Boolean isPaid) {
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.itemNames = itemNames;
        this.snapshotPrices = snapshotPrices;
        this.itemCount = itemCount;
        this.orderId = orderId;
        this.isPaid = isPaid;
    }

    public Patient getPatient() {
    return patient;
}

   public Pharmacist getPharmacist() {
    return pharmacist;
   }

   public String getItemNames() {
    return itemNames;
   }

   public Double getSnapshotPrices() {
    return snapshotPrices;
   }



   public Integer getItemCount() {
    return itemCount;
   }

   public void createOrder(Patient patient, Pharmacist pharmacist) {
    
    }

   public Integer getOrderId() {
    return orderId;
   }


   public Boolean isPaid() {
    return isPaid;
   }


    public double getTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total = total + snapshotPrices;
        }
        return total;
    }

    public String receipt() {
        return "Receipt #" + orderId + " | Sold By: " + pharmacist.getFullname() + " | Total: $" + getTotal();
    }

    public Boolean getIsPaid() {
        return isPaid;
    }
}
