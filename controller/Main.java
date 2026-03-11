package controller;

import user.ManagerStaff;
import user.Pharmacist;
import user.Staff;

public class Main {
    public static void main(String[] args) {
        PharmacyShop pShop = new PharmacyShop("Kaisen", "67676", "st 60m borey-peng-hout psar-kandal battambang");
        Staff s1 = new Pharmacist("Sokha", "S001", "012345678", "sokha123", "Manager", true, "Admin", 500.0,
                "fdaff@gmailc.om");
        Staff s2 = new Pharmacist("Sophea", "S002", "0987654321", "sophea123", "Pharmacist", true, "sophea/Pharmacist", 300.0,
                "faek1212@gmail.com");
        pShop.createStaff(new ManagerStaff(s1, 10));
        pShop.createStaff(s2);
        for (Staff p : pShop.getStaffs()) {
            System.out.println(p.getUsername() + " can CREATE_ORDER? " + p.can(PharmacyShop.CREATE_ORDER));
            System.out.println(p.getUsername() + " can CREATE_MENU_ITEM? " + p.can(PharmacyShop.CREATE_MENU_ITEM));
            System.out.println(p.getUsername() + " can VIEW_ORDERS? " + p.can(PharmacyShop.VIEW_ORDERS));

        }
        // CREATE_ORDER
        //         // CREATE_MENU_ITEM
        // VIEW_ORDERS

    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("=== Main menu ===");
    //     System.out.println("1). Create staff");
    //     System.out.println("2). Create order");
    //     System.out.println("3). Create menu item");
    //     System.out.println("4). Create customer");
    //     System.out.println("5). Login to account");
    //     System.out.println("6). Exit the menu");
    //     System.out.println("Enter your option below ");
    //     System.out.print("=> :");
    //     int option = scanner.nextInt();
    //     switch (option) {
    //         case 1: {
    //             System.out.println("=== Verification ===");
    //             scanner.nextLine();
    //             System.out.print("Do you have an account? (yes/no): ");
    //             String acc = scanner.nextLine();
    //             acc.toLowerCase();
    //             if (acc.equals("yes")) {
    //                 System.out.print("Enter your email: ");
    //                 String email = scanner.nextLine();
    //                 System.out.print("Enter your password (forget? type forget): ");
    //                 String password = scanner.nextLine();

    //             } else if (acc.equals("no")) {

    //             } else {
    //                 System.out.println("Must enter yes or no!");
    //             }
    //             break;// add Pharmacist and manager to the code
    //         }
    //         case 2: {
    //             System.out.println("What is your position?: ");
    //             String position = scanner.next();
    //             position = position.toLowerCase();
    //             if (position.equals("manager")) {
    //                 System.out.println("You do not have the permission to create order!");
    //             } else if (position.equals("pharmacist")) {
    //                 // create_order calling from pharmacist!
    //             }
    //         }
    //     }
     }
}
