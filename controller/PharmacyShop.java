package controller;

import java.util.ArrayList;
import java.util.Scanner;
import other.Medicine;
import other.Order;
import other.Patient;
import user.ManagerStaff;
import user.Pharmacist;
import user.Staff;

public class PharmacyShop {

    public static final String CREATE_STAFF      = "CREATE_STAFF";
    public static final String CREATE_PATIENT    = "CREATE_PATIENT";
    public static final String CREATE_MENU_ITEM  = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER      = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS    = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS       = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";

    private String shopName;
    private ArrayList<Medicine> inventory;
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders;
    private int medicineCount = 0;
    private int staffCount = 0;
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
    }

    // ── Getters 
    public String getShopName()         { return shopName; }
    public int getMedicineCount()       { return medicineCount; }
    public ArrayList<Staff> getStaffs() { return staffs; }

    public void setShopName(String shopName, String newPassword) {
        if (this.password.equals(newPassword)) {
            this.shopName = shopName;
            System.out.println("Shop name updated successfully.");
        } else {
            System.out.println("Incorrect password. Shop name not updated.");
        }
    }

    // ── Login 
    public Staff login(String username, String password) {
        for (Staff s : staffs) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return null;
    }

    // ── Staff ─
    public void createStaff(Staff staff) {
        staffs.add(staff);
        staffCount++;
        System.out.println("Staff \"" + staff.getFullname() + "\" added successfully.");
    }

    public void printStaffs() {
        if (staffs.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        for (Staff s : staffs) {
            System.out.println(s.getFullname() + " - " + s.getPosition());
        }
    }

    // ── Patient / Customer 
    public void createPatient(Patient patient) {
        patients.add(patient);
    }

    public void viewCustomers() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        System.out.println("-- Customer / Patient List --");
        for (Patient p : patients) {
            System.out.println("Name: " + p.getName()
                + " | Age: " + p.getAge()
                + " | Symptom: " + p.getSymptom()
                + " | Insurance: " + (p.HasInsurance() ? "Yes" : "No"));
        }
    }

    // ── Medicine 
    public void createMenuItem(Medicine medicine) {
        inventory.add(medicine);
        medicineCount++;
    }

    public void checkMenu() {
        if (inventory.isEmpty()) {
            System.out.println("No medicines available in the inventory.");
        } else {
            System.out.println("Available Medicines:");
            for (Medicine m : inventory) {
                System.out.println("  - " + m.getName()
                    + " | Price: $" + m.getPrice()
                    + " | Qty: "   + m.getQuantity());
            }
        }
    }

    // ── Orders 

    // Creates an order — shows inventory, asks for medicine + quantity
    public void createOrder(Patient patient, Staff staff) {
        checkMenu();
        if (inventory.isEmpty()) return;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter medicine name for " + patient.getName() + ": ");
        String medName = scanner.nextLine().trim();

        Medicine found = null;
        for (Medicine m : inventory) {
            if (m.getName().equalsIgnoreCase(medName)) {
                found = m;
                break;
            }
        }
        if (found == null) {
            System.out.println("Medicine \"" + medName + "\" not found in inventory.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }

        Order order = new Order(patient, found, quantity, staff.getFullname());
        orders.add(order);
        System.out.println("Order created! " + order);
    }

    // View all orders — summary list with index numbers
    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("-- Order List --");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }
    }

    // Print full receipt by order ID
    public void getReceipt(int orderId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                System.out.println(o.receipt());
                return;
            }
        }
        System.out.println("Order #" + orderId + " not found.");
    }

    public void updateOrderStatus() {}

    // ── Seed data 
    public void setPeople() {
        Staff staff = new Pharmacist(
                "Sokha", "P001", "012345678", "sokha123",
                "Pharmacist", true, "soksok", 500.0, "sokha@gmail.com");
        staffs.add(staff);

        Staff manager = new ManagerStaff(
                "Dara", "M001", "098765432", "dara123",
                "Manager", true, "Admin", 500.0, "dara@gmail.com", 100);
        staffs.add(manager);
    }

    public void permissionTest() {
        for (Staff p : staffs) {
            System.out.println(p.getUsername() + " can CREATE_ORDER? "     + p.can(PharmacyShop.CREATE_ORDER));
            System.out.println(p.getUsername() + " can CREATE_MENU_ITEM? " + p.can(PharmacyShop.CREATE_MENU_ITEM));
            System.out.println(p.getUsername() + " can VIEW_ORDERS? "      + p.can(PharmacyShop.VIEW_ORDERS));
            System.out.println(p.getUsername() + " can VIEW_CUSTOMERS? "   + p.can(PharmacyShop.VIEW_CUSTOMERS));
        }
        StaffFilter filter = (Staff s) -> s.isActive();
        staffs.stream().filter(s -> s.isActive()).forEach(s -> System.out.println(s.getFullname()));
    }
}