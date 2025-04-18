public class Truck extends Vehicle {
    public Truck(String id, String brand, double pricePerDay) {
        super(id, brand, pricePerDay);
    }
    public String getType() {
        return "Truck";
    }
}