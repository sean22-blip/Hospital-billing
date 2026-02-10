public class PharmacyShop {
    // NO private, NO getters/setters

    // Reference types
    private String shopName;
    private Medicine[] inventory; // Array of Objects
    
    // Primitives
   private int medicineCount; // Counter
   private int nextOrderId;

    public String getShopName() {
    return shopName;
}

   public void setShopName(String shopName) {
    this.shopName = shopName;
   }

   public Medicine[] getInventory() {
    return inventory;
   }

   public void setInventory(Medicine[] inventory) {
    this.inventory = inventory;
   }

   public int getMedicineCount() {
    return medicineCount;
   }

   public void setMedicineCount(int medicineCount) {
    this.medicineCount = medicineCount;
   }

   public int getNextOrderId() {
    return nextOrderId;
   }

   public void setNextOrderId(int nextOrderId) {
    this.nextOrderId = nextOrderId;
   }

    public PharmacyShop(String shopName, int capacity) {
        this.shopName = shopName;
        this.inventory = new Medicine[capacity];
        this.medicineCount = 0;
        this.nextOrderId = 1;
    }

    public void addMedicine(String name, double price) {
        if (medicineCount < inventory.length) {
            Medicine newMed = new Medicine(name, price);
            this.inventory[medicineCount] = newMed;
            this.medicineCount++;
        }
    }

    // Required Search Method
    public Medicine findMedicineByName(String name) {
        for (int i = 0; i < medicineCount; i++) {
            // Null safety check
            if (inventory[i] != null) {
                if (inventory[i].name.equals(name)) { // Use .equals()
                    return inventory[i];
                }
            }
        }
        return null; // Return null if not found
    }

    // Required: createOrder
    public Order createOrder(Patient patient, Pharmacist pharmacist) {
        // Create a new order with capacity for 5 items
        Order newOrder = new Order(nextOrderId, patient, pharmacist, 5);
        nextOrderId++;
        return newOrder;
    }
    
    public void printMenu() {
        System.out.println("--- " + shopName + " Menu ---");
        for(int i=0; i<medicineCount; i++) {
             System.out.println(inventory[i].toString());
        }
    }
}
