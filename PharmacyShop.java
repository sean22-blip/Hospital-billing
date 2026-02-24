import java.util.ArrayList;

public class PharmacyShop {

    public static final String CREATE_STAFF = "CREATE_STAFF";
    public static final String CREATE_CUSTOMER = "CREATE_CUSTOMER";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";
    // NO private, NO getters/setters

    // Reference types
    private String shopName;
    private Medicine inventory; // Array of Objects
    
    // Primitives
   private Integer medicineCount; // Counter
   private Integer nextOrderId;
    private Double price;

   
    public PharmacyShop(String shopName, Medicine inventory, Integer medicineCount, Integer nextOrderId) {
    this.shopName = shopName;
    this.inventory = inventory;
    this.medicineCount = medicineCount;
    this.nextOrderId = nextOrderId;
}

    public String getShopName() {
    return shopName;
}

   public void setShopName(String shopName) {
    this.shopName = shopName;
   }

   public  Medicine getInventory() {
    return inventory;
   }

   public void setInventory(Medicine inventory) {
    this.inventory = inventory;
   }

   public Integer getMedicineCount() {
    return medicineCount;
   }

   public void setMedicineCount(Integer medicineCount) {
    this.medicineCount = medicineCount;
   }

   public Integer getNextOrderId() {
    return nextOrderId;
   }

   public void setNextOrderId(Integer nextOrderId) {
    this.nextOrderId = nextOrderId;
   }

    public void addMedicine(ArrayList<String> name, ArrayList<Double> price) {
      
    }
    public void setPrice(ArrayList<Double> price) {
        this.price = price.get(0);
    }

    // Required Search Method
    public Medicine findMedicineByName(String name) {
        for (int i = 0; i < medicineCount; i++) {
            // Null safety check
            if (inventory != null) {
                if (inventory.getName().equals(name)) { // Use .equals()
                    return inventory;
                }
            }
        }
        return null; // Return null if not found
    }

    // Required: createOrder
    // public Order createOrder(Patient patient, Pharmacist pharmacist) {
    //     // Create a new order with capacity for 5 items
    //    Order newOrder = new Order(nextOrderId, patient, pharmacist, 5);
    //     nextOrderId++;
    //     return newOrder;
    // }
    
    // public void printMenu() {
    //     System.out.println("--- " + shopName + " Menu ---");
    //     for(int i=0; i< medicineCount; i++) {
    //          System.out.println(inventory[i].toString());
    //     }
    // }

    public String printMenu() {
        return "PharmacyShop [inventory=" + inventory + "]";
    }
    
}
