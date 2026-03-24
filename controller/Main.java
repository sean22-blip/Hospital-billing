package controller;

public class Main {
    public static void main(String[] args) {

        PharmacyShop pShop = new PharmacyShop("Kaisen", "67676", "st 60m borey-peng-hout psar-kandal battambang");
        pShop.setPeople();

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // ── Login gate ────────────────────────────────────────
        System.out.println("=== Welcome to " + pShop.getShopName() + " ===");
        user.Staff loggedInStaff = null;

        while (loggedInStaff == null) {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            loggedInStaff = pShop.login(username, password);
            if (loggedInStaff == null) {
                System.out.println("Invalid credentials. Try again.\n");
            }
        }
        System.out.println("Welcome, " + loggedInStaff.getFullname()
            + " (" + loggedInStaff.getPosition() + ")!\n");

        // ── Main menu loop ────────────────────────────────────
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1). Create staff");
            System.out.println("2). Create order");
            System.out.println("3). Create medicine (menu item)");
            System.out.println("4). Create patient");
            System.out.println("5). View staff list");
            System.out.println("6). View orders");         // NEW
            System.out.println("7). View customers");      // NEW
            System.out.println("8). Get receipt");         // NEW
            System.out.println("9). Exit");
            System.out.print("=> : ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (option) {

                case 1: // Create Staff
                    if (!loggedInStaff.can(PharmacyShop.CREATE_STAFF)) {
                        System.out.println("Access denied: you don't have permission to create staff.");
                        break;
                    }
                    System.out.println("-- Create Staff --");
                    System.out.print("Role (1 = Pharmacist, 2 = Manager): ");
                    int role;
                    try { role = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid input."); break; }

                    System.out.print("Full Name: ");    String fullName = scanner.nextLine().trim();
                    System.out.print("Staff ID: ");     String staffID  = scanner.nextLine().trim();
                    System.out.print("Phone: ");        String phone    = scanner.nextLine().trim();
                    System.out.print("Password: ");     String sPass    = scanner.nextLine().trim();
                    System.out.print("Username: ");     String sUser    = scanner.nextLine().trim();
                    System.out.print("Salary: ");
                    double salary;
                    try { salary = Double.parseDouble(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid salary."); break; }
                    System.out.print("Email: ");        String email = scanner.nextLine().trim();

                    user.Staff newStaff;
                    if (role == 1) {
                        newStaff = new user.Pharmacist(fullName, staffID, phone, sPass,
                                "Pharmacist", true, sUser, salary, email);
                    } else if (role == 2) {
                        newStaff = new user.ManagerStaff(fullName, staffID, phone, sPass,
                                "Manager", true, sUser, salary, email, 0);
                    } else {
                        System.out.println("Invalid role.");
                        break;
                    }
                    pShop.createStaff(newStaff);
                    break;

                case 2: // Create Order
                    if (!loggedInStaff.can(PharmacyShop.CREATE_ORDER)) {
                        System.out.println("Access denied: you don't have permission to create orders.");
                        break;
                    }
                    System.out.println("-- Create Order --");
                    System.out.print("Patient name: ");    String pName    = scanner.nextLine().trim();
                    System.out.print("Symptom: ");         String pSymptom = scanner.nextLine().trim();
                    System.out.print("Age: ");
                    int pAge;
                    try { pAge = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid age."); break; }
                    System.out.print("Has insurance? (true/false): ");
                    boolean pIns = Boolean.parseBoolean(scanner.nextLine().trim());

                    other.Patient orderPatient = new other.Patient(pName, pSymptom, pAge, pIns);
                    pShop.createPatient(orderPatient); // also save the patient
                    pShop.createOrder(orderPatient, loggedInStaff);
                    break;

                case 3: // Create Medicine
                    if (!loggedInStaff.can(PharmacyShop.CREATE_MENU_ITEM)) {
                        System.out.println("Access denied: you don't have permission to add medicines.");
                        break;
                    }
                    System.out.println("-- Add Medicine --");
                    System.out.print("Medicine name: ");   String medName = scanner.nextLine().trim();
                    System.out.print("Quantity: ");
                    int qty;
                    try { qty = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid quantity."); break; }
                    System.out.print("Price: ");
                    double medPrice;
                    try { medPrice = Double.parseDouble(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid price."); break; }

                    pShop.createMenuItem(new other.Medicine(medName, qty, medPrice));
                    System.out.println("Medicine \"" + medName + "\" added. Total: " + pShop.getMedicineCount());
                    break;

                case 4: // Create Patient
                    if (!loggedInStaff.can(PharmacyShop.CREATE_PATIENT)) {
                        System.out.println("Access denied: you don't have permission to create patients.");
                        break;
                    }
                    System.out.println("-- Create Patient --");
                    System.out.print("Name: ");     String cName    = scanner.nextLine().trim();
                    System.out.print("Symptom: ");  String cSymptom = scanner.nextLine().trim();
                    System.out.print("Age: ");
                    int cAge;
                    try { cAge = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid age."); break; }
                    System.out.print("Has insurance? (true/false): ");
                    boolean cIns = Boolean.parseBoolean(scanner.nextLine().trim());

                    pShop.createPatient(new other.Patient(cName, cSymptom, cAge, cIns));
                    System.out.println("Patient \"" + cName + "\" created successfully.");
                    break;

                case 5: // View Staff List
                    System.out.println("-- Staff List --");
                    pShop.printStaffs();
                    break;

                case 6: // View Orders
                    if (!loggedInStaff.can(PharmacyShop.VIEW_ORDERS)) {
                        System.out.println("Access denied: you don't have permission to view orders.");
                        break;
                    }
                    pShop.viewOrders();
                    break;

                case 7: // View Customers
                    if (!loggedInStaff.can(PharmacyShop.VIEW_CUSTOMERS)) {
                        System.out.println("Access denied: you don't have permission to view customers.");
                        break;
                    }
                    pShop.viewCustomers();
                    break;

                case 8: // Get Receipt
                    if (!loggedInStaff.can(PharmacyShop.VIEW_ORDERS)) {
                        System.out.println("Access denied: you don't have permission to view receipts.");
                        break;
                    }
                    pShop.viewOrders(); // show list first so user knows which ID to pick
                    System.out.print("Enter Order ID to print receipt: ");
                    int receiptId;
                    try { receiptId = Integer.parseInt(scanner.nextLine().trim()); }
                    catch (NumberFormatException e) { System.out.println("Invalid ID."); break; }
                    pShop.getReceipt(receiptId);
                    break;

                case 9: // Exit
                    System.out.println("Goodbye, " + loggedInStaff.getFullname() + "!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 -> 9.");
                    break;
            }
        }

        scanner.close();
    }
}