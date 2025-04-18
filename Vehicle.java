
public abstract class Vehicle {
    String id;
    String brand;
    double pricePerDay;
    boolean available = true;

    public Vehicle(String id, String brand, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }

    public abstract String getType();

    public String toString() {
        String status = available ? "✅" : "❌";
        return getType() + " - ID: " + id + ", Brand: " + brand + ", Price: $" + pricePerDay + ", Available: " + status;
    }
}
