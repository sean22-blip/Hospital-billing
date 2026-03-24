package other;

public class Order {
    private int itemCount;
    private int orderId;
    private boolean isPaid;
    private double total;
    private int quantity;
    private String medName;
    private Double snapshotPrices;
    private Patient patient;
    private String pharmacistName;

    public Order(Patient p, Medicine m, int quantity, String pharmacistName) {
        this.patient        = p;
        this.medName        = m.getName();
        this.quantity       = quantity;
        this.pharmacistName = pharmacistName;
        this.snapshotPrices = m.getPrice();
        this.orderId        = (int)(Math.random() * 9000) + 1000; // random 4-digit ID

        if (m.isAvailable() && m.getStock() >= quantity) {
            this.total  = m.getPrice() * quantity;
            this.isPaid = false;
        } else {
            System.out.println("Not enough stock for: " + m.getName());
            this.total = 0.00;
        }
    }

    // ── Getters ───────────────────────────────────────────────
    public Integer getOrderId()        { return orderId; }
    public Double  getTotal()          { return total; }
    public Boolean isPaid()            { return isPaid; }
    public Integer getItemCount()      { return itemCount; }
    public Double  getSnapshotPrices() { return snapshotPrices; }
    public Patient getPatient()        { return patient; }
    public String  getMedName()        { return medName; }
    public int     getQuantity()       { return quantity; }

    // ── Receipt ───────────────────────────────────────────────
    public String receipt() {
        return  "==============================\n"
              + "           RECEIPT            \n"
              + "==============================\n"
              + "Order ID   : #" + orderId        + "\n"
              + "Patient    : " + patient.getName() + "\n"
              + "Medicine   : " + medName           + "\n"
              + "Quantity   : " + quantity           + "\n"
              + "Unit Price : $" + snapshotPrices    + "\n"
              + "Total      : $" + total             + "\n"
              + "Sold By    : " + pharmacistName     + "\n"
              + "Paid       : " + (isPaid ? "Yes" : "No") + "\n"
              + "==============================";
    }

    @Override
    public String toString() {
        return "Order #" + orderId
             + " | Patient: "  + patient.getName()
             + " | Medicine: " + medName
             + " | Qty: "      + quantity
             + " | Total: $"   + total
             + " | Paid: "     + (isPaid ? "Yes" : "No");
    }
}