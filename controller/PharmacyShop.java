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
    private int medicineCount = 0;
    private int staffCount = 0;
    private String password;
    private String address;
    private String username;

    public PharmacyShop(String shopName, String password, String address, String username) {
        this.shopName = shopName;
        this.password = password;
        this.address = address;
        this.username = username;
        this.inventory = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.orders = new ArrayList<>();
        setPeople();
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
        Patient patientA = new Patient("Sokha", "Headache", 30, false);
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
        Patient  dara    = new Patient("Dara", "Fever", 25, true);
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

    // ── Getters
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

    // ── Login
    public Staff login(String username, String password) {
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

    while (true) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            continue;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty!");
            continue;
        }

        Staff staff = login(username, password);

        if (staff != null) {
            System.out.println("Welcome, " + staff.getFullname()
                    + " (" + staff.getPosition() + ")");
            return staff;
        } else {
            System.out.println("Invalid username or password. Try again.\n");
        }
    }
}

    // ── Staff
    public void createStaff(Staff staff) {
        staffs.add(staff);
        staffCount++;
        System.out.println("Staff \"" + staff.getFullname() + "\" added successfully.");
    }

    public void handleCreateStaff(Scanner scanner) {

        System.out.println("-- Create Staff --");

        System.out.println("Roles: 1. Pharmacist | 2. Manager");
        int role;
        do { 
            System.out.print("Select role (1 or 2): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Role selection cannot be empty!");
            }
            try {
                role = Integer.parseInt(input);
                if (role != 1 && role != 2) {
                    System.out.println("Invalid role selection. Please enter 1 or 2.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
            }
            
        } while (true);

        System.out.println("Enter full name: ");
        String fullName ;
        do {
            try {
                fullName = scanner.nextLine().trim();
                if (fullName.isEmpty()) {
                    throw new IllegalArgumentException("Full name cannot be empty! Please enter a valid full name.");
                }
                if (fullName.matches(".*\\d.*")) {
                    throw new IllegalArgumentException("Full name cannot contain numbers!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);

        System.out.println("Enter Staff ID: ");
        String staffId ;
        do {
            try {
                staffId = scanner.nextLine().trim();
                if (staffId.isEmpty()) {
                    throw new IllegalArgumentException("Staff ID cannot be empty! Please enter a valid Staff ID.");
                }
                if (staffId.matches(".*\\s.*")) {
                    throw new IllegalArgumentException("Staff ID cannot contain spaces!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);

        System.out.println("Enter phone number: ");
        String phone ;
        do {
            try {
                phone = scanner.nextLine().trim();
                if (phone.isEmpty()) {
                    throw new IllegalArgumentException("Phone number cannot be empty! Please enter a valid phone number.");
                }
                if (!phone.matches("\\d{9,15}")) {
                    throw new IllegalArgumentException("Invalid phone number format. Please enter 9 to 15 digits.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);

        System.out.println("Enter password: ");
        String password ;
        do {
            try {
                password = scanner.nextLine().trim();
                if (password.isEmpty()) {
                    throw new IllegalArgumentException("Password cannot be empty! Please enter a valid password.");
                }
                if (password.length() < 6) {
                    throw new IllegalArgumentException("Password must be at least 6 characters long.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);
        System.out.println("Enter username: ");
        String username ;
        do {
            try {
                username = scanner.nextLine().trim();
                if (username.isEmpty()) {
                    throw new IllegalArgumentException("Username cannot be empty! Please enter a valid username.");
                }
                if (username.matches(".*\\s.*")) {
                    throw new IllegalArgumentException("Username cannot contain spaces!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);

        System.out.println("Enter salary: ");
        double salary;
        do {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Salary cannot be empty!");
                continue;
            }
            try {
                salary = Double.parseDouble(input);
                if (salary < 0 || salary >= 100000) {
                    System.out.println("Invalid salary amount.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input.");
                
            }
        } while (true);

        System.out.print("Email: ");
        String email ;
        do {
            try {
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    throw new IllegalArgumentException("Email cannot be empty! Please enter a valid email address.");
                }
                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    throw new IllegalArgumentException("Invalid email format. Please enter a valid email address.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            email = scanner.nextLine().trim();
            
        } while (true);

        Staff newStaff;
        if (role == 1) {
            newStaff = new Pharmacist(fullName, staffId, phone, password,
                    "Pharmacist", true, username, salary, email);
        } else if (role == 2) {
            newStaff = new ManagerStaff(fullName, staffId, phone, password,
                    "Manager", true, username, salary, email, 0);
        } else {
            System.out.println("Invalid role.");
            return;
        }

        createStaff(newStaff);
    }

    // ── Patient / Customer
    public void createPatient(Patient patient) {
        patients.add(patient);

    }

    public Patient handleCreatePatient(Scanner scanner) {
        System.out.println("-- Create Patient --");

        System.out.print("Name: ");
        String name ;
        do {
            try {
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty! Please enter a valid name.");
                }
                if (name.matches(".*\\d.*")) {
                    throw new IllegalArgumentException("Name cannot contain numbers!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);

        System.out.print("Symptom: ");
        String symptom ;
        do {
            try {
                symptom = scanner.nextLine().trim();
                if (symptom.isEmpty()) {
                    throw new IllegalArgumentException("Symptom cannot be empty! Please enter a valid symptom.");
                }
                if (symptom.matches(".*\\d.*")) {
                    throw new IllegalArgumentException("Symptom cannot contain numbers!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        
        } while (true);

        System.out.print("Age: ");
        int age;
        do{
            System.out.print("Age: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Age cannot be empty!");
            }
            try {
                age = Integer.parseInt(input);
                if (age <= 0 || age >= 120) {
                    System.out.println("Invalid AGE.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input.");
                return null;
            }
        }
        while (true);

        System.out.print("Has insurance (true/false): ");
        boolean ins = Boolean.parseBoolean(scanner.nextLine().trim());

        Patient p = new Patient(name, symptom, age, ins);
        createPatient(p);

        return p;
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

    public void handleCreateMedicine(Scanner scanner) {
        System.out.println("-- Add Medicine --");

        System.out.print("Medicine name: ");
        String medName;
        do {
            try {
                medName = scanner.nextLine().trim();
                if (medName.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Medicine medName cannot be empty. Please enter a valid medName.");
                }
                if (medName.matches(".*\\d.*")) {
                    throw new IllegalArgumentException("medicine medName cannot contain numbers!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        System.out.print("Quantity: ");
        int qty;

        do {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Quantity cannot be empty!");
                continue;
            }
            try {
                qty = Integer.parseInt(input);
                if (qty <= 0 || qty >= 1000) {
                    System.out.println("Invalid QUANTITY.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity input.");
                return;
            }
        } while (true);

        System.out.print("Price: ");
        double price;
        do {
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input.");
                return;
            }
            if (price <= 0 || price >= 10000) {
                System.out.println("Invalid PRICE.");
                break;
            }

        } while (true);

        createMenuItem(new Medicine(medName, qty, price));
        System.out.println("Medicine added!");
    }

    public String checkMenu() {
        if (inventory.isEmpty()) {
            return "No medicines available in the inventory.";
        } else {
            System.out.println("Available Medicines:");
            for (Medicine m : inventory) {
                System.out.println("  - " + m.getName()
                        + " | Price: $" + m.getPrice()
                        + " | Qty: " + m.getQuantity());
            }
        }
        return "TEst";
    }

    // ── Orders

    // Creates an order — shows inventory, asks for medicine + quantity
    public void createOrder(Patient patient, Staff staff) {
        checkMenu();
        if (inventory.isEmpty())
            return;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter medicine name for " + patient.getName() + ": ");
        String medName ;
        do {
            try {
                medName = scanner.nextLine().trim();
                if (medName.isEmpty()) {
                    throw new IllegalArgumentException("Medicine name cannot be empty! Please enter a valid medicine name.");
                }
                if (medName.matches(".*\\d.*")) {
                    throw new IllegalArgumentException("Medicine name cannot contain numbers!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        
        } while (true);

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
        do {
            System.out.print("Quantity: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Quantity cannot be empty!");

            }
            try {
                quantity = Integer.parseInt(input);
                if (quantity <= 0 || quantity >= 1000) {
                    System.out.println("Invalid QUANTITY.");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println();
            }
        } while (true);

        Order order = new Order(patient, found, quantity, staff.getFullname());
        orders.add(order);
        System.out.println("Order created! " + order);
        scanner.close();
    }
    public void handleCreateOrder(Scanner scanner, Staff staff) {
    if (inventory.isEmpty()) {
        System.out.println("No medicines available.");
        return;
    }

    Patient patient = handleCreatePatient(scanner);
    createOrder(patient, staff);
}
    //view staff list
    public void handleViewStaffs() {
    System.out.println("-- Staff List --");
    if (staffs.isEmpty()) {
        System.out.println("No staff found.");
        return;
    }
}
    // View all orders — summary list with index numbers
    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No order has been created!");
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
            if (o.getOrderId() == null) {
                System.out.println("No order in the list!");
                break;
            }
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

    System.out.print("Enter Order ID: ");
    int id ;
    do {
        System.out.print("Order ID: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Order ID cannot be empty!");
        }
        try {
            id = Integer.parseInt(input);
            if (id <= 0) {
                System.out.println("Invalid Order ID.");
                break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID input.");
            return;
        }
    } while (true);

    getReceipt(id);
}

    public boolean hasOrders() {
        return !orders.isEmpty();
    }

    // ── Seed data
    private void setPeople() {

        Staff staff = new Pharmacist(
                "Sokha", "P001", "012345678", "sokha123",
                "Pharmacist", true, "soksok", 500.0, "sokha@gmail.com");
        staffs.add(staff);

        Staff manager = new ManagerStaff(
                "Dara", "M001", "098765432", "dara123",
                "Manager", true, "Admin", 500.0, "dara@gmail.com", 100);
        staffs.add(manager);
    }

    public boolean hasInventory() {
        return !inventory.isEmpty();
    }

    public void permissionTest() {
        for (Staff p : staffs) {
            System.out.println(p.getUsername() + " can CREATE_ORDER? " + p.can(PharmacyShop.CREATE_ORDER));
            System.out.println(p.getUsername() + " can CREATE_MENU_ITEM? " + p.can(PharmacyShop.CREATE_MENU_ITEM));
            System.out.println(p.getUsername() + " can VIEW_ORDERS? " + p.can(PharmacyShop.VIEW_ORDERS));
            System.out.println(p.getUsername() + " can VIEW_CUSTOMERS? " + p.can(PharmacyShop.VIEW_CUSTOMERS));
        }
        StaffFilter filter = (Staff s) -> s.isActive();
        staffs.stream().filter(s -> s.isActive()).forEach(s -> System.out.println(s.getFullname()));
    }
}
