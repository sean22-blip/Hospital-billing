import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Setup Shop and Staff
        //ArrayList<PharmacyShop> shop = new ArrayList<PharmacyShop>();
        PharmacyShop shop = new PharmacyShop("City Pharmacy", null, 0, 1);
        Pharmacist staff = new Pharmacist("S123", "Dr. Kim", "0123456789", "password123", "Pharmacist", "drkim", true);
        Patient patient = new Patient("Vilbol", 10);
        // new ("City Pharmacy", 10);
        // new Pharmacist("Dr. Kim", 800.0);
        // new Patient("Vibol", 50.0, 25);

        // 2. Add Medicine to Shop
        shop.addMedicine(new ArrayList<String>(java.util.Arrays.asList("Vitamin C", "Vitamind D")), new ArrayList<Double>(java.util.Arrays.asList(5.00, 10.00)));
        shop.printMenu();


    
        // 3. Create Order
        System.out.println("\n--- F4 Snapshot Proof ---");
        Order order1 = new Order(patient, staff, "Vitamin C", 98.00, 1, 1001, false);
        
        // 4. Find item and add to order
        Medicine vitC = shop.findMedicineByName("Vitamin C");
        if (vitC != null) {
          shop.addMedicine(new ArrayList<String>(java.util.Arrays.asList("Vitamin U", "Vitamind F")), new ArrayList<Double>(java.util.Arrays.asList(8.90, 10.12)));// Price 5.00 saved to snapshot
            System.out.println("Added Vitamin C at price" + vitC.getPrice());
        }

        // 5. CHANGE the original price in the shop
            shop.setPrice(new ArrayList<Double>(java.util.Arrays.asList(65.00)));
        System.out.println("Price changed in shop to: " + vitC.getPrice());

        // 6. Check Order Total
        System.out.println("Order Total: " + order1.getTotal());
        System.out.println(order1.receipt());
    }
}
