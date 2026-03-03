package controller;

import java.util.ArrayList;

import other.Medicine;
import user.IStaff;
import user.ManagerStaff;
import user.Pharmacist;

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
    private ArrayList<IStaff> iStaffs; // Array of Objects
    private ArrayList<Medicine> inventory;  // Array of Objects
    private ArrayList<Pharmacist> pharmacists; // Array of Objects
    ManagerStaff manager; // Single Object
    // Primitives
    private Integer medicineCount = 0; // Counter
    private Integer pharmacistCount = 0 ; // Counter
    private Double price;
    private String password;

    public PharmacyShop(String shopName, String password) {
    this.shopName = shopName;
    this.password = password;
    this.inventory = new ArrayList<>();    
    this.pharmacists = new ArrayList<>();  
    this.medicineCount = 0;              
}

    public String getShopName() {
        return shopName;
    }

    public Integer getMedicineCount() {
        return medicineCount;
    }

    public void setShopName(String shopName, String newPassword) {
        if (this.password.equals(newPassword)) {
            this.shopName = shopName;
            System.out.println("Shop name updated successfully.");
        } else {
            System.out.println("Incorrect password. Shop name not updated.");
        }
    }


    

    // Required Search Method
    public void createStaff(Pharmacist pharmacist) {
        pharmacists.add(pharmacist);
        pharmacistCount++;
    }

    public void createMenuItem(Medicine medicine) {
        inventory.add(medicine);
        medicineCount++;
    }

    public void setMenuItemAvailability() {
    }

    public void createOrder() {
    }

    public void viewCustomers() {
    }

    public void viewOrders() {
    }

    public void updateOrderStatus() {
    }

}
