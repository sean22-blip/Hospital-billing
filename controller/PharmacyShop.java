package controller;

import java.util.ArrayList;
import java.util.Scanner;
import other.Medicine;
import other.Order;
import other.Patient;
import user.ManagerStaff;
import user.OwnerStaff;
import user.Pharmacist;
import user.Staff;

@FunctionalInterface
interface StaffFilter {

    boolean test(Staff s);
}

public class PharmacyShop {

    // ─────────────────────────────────────────────
    // ── Constants (Permissions)
    // ─────────────────────────────────────────────
    public static final String CREATE_STAFF = "CREATE_STAFF";
    public static final String CREATE_PATIENT = "CREATE_PATIENT";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";

    // ─────────────────────────────────────────────
    // ── Fields
    // ─────────────────────────────────────────────
    private String shopName;
    private String password;
    private String address;
    private String username;

    private ArrayList<Medicine> inventory;
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders;

    private int medicineCount = 0;
    private int staffCount = 0;

    // ─────────────────────────────────────────────
    // ── Predefined Staff Filters
    // ─────────────────────────────────────────────
    private final StaffFilter activeFilter = s -> s.isActive();
    // private final StaffFilter pharmacistFilter = s ->
    // s.getPosition().equals("Pharmacist");
    // private final StaffFilter managerFilter = s ->
    // s.getPosition().equals("Manager");

    // ─────────────────────────────────────────────
    // ── Constructor
    // ─────────────────────────────────────────────
    public PharmacyShop(String shopName, String password, String address, String username) {
        this.shopName = shopName;
        this.password = password;
        this.address = address;
        this.username = username;
        this.inventory = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.orders = new ArrayList<>();
        // setPeople();
        snapshotProof();
    }
        // ── Snapshot Design Requirement Proof ─────────────────────
    // Demonstrates primitive vs reference behaviour using real
    // classes from this project (Medicine, Patient, Order).
    public void snapshotProof() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   PRIMITIVE vs REFERENCE — PROOF OUTPUT          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
 
        // ── F1: Primitive copy ────────────────────────────────
        // A primitive variable holds its value directly.
        // Copying it creates a completely independent copy.
        // Modifying the copy does NOT affect the original.
        System.out.println("\n── F1: Primitive Copy ──────────────────────────────");
        double originalPrice = 25.00;
        double copyPrice     = originalPrice; // value is duplicated, not shared
        copyPrice = 99.99;                    // modify only the copy
        System.out.println("  originalPrice = $" + originalPrice); // still 25.00
        System.out.println("  copyPrice     = $" + copyPrice);     // 99.99
        System.out.println("  ✓ Original unchanged — primitive copy proved.");
 
        // ── F2: Reference copy ───────────────────────────────
        // A reference variable holds a memory address, not the object.
        // Two references pointing to the same object — a change through
        // one is immediately visible through the other.
        System.out.println("\n── F2: Reference Copy ──────────────────────────────");
        Patient patientA = new Patient("Mey", "Cancer", 67, "P001", 'M', false);
        Patient patientB = patientA;               // both point to same object
        patientB.setHasInsurance(true);            // mutate through patientB
        System.out.println("  After patientB.setHasInsurance(true):");
        System.out.println("  patientA.HasInsurance() = " + patientA.HasInsurance()); // true
        System.out.println("  patientB.HasInsurance() = " + patientB.HasInsurance()); // true
        System.out.println("  ✓ Change visible on both variables — reference copy proved.");
 
        // ── F3: Array stores references ───────────────────────
        // Arrays of objects store references, not copies of objects.
        // Mutating the original object is reflected when accessed via the array.
        System.out.println("\n── F3: Array Stores References ─────────────────────");
        Medicine paracetamol = new Medicine("Paracetamol", 100, 5.00);
        Medicine[] shelf = new Medicine[1];
        shelf[0] = paracetamol;                    // array slot holds a reference
        System.out.println("  shelf[0].getPrice() before change: $" + shelf[0].getPrice());
        paracetamol.setPrice(8.50);                // mutate original
        System.out.println("  shelf[0].getPrice() after  change: $" + shelf[0].getPrice());
        System.out.println("  ✓ Array reflected the mutation — reference in array proved.");
 
