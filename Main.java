import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Setup Shop and Staff
        //ArrayList<PharmacyShop> shop = new ArrayList<PharmacyShop>();
        PharmacyShop shop = new PharmacyShop("HealthPlus Pharmacy", new ArrayList<Medicine>(), 0, 1001);
        Pharmacist pharmacist = new Pharmacist("P123", "Dr. Smith", "1234567890", "password123", "Pharmacist", "drsmith", true);
        ManagerStaff manager = new ManagerStaff("M456", "Jane Doe", "0987654321", "adminpass", "Manager", true, "janedoe");
        Patient patient = new Patient(new ArrayList<String>(java.util.Arrays.asList("John Doe")), new ArrayList<String>(java.util.Arrays.asList("Cough")), new ArrayList<Integer>(java.util.Arrays.asList(30)), new ArrayList<Boolean>(java.util.Arrays.asList(true))) ;       
        // 2. Add Medicine to Shop
        shop.addMedicine("Vitamin C", 98.00, "B001", 50, true);
        shop.addMedicine("Paracetamol", 12.50, "B002", 100, true);
        shop.addMedicine("Amoxicillin", 45.00, "B003", 30, true);
        shop.addMedicine("Ibuprofen", 25.00, "B004", 80, true);
        shop.addMedicine("Cough Syrup", 15.00, "B005", 40, true);
        shop.addMedicine("Antibiotic Ointment", 20.00, "B006", 60, true);
        System.out.println(shop.toString());


    
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
