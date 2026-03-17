package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import other.Medicine;
import other.Order;
import other.Patient;
import user.ManagerStaff;
import user.Pharmacist;
import user.Staff;
import controller.ActiveStaffFilter;
import controller.StaffFilter;

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
    private ArrayList<Medicine> inventory; // Array of Objects
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders; // Single Object
    // Primitives
    private int medicineCount = 0; // Counter
    private int staffCount = 0; // Counter
    private double price;
    private String password;
    private String address;

    public PharmacyShop(String shopName, String password, String address) {
        this.shopName = shopName;
        this.password = password;
        this.address = address;
        this.inventory = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.medicineCount = 0;

    }

    public String getShopName() {
        return shopName;
    }

    public int getMedicineCount() {
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
    public void createStaff(Staff staff) {
        // Staff s = new Pharmacist();
    }

    public void createPatient(Patient patient) {
        patients.add(patient);
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

    public void createOrder(Patient patient) {
        // Implementation for creating an order]

        System.out.println("Order created for patient: " + patient.getName());
        checkMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter medicine for" + patient.getName() + ":");
        String requireMedicine = scanner.nextLine();
        System.out.println("Enter quantity for: " + requireMedicine);
        int quantity = scanner.nextInt();
        System.out.println("Creating order...");
    }

    public void viewCustomers() {
    }

    public void printStaffs() {
        for (Staff s : staffs) {
            System.out.println(s.getFullname() + " - " + s.getPosition());
        }
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void updateOrderStatus() {

    }

    public void viewOrder(Order order) {

    }

    public void checkMenu() {
        if (inventory.isEmpty()) {
            System.out.println("No medicines available in the inventory.");
        } else {
            System.out.println("Available Medicines:");
            for (Medicine medicine : inventory) {
                System.out.println("- " + medicine.getName() + " (Price: " + medicine.getPrice() + ", Quantity: "
                        + medicine.getQuantity() + ")");
            }
        }
    }

    public void setPeople() {
        Patient p1 = new Patient("KabekSloy", "Autisitc", 67, false);
        Patient p2 = new Patient("Iseann", "Autisitc", 67, true);
        Staff staff = new Pharmacist(
                "Sokha",
                "P001",
                "012345678",
                "sokha123",
                "Pharmacist",
                true,
                "soksok",
                500.0,
                "sokha@gmail.com");

        Staff manager = new ManagerStaff(
                "Dara",
                "M001",
                "098765432",
                "dara123",
                "Manager",
                true,
                "Admin",
                500.0,
                "dara@gmail.com", 100);
    }

    public void permissionTest() {
        //anonymous class but lamda expression 
        Comparator<Medicine> comparator =  new Comparator<Medicine>() {
        // (a,b) ->  Double.compare(a.getPrice(), b.getPrice());
            public int compare(Medicine a, Medicine b){
                return Double.compare(a.getPrice(), b.getPrice());
            }
        };
        //  anonymous class 
            //the purpose is to create class without having to create a seperate file like 
            //.java file
        for (Staff p : staffs) {
        System.out.println(p.getUsername() + " can CREATE_ORDER? " +
        p.can(PharmacyShop.CREATE_ORDER));
        System.out.println(p.getUsername() + " can CREATE_MENU_ITEM? " +
        p.can(PharmacyShop.CREATE_MENU_ITEM));
        System.out.println(p.getUsername() + " can VIEW_ORDERS? " +
        p.can(PharmacyShop.VIEW_ORDERS));
        System.out.println(p.getUsername() + " can VIEW_CUSTOMERS? " +
        p.can(PharmacyShop.VIEW_CUSTOMERS));
        }//Abstraction 
        StaffFilter filter = new StaffFilter() {
            @Override
            public boolean test(Staff s) {
                return s.isActive();
            }
        };
        // StaffFilter filter = (Staff s)->s.isActive();
        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println(s.getFullname());
            }
        }
        staffs.stream()
            .filter(s->s.isActive())
            .forEach(s-> System.out.println(s.getFullname()));

    }//lamda expresssion
}