        // ── F4: Snapshot behaviour ────────────────────────────
        // Order stores medicine price as a primitive double (snapshotPrice)
        // and medicine name as a String (immutable in Java).
        // Both are VALUE copies taken at order creation time.
        // Even after the Medicine object is mutated, the order's
        // stored values remain exactly as they were.
        System.out.println("\n── F4: Snapshot Behaviour ──────────────────────────");
        Medicine aspirin = new Medicine("Aspirin", 50, 12.00);
        Patient dara = new Patient("Dara", "Flu", 30, "C002", 'F', true);
        Order    order   = new Order(dara, aspirin, 3, "Sokha");
        System.out.println("  [At order creation time]");
        System.out.println("  order.getSnapshotMedName() = " + order.getSnapshotMedName());
        System.out.println("  order.getSnapshotPrice()   = $" + order.getSnapshotPrice());
        System.out.println("  order.getTotal()           = $" + order.getTotal());
 
        // Now mutate the original Medicine — price and name both change
        aspirin.setPrice(99.99);
        aspirin.setName("RENAMED-Aspirin");
        System.out.println("\n  [After aspirin.setPrice(99.99) and setName(\"RENAMED-Aspirin\")]");
        System.out.println("  aspirin.getPrice()         = $" + aspirin.getPrice());        // 99.99
        System.out.println("  aspirin.getName()          = "  + aspirin.getName());         // RENAMED
        System.out.println("  order.getSnapshotPrice()   = $" + order.getSnapshotPrice());  // still 12.0
        System.out.println("  order.getSnapshotMedName() = "  + order.getSnapshotMedName()); // still Aspirin
        System.out.println("  order.getTotal()           = $" + order.getTotal());           // still 36.0
        System.out.println("  ✓ Snapshot unchanged — primitive value copy proved.");
 
    }

    // ─────────────────────────────────────────────
    // ── Getters & Setters
    // ─────────────────────────────────────────────
    public String getShopName() {
        return shopName;
    }

    public int getMedicineCount() {
        return medicineCount;
    }

    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public void setShopName(String shopName, String newPassword) {
        if (this.password.equals(newPassword)) {
            this.shopName = shopName;
            System.out.println("Shop name updated successfully.");
        } else {
            System.out.println("Incorrect password. Shop name not updated.");
        }
    }

    // ─────────────────────────────────────────────
    // ── Helper: Filter Staffs
    // ─────────────────────────────────────────────
    public void filterAndPrintStaffs(StaffFilter filter) {
        boolean found = false;
        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println("  - " + s.getFullname() + " (" + s.getPosition() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No staff matched the filter.");
        }
    }

    // ─────────────────────────────────────────────
    // ── Authentication
    // ─────────────────────────────────────────────
    private Staff login(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)) {
            return new OwnerStaff(
                    "Owner", "OWNER001", "000000000", password,
                    "Owner", true, username, 0.0, "owner@kaisen.com");
        }
        for (Staff s : staffs) {
            if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return null;
    }

    public Staff handleLogin(Scanner scanner) {
        System.out.println("=== Login ===");
        int attempts = 0;

        while (attempts < 3) {
            // Username
            System.out.print("Username: ");
            String usn = scanner.nextLine().trim();
            if (usn.isEmpty()) {
                System.out.println("Username cannot be empty!");
                continue;
            }
            if (!usn.matches("[a-zA-Z0-9_]+")) {
                System.out.println("Invalid username!");
                continue;
            }
            int letterCount = 0;
            for (char c : usn.toCharArray()) {
                if (Character.isLetter(c)) {
                    letterCount++;
                }
            }
            if (letterCount < 3) {
                System.out.println("Username must contain at least 3 letters!");
                continue;
            }

            System.out.print("Password: ");
            String pass;
            while (true) {
                pass = scanner.nextLine().trim();
                if (!pass.isEmpty()) {
                    break;
                }
                System.out.print("Password cannot be empty! Try again: ");
            }
            Staff staff = login(usn, pass);
            if (staff != null) {
                System.out.println("Welcome, " + staff.getFullname() + " (" + staff.getPosition() + ")");
                return staff;
            }
            attempts++;
            if (attempts < 3) {
                System.out.println("Invalid username or password."
                        + "\nYou have " + (3 - attempts) + " attempts left.\n");
            }
        }

        System.out.println("Too many failed attempts. Exiting...");
        return null;
    }

    // ─────────────────────────────────────────────
    // ── Staff Management
    // ─────────────────────────────────────────────
    private void createStaff(Staff staff) {
        staffs.add(staff);
        staffCount++;
        System.out.println("Staff \"" + staff.getFullname() + "\" added successfully.");
    }

    public void handleCreateStaff(Scanner scanner) {
        System.out.println("-- Create Staff --");

        // Role
        int role;
        System.out.println("Roles: 1. Pharmacist | 2. Manager");
        do {
            System.out.print("Select role (1 or 2): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Role cannot be empty!");
                continue;
            }
            try {
                role = Integer.parseInt(input);
                if (role != 1 && role != 2) {
                    System.out.println("Please enter 1 or 2.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter 1 or 2.");
            }
        } while (true);
        // Full Name
        String fullName;
        System.out.print("Full name: ");
        while (true) {
            fullName = scanner.nextLine().trim();

            if (fullName.isEmpty()) {
                System.out.print("Full name cannot be empty. Please try again:");
                continue;
            }

            if (!fullName.matches("[a-zA-Z ]+")) {
                System.out.print("Invalid name, Please try again:");
                continue;
            }
            fullName = Character.toUpperCase(fullName.charAt(0)) + fullName.substring(1).toLowerCase();
            break;
        }
        // Staff ID
        String staffId;
        while (true) {
            System.out.print("Staff ID: ");
            staffId = scanner.nextLine().trim();
            if (staffId.isEmpty()) {
                System.out.println("Staff ID cannot be empty!");
                continue;
            }
            if (staffId.matches(".*\\s.*")) {
                System.out.println("Staff ID cannot contain spaces!");
                continue;
            }
            if (role == 1) {
                if (!staffId.matches("p\\d{3}")) {
                    System.out.println("Please enter a valid staff ID (e.g., p001 for Pharmacist)");
                    continue;
                }
            }
            if (role == 2) {
                if (!staffId.matches("m\\d{3}")) {
                    System.out.println("Please enter a valid staff ID (e.g., m001 for Manager)");
                    if (ManagerStaff.getManagerCount() > 3) {
                        System.out.println(
                                "==== WARNING === \nYou have created " + ManagerStaff.getManagerCount() + " managers.");
                    }
                    continue;
                }
            }
            break;
        }

        // Phone
        String phone;
        while (true) {
            System.out.print("Phone number: ");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Phone cannot be empty!");
                continue;
            }
            if (phone.length() < 9 || phone.length() > 15) {
                System.out.println("Phone number must be between 9 and 15 digits.");
                continue;
            }
            if (!phone.matches("\\d{9,15}")) {
                System.out.println("Enter 9-15 digits only.");
                continue;
            }
            break;
        }
        ;

        // Password
        String password;
        while (true) {
            System.out.print("Password: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty!");
                continue;
            }
            if (password.length() < 6) {
                System.out.println("Password must be at least 6 characters.");
                continue;
            }
            break;
        }

        // Username
        String username;
        while (true) {
            System.out.print("Username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty!");
                continue;
            }
            if (username.matches(".*\\s.*")) {
                System.out.println("Username cannot contain spaces!");
                continue;
            }
            break;
        }

        // Salary
        double salary;
        while (true) {
            System.out.print("Salary: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Salary cannot be empty!");
                continue;
            }
            try {
                salary = Double.parseDouble(input);
                if (salary < 0 || salary >= 100000) {
                    System.out.println("Invalid salary amount.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input.");
            }
        }

        // Email
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email cannot be empty!");
                continue;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Invalid email format.");
                continue;
            }
            break;
        }

        // Create
        Staff newStaff = (role == 1)
                ? new Pharmacist(fullName, staffId, phone, password, "Pharmacist", true, username, salary, email)
                : new ManagerStaff(fullName, staffId, phone, password, "Manager", true, username, salary, email, 0);

        createStaff(newStaff);
    }

    public void viewAllStaffs() {
        if (staffs.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        filterAndPrintStaffs(s -> true); // print all
    }

    public void viewActiveStaffs() {
        System.out.println("-- Active Staff List --");
        filterAndPrintStaffs(activeFilter);
    }

    // ─────────────────────────────────────────────
    // ── Patient Management
    // ─────────────────────────────────────────────
    public void createPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient handleCreatePatient(Scanner scanner) {

        // Name
        String name;
        while (true) {
            System.out.print("Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
                continue;
            }
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid name input!!");
                continue;
            }
            break;
        }

        // Symptom
        String symptom;
        while (true) {
            System.out.print("Symptom: ");
            symptom = scanner.nextLine().trim();
            if (symptom.isEmpty()) {
                System.out.println("Symptom cannot be empty!");
                continue;
            }
            if (!symptom.matches("[a-zA-Z ]+")) {
                System.out.print("Symptom must contain letters!");
                continue;
            }
            break;
        }

        // Age
        int age;
        while (true) {
            System.out.print("Age: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Age cannot be empty!");
                continue;
            }
            try {
                age = Integer.parseInt(input);
                if (age <= 0 || age >= 120) {
                    System.out.println("Invalid age. Enter between 1-119.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input.");
            }
        }

        String id;
        while (true) {
            System.out.print("Patient ID: ");
            id = scanner.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Patient ID cannot be empty!");
                continue;
            }

            if (!id.matches("[Cc]\\d{3}")) {
                System.out.println("Please enter a valid patient ID (e.g., C001 for a patient)");
                continue;
            }
            if (id.matches(".*\\s.*")) {
                System.out.println("Patient ID cannot contain spaces!");
                continue;
            }
            break;
        }
        char gender;
        while (true) {
            System.out.print("Gender (M/F): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Gender cannot be empty!");
                continue;
            }
            if (!input.equalsIgnoreCase("M") && !input.equalsIgnoreCase("F")) {
                System.out.println("Gender must be M or F!");
                continue;
            }
            gender = Character.toUpperCase(input.charAt(0));
            break;
        }
        // Insurance
        boolean ins = false;
        while(true){
        System.out.print("Has insurance (yes/no): ");
         String input = scanner.nextLine().trim();
         if (input.isEmpty()) {
             System.out.println("Insurance status cannot be empty!");
             continue;
         }
         if (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
             System.out.println("Please enter 'yes' or 'no'.");
             continue;
         }
         ins = input.equalsIgnoreCase("yes");
         break;
        }
        Patient p = new Patient(name, symptom, age, id, gender, ins);
        createPatient(p);
        return p;
    }

    public void viewPatients() {
        System.out.println("-- Patient List --");
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient p : patients) {
            System.out.println("  - " + p.getName()
                    + " | Age: " + p.getAge()
                    + " | Symptom: " + p.getSymptom()
                    + " | Insurance: " + (p.HasInsurance() ? "Yes" : "No"));
        }
    }

    // ─────────────────────────────────────────────
    // ── Medicine / Inventory
    // ─────────────────────────────────────────────
    private void createMenuItem(Medicine medicine) {
        inventory.add(medicine);
        medicineCount++;
    }

    public void handleCreateMedicine(Scanner scanner) {
        System.out.println("-- Add Medicine --");
        String medName = "";
        while (true) {
            System.out.print("Medicine name: ");
            medName = scanner.nextLine().trim();
            if (medName.isEmpty()) {
                System.out.println("Medicine name cannot be empty!");
                continue;
            }
            if (!medName.matches("[a-zA-Z ]+")) {
                System.out.println("Medicine name can only contain letters!");
                continue;
            }
            break;
        }

        // Quantity
        int qty;
        while (true) {
            System.out.print("Quantity: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Quantity cannot be empty!");
                continue;
            }
            try {
                qty = Integer.parseInt(input);
                if (qty <= 0 || qty >= 1000) {
                    System.out.println("Quantity must be between 1–999.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity input.");
                continue;
            }
        }

        // Price
        double price;
        while (true) {
            System.out.print("Price: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Price cannot be empty!");
                continue;
            }
            try {
                price = Double.parseDouble(input);
                if (price <= 0 || price >= 10000) {
                    System.out.println("Price must be between 1–9999.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input.");
            }
        }

        createMenuItem(new Medicine(medName, qty, price));
        System.out.println("Medicine added successfully!");
    }

    public void viewInventory() {
        System.out.println("=== Current inventory ===");
        if (inventory.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }
        for (Medicine m : inventory) {
            System.out.println("  - " + m.getName()
                    + " | Price: $" + m.getPrice()
                    + " | Qty: " + m.getQuantity());
        }
    }

    public boolean hasInventory() {
        return !inventory.isEmpty();
    }

    // ─────────────────────────────────────────────
    // ── Order Management
    // ─────────────────────────────────────────────
    private void createOrder(Patient patient, Staff staff, Scanner scanner) {
        viewInventory();
        if (inventory.isEmpty()) {
            return;
        }

        // Medicine name
        String medName;
        while (true) {
            System.out.print("Enter medicine name for " + patient.getName() + ": ");
            medName = scanner.nextLine().trim();
            if (medName.isEmpty()) {
                System.out.println("Medicine name cannot be empty!");
                continue;
            }
            if (medName.matches(".*\\d.*")) {
                System.out.println("Medicine name cannot contain numbers!");
                continue;
            }
            String finalMedName = medName;
            if (!inventory.stream().anyMatch(m -> m.getName().equalsIgnoreCase(finalMedName))) {
                System.out.println("Medicine not found in inventory! Please choose from the list above.");
                continue;
            }
            break;
        }

        // Find medicine
        Medicine found = null;
        for (Medicine m : inventory) {
            if (m.getName().equalsIgnoreCase(medName)) {
                found = m;
                break;
            }
        }
        if (found == null) {
            System.out.println("Medicine \"" + medName + "\" not found.");
            return;
        }

        // Quantity
        int quantity;
        while (true) {
            System.out.print("Quantity: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Quantity cannot be empty!");
                continue;
            }
            try {
                quantity = Integer.parseInt(input);
                if (quantity <= 0 || quantity >= 1000) {
                    System.out.println("Quantity must be between 1–999.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity input.");
                continue;
            }
        }

        Order order = new Order(patient, found, quantity, staff.getFullname());
        orders.add(order);
        System.out.println("Order created! " + order);
    }

    public void handleCreateOrder(Scanner scanner, Staff staff) {
        if (!hasInventory()) {
            System.out.println("Cannot create order: inventory is empty.");
            return;
        }

        String patientId;
        Patient patient = null;

        while (true) {
            System.out.print("Enter patient ID: ");
            patientId = scanner.nextLine().trim();

            if (patientId.isEmpty()) {
                System.out.println("Patient ID cannot be empty!");
                continue;
            }
            if (patientId.matches(".*\\s.*")) {
                System.out.println("Patient ID cannot contain spaces!");
                continue;
            }

            for (Patient p : patients) {
                if (p.getID().equals(patientId)) {
                    patient = p;
                    break;
                }
            }

            if (patient == null) {
                System.out.println("Patient ID not found!");
                continue;
            }

            break;
        }

        createOrder(patient, staff, scanner);
    }

    public void viewOrders() {
        System.out.println("-- Order List --");
        if (orders.isEmpty()) {
            System.out.println("No orders created yet.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }
    }

    private void getReceipt(int orderId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                System.out.println(o.receipt());
                return;
            }
        }
        System.out.println("Order #" + orderId + " not found.");
    }

    public void handleReceipt(Scanner scanner) {
        if (!hasOrders()) {
            System.out.println("No orders available.");
            return;
        }
        viewOrders();

        int id;
        while (true) {
            System.out.print("Enter Order ID: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Order ID cannot be empty!");
                continue;
            }
            try {
                id = Integer.parseInt(input);
                if (id <= 0) {
                    System.out.println("Invalid Order ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Order ID input.");
                continue;
            }
        }

        getReceipt(id);
    }

    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    // ─────────────────────────────────────────────
    // ── Permission Test
    // ─────────────────────────────────────────────
    public void permissionTest() {
        System.out.println("-- Permission Test --");
        for (Staff s : staffs) {
            System.out.println(s.getUsername() + " | CREATE_ORDER: " + s.can(CREATE_ORDER));
            System.out.println(s.getUsername() + " | CREATE_MENU_ITEM: " + s.can(CREATE_MENU_ITEM));
            System.out.println(s.getUsername() + " | VIEW_ORDERS: " + s.can(VIEW_ORDERS));
            System.out.println(s.getUsername() + " | VIEW_CUSTOMERS: " + s.can(VIEW_CUSTOMERS));
        }
    }

    // ─────────────────────────────────────────────
    // ── See Data
    // ─────────────────────────────────────────────
    private void seeData() {
        staffs.add(new Pharmacist(
                "Sokha", "P001", "012345678", "sokha123",
                "Pharmacist", true, "soksok", 500.0, "sokha@gmail.com"));

        staffs.add(new ManagerStaff(
                "Dara", "M001", "098765432", "dara123",
                "Manager", true, "Admin", 500.0, "dara@gmail.com", 100));
        staffs.add(new ManagerStaff(
                "Chan", "M002", "077123456", "chan123",
                "Manager", true, "Surgery", 800.0, "chan@gmail.com", 50));
        staffs.add(new ManagerStaff(
                "Sophea", "N001", "070987654", "sophea123",
                "Nurse", true, "Pediatrics", 450.0, "sophea@gmail.com", 30));
        patients.add(new Patient("Mey", "Cancer", 67, "P001", 'M', true));
    }
}
