package controller;

import java.util.Scanner;
import user.Staff;

public class Main {

    public static void main(String[] args) throws RuntimeException {

        PharmacyShop pShop = new PharmacyShop("Kaisen", "676767", "st 60m borey-peng-hout psar-kandal battambang",
                "IKAKAKA");
        Scanner scanner = new Scanner(System.in);

        // ── Login gate ────────────────────────────────────────
        Staff loggedInStaff = pShop.handleLogin(scanner);
        // ── Main menu loop ────────────────────────────────────
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1). Create staff");
            System.out.println("2). Create order");
            System.out.println("3). Create medicine (menu item)");
            System.out.println("4). Create patient");
            System.out.println("5). View staff list");
            System.out.println("6). View orders"); // NEW
            System.out.println("7). View Patients"); // NEW
            System.out.println("8). Get receipt"); // NEW
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
                   pShop.handleCreateStaff(scanner);
                    break;

                case 2: // Create Order
                     
                    if (!loggedInStaff.can(PharmacyShop.CREATE_ORDER)) {
                        System.out.println("Access denied: you don't have permission to create orders.");
                        break;
                    }
                    
                    pShop.handleCreateOrder(scanner, loggedInStaff);
                    break;

                case 3: // Create Medicine
                    if (!loggedInStaff.can(PharmacyShop.CREATE_MENU_ITEM)) {
                        System.out.println("Access denied: you don't have permission to add medicines.");
                        break;
                    }
                    
                    pShop.handleCreateMedicine(scanner);
                    break;

                case 4: // Create Patient
                    if (!loggedInStaff.can(PharmacyShop.CREATE_PATIENT)) {
                        System.out.println("Access denied: you don't have permission to create patients.");
                        break;
                    }
                    pShop.handleCreatePatient(scanner);
                    break;

                case 5: // View Staff List
                    System.out.println("-- Staff List --");
                    pShop.handleViewStaffs();
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
                    
                    pShop.handleReceipt(scanner);

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
