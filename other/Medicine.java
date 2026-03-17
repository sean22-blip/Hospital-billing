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

    @Override
    public String toString() {
        return "Medicine{name='" + name + "', price=" + price + "}";
    }

}
