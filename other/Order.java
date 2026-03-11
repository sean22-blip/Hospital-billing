package other;

import user.Pharmacist;

public class Order {
    // Primitives
    private int itemCount;
    private int orderId;
    private boolean isPaid;
    private double total;
    private int quantity;

    private String medName;
    private Double snapshotPrices; // Primitive array stores price at moment of buy

    public Order(Patient p, Medicine m) {
        this.orderId = orderId;
        this.quantity = quantity;


        if (m.isAvailable() && m.getStock() >= quantity) {
            this.total = m.getPrice() * quantity;
        }
    }

    // public Patient getPatient() {
    //     return patient;
    // }

    // public Pharmacist getPharmacist() {
    //     return pharmacist;

    // }

    // public String getItemNames() {
    //     return itemNames;
    // }

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

    // public void calculateTotal(String medicineName, int quantity) {
    //     if (medicineName.equals(medicine.getName()) && medicine.isAvailable() && medicine.getStock() >= quantity) {
    //         total = medicine.getPrice() * quantity;
    //     } else {
    //         total = 0.00;
    //     }
    // }

    public Double getTotal() {
        return total;
    }

    // public String receipt() {
    //     return "Receipt #" + orderId
    //      + " | Sold By: " + pharmacist.getFullname() 
    //      + " |Patient: " + patient.toString() 
    //      + " | Total: $" + total;
    // }
}
