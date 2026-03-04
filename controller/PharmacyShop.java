package controller;

import java.util.ArrayList;
import java.util.Scanner;

import other.Medicine;
import other.Patient;
import user.ManagerStaff;
import user.Pharmacist;
import user.Staff;
import other.Order;

public class PharmacyShop {

    public static final String CREATE_STAFF = "CREATE_STAFF";
    public static final String CREATE_PATIENT = "CREATE_PATIENT";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";
    // NO private, NO getters/setters

    // Reference types
    private String shopName;
    private ArrayList<Medicine> inventory;  // Array of Objects
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders; // Single Object
    // Primitives
    private Integer medicineCount = 0; // Counter
    private Integer pharmacistCount = 0 ; // Counter
    private Double price;
    private String password;

    public PharmacyShop(String shopName, String password) {
    this.shopName = shopName;
    this.password = password;
    this.inventory = new ArrayList<>();    
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

    public void setMenuItemAvailability(Medicine medicine, boolean isAvailable) {
        for (Medicine item : inventory) {
            if (item.getName().equals(medicine.getName())) {
                System.out.println("Medicine " + medicine.getName() + " availability set to " + isAvailable);
                return;
            }
        }
    }
    public void createPatient(Patient patient) {
        patients.add(patient);
    }

    public void createOrder(Patient patient, Staff staff) {
     // Implementation for creating an order
     System.out.println("Order created for patient: " + patient.getName() + " by staff: " + staff.getFullname());
    checkMenu();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter medicine for" + patient.getName() + ":");
    String requireMedicine = scanner.nextLine();
    System.out.println("Enter quantity for: " + requireMedicine);
    int quantity = scanner.nextInt();
    order.getTotal(requireMedicine, quantity);
    }

    public void viewCustomers() {
    }

    public void updateOrderStatus() {
    }
    public void checkMenu(){
        if(inventory.isEmpty()){
            System.out.println("No medicines available in the inventory.");
        } else {
            System.out.println("Available Medicines:");
            for (Medicine medicine : inventory) {
                System.out.println("- " + medicine.getName() + " (Price: " + medicine.getPrice() + ", Quantity: " + medicine.getQuantity() + ")");
            }
        }
    }

}
