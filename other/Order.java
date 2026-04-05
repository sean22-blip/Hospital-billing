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

    // ── Insurance discount ────────────────────────────────────
    public static final double INSURANCE_DISCOUNT = 0.20; // 20% off
    private boolean hadInsurance; // snapshot of patient's insurance at order time
    private double discountAmount; // how much was saved

    public Order(Patient p, Medicine m, int quantity, String pharmacistName) {
        this.patient = p; // reference copy — shares object
        this.snapshotMedName = m.getName(); // String value snapshot
        this.snapshotPrice = m.getPrice(); // primitive double — value snapshot
        this.hadInsurance = p.HasInsurance(); // snapshot boolean at order time
        this.quantity = quantity;
        this.pharmacistName = pharmacistName;
        this.orderId = (int) (Math.random() * 9000) + 1000;

        if (m.isAvailable() && m.getStock() >= quantity) {
            double rawTotal = m.getPrice() * quantity;
            if (this.hadInsurance) {
                this.discountAmount = rawTotal * INSURANCE_DISCOUNT;
                this.total = rawTotal - this.discountAmount;
                System.out.println("Insurance discount applied: -$" +
                        String.format("%.2f", this.discountAmount));
            } else {
                this.discountAmount = 0.00;
                this.total = rawTotal;
            }
            this.isPaid = false;
        } else {
            System.out.println("Not enough stock for: " + m.getName());
            this.total = 0.00;
            this.discountAmount = 0.00;
        }
    }

    // ── Getters ───────────────────────────────────────────────
    public Integer getOrderId() {
        return orderId;
    }

    public Double getTotal() {
        return total;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public double getSnapshotPrice() {
        return snapshotPrice;
    }

    public String getSnapshotMedName() {
        return snapshotMedName;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getMedName() {
        return snapshotMedName;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getHadInsurance() {
        return hadInsurance;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

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
        double rawTotal = snapshotPrice * quantity;
        String discountLine = hadInsurance
                ? "Discount(20%): -$" + String.format("%.2f", discountAmount) + "\n"
                : "Discount      : None\n";
        return "==============================\n"
                + "           RECEIPT            \n"
                + "==============================\n"
                + "Order ID   : #" + orderId + "\n"
                + "Patient    : " + patient.getName() + "\n"
                + "Insurance  : " + (hadInsurance ? "Yes" : "No") + "\n"
                + "Medicine   : " + snapshotMedName + "\n"
                + "Quantity   : " + quantity + "\n"
                + "Unit Price : $" + String.format("%.2f", snapshotPrice) + "\n"
                + "Subtotal   : $" + String.format("%.2f", rawTotal) + "\n"
                + discountLine
                + "Total      : $" + String.format("%.2f", total) + "\n"
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
                + " | Total: $" + String.format("%.2f", total)
                + (hadInsurance ? " | Discount: -$" + String.format("%.2f", discountAmount) : "")
                + " | Paid: " + (isPaid ? "Yes" : "No");
    }
}