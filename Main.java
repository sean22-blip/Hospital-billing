import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Setup Shop and Staff
        //ArrayList<PharmacyShop> shop = new ArrayList<PharmacyShop>();
        PharmacyShop shop = new PharmacyShop("Kaisen", 10);
        Pharmacist staff = new Pharmacist("Sreyka", 800);
        Patient patient =new Patient("Rormony", 55.00, 12);
        // new ("City Pharmacy", 10);
        // new Pharmacist("Dr. Kim", 800.0);
        // new Patient("Vibol", 50.0, 25);

        // 2. Add Medicine to Shop
        shop.addMedicine("Vitamin C", 98.00);
        shop.printMenu();


    
        // 3. Create Order
        System.out.println("\n--- F4 Snapshot Proof ---");
        Order order1 = shop.createOrder(patient, staff);
        
        // 4. Find item and add to order
        Medicine vitC = shop.findMedicineByName("Vitamin C");
        if (vitC != null) {
            order1.addItem(vitC); // Price 5.00 saved to snapshot
            System.out.println("Added Vitamin C at price" + vitC.getPrice());
        }

        // 5. CHANGE the original price in the shop
        vitC.price = 65.00;
        System.out.println("Price changed in shop to: " + vitC.getPrice());

        // 6. Check Order Total
        System.out.println("Order Total: " + order1.getTotal());
        System.out.println(order1.receipt());
    }
}
