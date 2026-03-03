package controller;
import other.Medicine;
import user.ManagerStaff;
import user.Staff;

public class Main {

    public static void main(String[] args) {
        // 1. Setup Shop and Staff
       PharmacyShop shop = new PharmacyShop("Kaisen Pharmacy", "Kasean123");
       
       //Create staff
       Staff staff = new Staff("Alice Johnson", "S001", "1234567890", "staff123", "Staff", true, "alice.johnson");
       
       //set manager
        ManagerStaff manager = new ManagerStaff(staff, 5000.0);

        shop.createMenuItem(new Medicine("Vitamin C", "B001", 50, 98.00));
        shop.createMenuItem(new Medicine("Paracetamol", "B002", 100, 12.50));
        shop.createMenuItem(new Medicine("Amoxicillin", "B003", 30, 45.00));
        shop.createMenuItem(new Medicine("Ibuprofen", "B004", 80, 25.00));
        shop.createMenuItem(new Medicine("Cough Syrup", "B005", 40, 15.00));
        

        System.out.println(shop.toString());
        //set Employees
        //set Patients

        // // 3. Create Order
        // System.out.println("\n--- F4 Snapshot Proof ---");
        // Order order1 = new Order(patient, staff, "Vitamin C", 98.00, 1, 1001, false);
        // // 4. Find item and add to order
        // Medicine vitC = shop.findMedicineByName("Vitamin C");
        // if (vitC != null) {
        //   shop.addMedicine(new ArrayList<String>(java.util.Arrays.asList("Vitamin U", "Vitamind F")), new ArrayList<Double>(java.util.Arrays.asList(8.90, 10.12)));// Price 5.00 saved to snapshot
        //     System.out.println("Added Vitamin C at price" + vitC.getPrice());
        // }
        // // 5. CHANGE the original price in the shop
        //     shop.setPrice(new ArrayList<Double>(java.util.Arrays.asList(65.00)));
        // System.out.println("Price changed in shop to: " + vitC.getPrice());
        // 6. Check Order Total
        //  System.out.println("Order Total: " + order1.getTotal());
        // System.out.println(order1.receipt());
    }
}
