public class Order {
    // References to Objects
  private  Patient patient;
   private Pharmacist pharmacist;
    
    // SNAPSHOT DATA (Arrays to store history)
   private String[] itemNames;
   private double[] snapshotPrices; // Primitive array stores price at moment of buy
    
    public Patient getPatient() {
    return patient;
}

   public void setPatient(Patient patient) {
    this.patient = patient;
   }

   public Pharmacist getPharmacist() {
    return pharmacist;
   }

//    public void setPharmacist(Pharmacist pharmacist) {
//     this.pharmacist = pharmacist;
//    }

   public String[] getItemNames() {
    return itemNames;
   }

//    public void setItemNames(String[] itemNames) {
//     this.itemNames = itemNames;
//    }

   public double[] getSnapshotPrices() {
    return snapshotPrices;
   }

//    public void setSnapshotPrices(double[] snapshotPrices) {
//     this.snapshotPrices = snapshotPrices;
//    }

   public int getItemCount() {
    return itemCount;
   }

//    public void setItemCount(int itemCount) {
//     this.itemCount = itemCount;
//    }

   public int getOrderId() {
    return orderId;
   }

//    public void setOrderId(int orderId) {
//     this.orderId = orderId;
//    }

   public boolean isPaid() {
    return isPaid;
   }

//    public void setPaid(boolean isPaid) {
//     this.isPaid = isPaid;
//    }

   // Primitives
   private int itemCount;
   private int orderId;
   private boolean isPaid;

    public Order(int orderId, Patient patient, Pharmacist pharmacist, int capacity) {
        this.orderId = orderId;
        this.patient = patient;
        this.pharmacist = pharmacist;
        
        // Initialize arrays
        this.itemNames = new String[capacity];
        this.snapshotPrices = new double[capacity];
        this.itemCount = 0;
        this.isPaid = false;
    }

    // This method saves the SNAPSHOT
    public void addItem(Medicine med) {
        if (itemCount < itemNames.length) {
            this.itemNames[itemCount] = med.name;
            
            // KEY MOMENT: Copy the primitive value!
            // If med.price changes later, this number in the array will NOT change.
            this.snapshotPrices[itemCount] = med.price;
            
            this.itemCount++;
        }
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total = total + snapshotPrices[i];
        }
        return total;
    }

    public String receipt() {
        return "Receipt #" + orderId + " | Sold By: " + pharmacist.name + " | Total: $" + getTotal();
    }
}
