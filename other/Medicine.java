package other;

public class Medicine {

    private String name;
    private String batchId;

    private int quantity;
    private double price;

    public Medicine(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBatchId() {
        return batchId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public int getStock() {
        return quantity;
    }

    // ── Mutators (used to prove snapshot independence in Main) ─
    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Reduces stock after an order is placed
    public void reduceStock(int qty) {
        if (qty > 0 && qty <= this.quantity) {
            this.quantity -= qty;
        }
    }

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + ", qty=" + quantity + "}";
    }

}
