package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import other.Medicine;
import other.Order;
import other.Patient;
import user.ManagerStaff;
import user.Staff;

@FunctionalInterface
interface StaffFilter {
    abstract boolean test(Staff s);
}

public class PharmacyShop {

    public static final String CREATE_STAFF = "CREATE_STAFF";
    public static final String CREATE_PATIENT = "CREATE_PATIENT";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";

    private String shopName;
    private ArrayList<Medicine> inventory;
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders;
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

    public void seedDefaulfAdmin() {

    };

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
        if (staff == null) {
            System.out.println("Staff cannot be null");
            return;
        }
        // staffs.add(staff);
        // staffCount++;
        // System.out.println("Staff " + staff.getFullname() + " added successfully");
        // }

    }

    public void createPatient(Patient patient) {
        if (patient == null) {
            System.out.println("Patient cannot be null");
            return;
        }
        patients.add(patient);
        System.out.println("Patient " + patient.getName() + " added successfully");
    }

    public void createMenuItem(Medicine medicine) {
        if (medicine == null) {
            System.out.println("Medicine cannot be null");
            return;
        }
        inventory.add(medicine);
        medicineCount++;
        System.out.println("Medicine " + medicine.getName() + " added successfully");
    }

    public void setMenuItemAvailability(Medicine medicine, boolean isAvailable) {
        if (medicine == null) {
            System.out.println("Medicine cannot be null");
            return;
        }
        for (Medicine item : inventory) {
            if (item.getName().equals(medicine.getName())) {
                System.out.println("Medicine " + medicine.getName() + " availability set to " + isAvailable);
                return;
            }
        }
        System.out.println("Medicine " + medicine.getName() + " not found in inventory");
    }

    public void createOrder(Patient patient) {
        if (patient == null) {
            System.out.println("Patient cannot be null");
            return;
        }
        if (inventory.isEmpty()) {
            System.out.println("No medicines available");
            return;
        }
        System.out.println("Order created for patient: " + patient.getName());
        checkMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter medicine for " + patient.getName() + ":");
        String requireMedicine = scanner.nextLine();
        System.out.println("Enter quantity for: " + requireMedicine);
        int quantity = scanner.nextInt();
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0");
            return;
        }
        System.out.println("Creating order...");
    }

    public void viewCustomers() {
        if (patients.isEmpty()) {
            System.out.println("No patients found");
            return;
        }
        System.out.println("=== Patients ===");
        for (Patient p : patients) {
            System.out.println("- " + p.getName() + " | Symptom: " + p.getSymptom() + " | Age: " + p.getAge());
        }
    }

    public void updateOrderStatus() {
        if (orders.isEmpty()) {
            System.out.println("No orders found");
            return;
        }
        System.out.println("=== Orders ===");
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() + " | Total: " + o.getTotal() + " | Paid: " + o.isPaid());
        }
    }

    public void viewOrder(Order order) {
        if (order == null) {
            System.out.println("Order cannot be null");
            return;
        }
        System.out.println("=== Order Details ===");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Total: " + order.getTotal());
        System.out.println("Paid: " + order.isPaid());
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
        // Staff staff = new Pharmacist(
        // "Sokha",
        // "P001",
        // "012345678",
        // "sokha123",
        // "Pharmacist",
        // true,
        // "soksok",
        // 500.0,
        // "sokha@gmail.com");

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
        // anonymous class but lamda expression
        Comparator<Medicine> comparator = new Comparator<Medicine>() {
            // (a,b) -> Double.compare(a.getPrice(), b.getPrice());
            @Override
            public int compare(Medicine a, Medicine b) {
                return Double.compare(a.getPrice(), b.getPrice());
            }
        };
        // anonymous class
        // the purpose is to create class without having to create a seperate file like
        // .java file
        for (Staff p : staffs) {
            System.out.println(p.getUsername() + " can CREATE_ORDER? "
                    + p.can(PharmacyShop.CREATE_ORDER));
            System.out.println(p.getUsername() + " can CREATE_MENU_ITEM? "
                    + p.can(PharmacyShop.CREATE_MENU_ITEM));
            System.out.println(p.getUsername() + " can VIEW_ORDERS? "
                    + p.can(PharmacyShop.VIEW_ORDERS));
            System.out.println(p.getUsername() + " can VIEW_CUSTOMERS? "
                    + p.can(PharmacyShop.VIEW_CUSTOMERS));
        } // Abstraction

        StaffFilter filter = new StaffFilter() {
            public boolean test(Staff s) {
                return s.isActive();
            }
        };

        // StaffFilter filter = s -> s.isActive();
        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println(s.getFullname());
            }
        }
        // StaffFilter managerFilter = s -> s.getPosition().equals("manager");
        // StaffFilter phStaffFilter = s -> s.getPosition().equals("pharmacist");
        // active staff
        staffs.stream()
                .filter(s -> s.isActive())
                .forEach(s -> System.out.println(s.getFullname()));

        // // managers
        // staffs.stream()
        // .filter(s -> s.getPosition().equals("Pharmacist"))
        // .forEach(s -> System.out.println(s.getFullname()));
        // // pharmacists
        // staffs.stream()
        // .filter(s -> s.getPosition().equals("Manager"))
        // .forEach(s -> System.out.println(s.getFullname()));
    }
}
