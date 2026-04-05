package other;

public class Order {

    private int itemCount;
    private int orderId;
    private boolean isPaid;
    private double total;
    private int quantity;

    // primitive
    private double snapshotPrice; // primitive double — value copy
    private String snapshotMedName; // String is immutable — safe snapshot

    // Reference 
    private Patient patient;

    private String pharmacistName;

    public Order(Patient p, Medicine m, int quantity, String pharmacistName) {
        this.patient = p; // reference copy — shares object
        this.snapshotMedName = m.getName(); // String value snapshot
        this.snapshotPrice = m.getPrice(); // primitive double — value snapshot
        this.quantity = quantity;
        this.pharmacistName = pharmacistName;
        this.orderId = (int) (Math.random() * 9000) + 1000;

        if (m.isAvailable() && m.getStock() >= quantity) {
            this.total = m.getPrice() * quantity; // computed from snapshot price
            this.isPaid = false;
        } else {
            System.out.println("Not enough stock for: " + m.getName());
            this.total = 0.00;
        }
    }

    // ── Getters ───────────────────────────────────────────────
    public Integer getOrderId() {return orderId;}

    public Double getTotal() {return total;}

    public Boolean isPaid() {return isPaid;}

    public Integer getItemCount() {return itemCount;}

    public double getSnapshotPrice() {return snapshotPrice;}

    public String getSnapshotMedName() {return snapshotMedName;}

    public Patient getPatient() {return patient;}

    public String getMedName() {return snapshotMedName;}

    public int getQuantity() {return quantity;}

    // kept for backward compatibility
    public Double getSnapshotPrices() {
        return snapshotPrice;
    }

    // ── Mark as paid ──────────────────────────────────────────
    public void markAsPaid() {
        this.isPaid = true;
    }

    // ── Receipt ───────────────────────────────────────────────
    public String receipt() {
        return "==============================\n"
                + "           RECEIPT            \n"
                + "==============================\n"
                + "Order ID   : #" + orderId + "\n"
                + "Patient    : " + patient.getName() + "\n"
                + "Medicine   : " + snapshotMedName + "\n"
                + "Quantity   : " + quantity + "\n"
                + "Unit Price : $" + snapshotPrice + "\n"
                + "Total      : $" + total + "\n"
                + "Sold By    : " + pharmacistName + "\n"
                + "Paid       : " + (isPaid ? "Yes" : "No") + "\n"
                + "==============================";
    }

    @Override
    public String toString() {
        return "Order #" + orderId
                + " | Patient: " + patient.getName()
                + " | Medicine: " + snapshotMedName
                + " | Qty: " + quantity
                + " | Total: $" + total
                + " | Paid: " + (isPaid ? "Yes" : "No");
    }
}