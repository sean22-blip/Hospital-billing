package controller;

import java.util.Scanner;

import other.Medicine;
import other.Patient;
import user.ManagerStaff;
import user.Pharmacist;
import user.Staff;

public class Main {

    public static void main(String[] args) throws RuntimeException {

        PharmacyShop pShop = new PharmacyShop("Kaisen", "676767", "st 60m borey-peng-hout psar-kandal battambang",
                "IKAKAKA");
        Scanner scanner = new Scanner(System.in);

        // ── Login gate ────────────────────────────────────────
        System.out.println("=== Welcome to " + pShop.getShopName() + " ===");
        Staff loggedInStaff = null;
        String username = "";
        String password = "";

        while (loggedInStaff == null) {

            do {
                try {
                    System.out.print("Username: ");
                    username = scanner.nextLine().trim();

                    if (username.isEmpty()) {
                        throw new IllegalArgumentException("Username cannot be empty!");
                    }
                    if (username.matches(".*\\d.*")) {
                        throw new IllegalArgumentException("Username cannot contain numbers!");
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (true);
            do {
                try {
                    System.out.print("Password: ");
                    password = scanner.nextLine().trim();
                    if (password.isEmpty()) {
                        throw new IllegalArgumentException("Password cannot be empty!");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    System.out.println("Finished!");
                }

            } while (true);
            loggedInStaff = pShop.login(username, password);
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
            System.out.println("6). View orders");
            System.out.println("7). View Patients");
            System.out.println("8). Get receipt");
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
                    int role = 0;

                    try {

                        role = Integer.parseInt(scanner.nextLine().trim());
                        if (role >= 3 || role <= 0) {
                            System.out.println("Invalid Role!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                        break;
                    }

                    System.out.print("Full Name: ");
                    String fullName = scanner.nextLine().trim();
                    System.out.print("Staff ID: ");
                    String staffID = scanner.nextLine().trim();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine().trim();
                    System.out.print("Password: ");
                    String sPass = scanner.nextLine().trim();
                    System.out.print("Username: ");
                    String sUser = scanner.nextLine().trim();
                    System.out.print("Salary: ");
                    double salary;
                    try {
                        salary = Double.parseDouble(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary.");
                        break;
                    }
                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();

                    Staff newStaff;
                    if (role == 1) {
                        newStaff = new Pharmacist(fullName, staffID, phone, sPass,
                                "Pharmacist", true, sUser, salary, email);
                    } else if (role == 2) {
                        newStaff = new ManagerStaff(fullName, staffID, phone, sPass,
                                "Manager", true, sUser, salary, email, 0);
                    } else {
                        System.out.println("Invalid role.");
                        break;
                    }
                    pShop.createStaff(newStaff);
                    break;

                case 2: // Create Order
                    if (!pShop.hasInventory()) {
                        System.out.println("No medicines available in the inventory.");
                        break;
                    }

                    pShop.checkMenu();
                    if (!loggedInStaff.can(PharmacyShop.CREATE_ORDER)) {
                        System.out.println("Access denied: you don't have permission to create orders.");
                        break;
                    }
                    System.out.println("-- Create Order --");
                    int pAge = 0;
                    String pName = "";
                    String pSymptom = "";
                    boolean pIns = false;
                    boolean isRunning = true;
                    do {
                        try {
                            System.out.print("Patient name: ");
                            pName = scanner.nextLine().trim();
                            if (pName.isEmpty()) {
                                throw new IllegalArgumentException("Name can not be empty!");
                            }
                            System.out.print("Symptom: ");
                            pSymptom = scanner.nextLine().trim();
                            if (pSymptom.isEmpty()) {
                                throw new IllegalArgumentException("Symptom can't be empty");
                            }
                            System.out.print("Age: ");
                            pAge = Integer.parseInt(scanner.nextLine().trim());
                            System.out.print("Has insurance? (true/false): ");
                            pIns = Boolean.parseBoolean(scanner.nextLine().trim());

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        } finally {
                            System.out.println("Finished!");
                            isRunning = false;
                        }
                    } while (isRunning);

                    Patient orderPatient = new Patient(pName, pSymptom, pAge, pIns);
                    pShop.createPatient(orderPatient); // also save the patient
                    pShop.createOrder(orderPatient, loggedInStaff);
                    break;

                case 3: // Create Medicine
                    if (!loggedInStaff.can(PharmacyShop.CREATE_MENU_ITEM)) {
                        System.out.println("Access denied: you don't have permission to add medicines.");
                        break;
                    }
                    System.out.println("-- Add Medicine --");
                    String medName = "";
                    do {
                        try {
                            System.out.print("Medicine name: ");
                            medName = scanner.nextLine().trim();
                            if (medName.isEmpty()) {
                                System.out.println("Medicine name cannot be empty");
                                continue;
                            }
                            if (medName.matches(".*\\d.*")) {
                                System.out.println("medicine name cannot contain numbers!");
                                continue;
                            }
                            if (!medName.matches("[a-zA-Z ]+")) {
                                System.out.println("Medicine name can not contain special letters!");
                                continue;
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());

                        }
                    } while (true);

                    int qty = 0;

                    do {
                        System.out.print("Quantity: ");
                        String input = scanner.nextLine().trim();
                        try {
                            if (input.isEmpty()) {
                                System.out.println("Quantity cannot be empty!");
                                continue;
                            }
                            qty = Integer.parseInt(input);
                            if (qty <= 0 || qty >= 1000) {
                                System.out.println("Invalid quantity.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Number only!");
                        }
                    } while (true);
                    double price = 0.00;
                    do {
                        try {
                            System.out.print("Price per piece: ");
                            String medPrice = scanner.nextLine().trim();
                            if (medPrice.isEmpty()) {
                                System.out.println("Medicine price can't be empty!");
                                continue;
                            }
                            if (!medPrice.matches("[0-9.]+")) {
                                System.out.println("Numbers only, no special characters!");
                                continue;
                            }
                            price = Double.parseDouble(medPrice);
                            if (price <= 0.25 || price >= 70) {
                                System.out.println("Invalid price.");
                                continue;
                            }
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price.");
                        }
                    } while (true);

                    pShop.createMenuItem(new Medicine(medName, qty, price));
                    System.out.println("Medicine \"" + medName + "\" added. Total: " + pShop.getMedicineCount());
                    break;

                case 4: // Create Patient
                    if (!loggedInStaff.can(PharmacyShop.CREATE_PATIENT)) {
                        System.out.println("Access denied: you don't have permission to create patients.");
                        break;
                    }
                    System.out.println("-- Create Patient --");
                    String name = "";
                    do {
                        try {
                            System.out.print("Name: ");
                            name = scanner.nextLine().trim();
                            if (name.isEmpty()) {
                                System.out.println("Patient name cannot be empty");
                                continue;
                            }
                            if (name.matches(".*\\d.*")) {
                                System.out.println("Patient name cannot contain numbers!");
                                continue;
                            }
                            if (!name.matches("[a-zA-Z ]+")) {
                                System.out.println("Patient name can not contain special letters!");
                                continue;
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } while (true);
                    String symptom = "";
                    do {
                        try {

                            System.out.print("Symptom: ");
                            symptom = scanner.nextLine().trim();
                            if (symptom.isEmpty()) {
                                throw new IllegalArgumentException("symptom cannot be empty!");
                            }
                            if (symptom.matches(".*\\d.*")) {
                                throw new IllegalArgumentException("symptom cannot contain numbers!");
                            }
                            if (!symptom.matches("[a-zA-Z ]+")) {
                                System.out.println("Patient name can not contain special letters!");
                                continue;
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());

                        }
                    } while (true);

                    int cAge = 0;
                    do {
                        System.out.print("Age: ");
                        String input = scanner.nextLine().trim();
                        try {
                        if (input.isEmpty()) {
                            System.out.println("Age cannot be empty!");
                            continue;
                        }
                            if (!input.matches("[0-9]+")) {
                                System.out.println("Numbers only, no characters!");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Unable to identified!");
                        }
                    } while (true);

                        String insInput = scanner.nextLine().trim();
                    do {
                        System.out.print("Has insurance? (yes/no): ");
                         insInput = scanner.nextLine().trim();
                        if (insInput.isEmpty()) {
                            System.out.println("Cannot be empty!");
                            continue;
                        }

                        if (!insInput.equalsIgnoreCase("yes") && !insInput.equalsIgnoreCase("no")) {
                            System.out.println("Please enter yes or no only!");
                            continue;
                        }
                        break;
                    }while(true);
                    boolean cIns = Boolean.parseBoolean(insInput);

                    pShop.createPatient(new Patient(name, symptom, cAge, cIns));
                    System.out.println("Patient \"" + name + "\" created successfully.");
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
                    if (!pShop.hasOrders()) {
                        System.out.println("No orders available.");
                        break;
                    }

                    pShop.viewOrders(); // show list first so user knows which ID to pick

                    System.out.print("Enter Order ID to print receipt: ");
                    int receiptId;
                    try {
                        receiptId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                        break;
                    }
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
