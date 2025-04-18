public class Bike extends Vehicle {
    public Bike(String id, String brand, double pricePerDay) {
        super(id, brand, pricePerDay);
    }
    public String getType() {
        return "Bike";
    }
}