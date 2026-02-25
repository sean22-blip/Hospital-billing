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
    private ArrayList<Medicine> inventory; // Array of Objects

    // Primitives
    private Integer medicineCount; // Counter
    private Integer nextOrderId;
    private Double price;

    public PharmacyShop(String shopName, ArrayList<Medicine> inventory, Integer medicineCount, Integer nextOrderId) {
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

    public void addMedicine(String name, Double price, String batchId, Integer quantity, Boolean requiresPrescription) {
        inventory.add(new Medicine(name, batchId, quantity, requiresPrescription, price));
        medicineCount++;
    }

    public Integer getMedicineCount() {
        return medicineCount;
    }

    public Integer getNextOrderId() {
        return nextOrderId;
    }

    public void setNextOrderId(Integer nextOrderId) {
        this.nextOrderId = nextOrderId;
    }

    // Required Search Method
    public Medicine findMedicineByName(String name) {
        return null; // Return null if not found
    }

    @Override
    public String toString() {
        return "PharmacyShop [\nShopName: " + shopName + ", \nInventory: " + inventory + ", \nMedicineCount: " + medicineCount
                + ", \nNextOrderId: " + nextOrderId + ", \nPrice: " + price + "]";
    }

    

}
