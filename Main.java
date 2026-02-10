public class Main {
    public static void main(String[] args) {
        // 1. Setup Shop and Staff
        PharmacyShop shop = new PharmacyShop("City Pharmacy", 10);
        Pharmacist staff = new Pharmacist("Dr. Kim", 800.0);
        Patient patient = new Patient("Vibol", 50.0, 25);

        // 2. Add Medicine to Shop
        shop.addMedicine("Vitamin C", 5.00);
        shop.printMenu();

        // 3. Create Order
        
        System.out.println("\n--- F4 Snapshot Proof ---");
        Order order1 = shop.createOrder(patient, staff);
        
        // 4. Find item and add to order
        Medicine vitC = shop.findMedicineByName("Vitamin C");
        if (vitC != null) {
            order1.addItem(vitC); // Price 5.00 saved to snapshot
            System.out.println("Added Vitamin C at price: " + vitC.price);
        }

        // 5. CHANGE the original price in the shop
        vitC.price = 20.00;
        System.out.println("Price changed in shop to: " + vitC.price);

        // 6. Check Order Total (Should still be 5.00)
        System.out.println("Order Total (Should be 5.00): " + order1.getTotal());
        System.out.println(order1.receipt());
    }
}
