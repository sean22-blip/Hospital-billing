package other;

import user.Pharmacist;
import controller.PharmacyShop;

public class Order {
    // References to Objects
    Medicine medicine; // Single Object
    private Patient patient;
    private Pharmacist pharmacist;
    // Primitives
    private Integer itemCount;
    private Integer orderId;
    private Boolean isPaid;
    private Double total;

    // SNAPSHOT DATA (Arrays to store history)
    private String itemNames;
    private Double snapshotPrices; // Primitive array stores price at moment of buy

    public Order(Patient patient, Pharmacist pharmacist, Integer itemCount, Integer orderId, Boolean isPaid,
            String itemNames, Double snapshotPrices) {
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.itemCount = itemCount;
        this.orderId = orderId;
        this.isPaid = isPaid;
        this.itemNames = itemNames;
        this.snapshotPrices = snapshotPrices;
    }

    public Patient getPatient() {
        return patient;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;

    }

    public String getItemNames() {
        return itemNames;
    }

    public Double getSnapshotPrices() {
        return snapshotPrices;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void getTotal(String medicineName, int quantity) {
        if(medicineName.equals(medicine.getName()) && medicine.isAvailable() && medicine.getStock() >= quantity) {
            total = medicine.getPrice() * quantity;
        }else{
            System.out.println("Medicine not available or insufficient stock.");
        }
        
    }

    public String receipt() {
        return "Receipt #" + orderId + " | Sold By: " + pharmacist.getFullname() + " | Total: $" + total;
    }
}
