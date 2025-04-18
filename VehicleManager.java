import java.util.ArrayList;
public class VehicleManager {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }
    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.id.equals(id)) return v;
        }
        return null;
    }
    public void removeVehicle(String id) {
        vehicles.removeIf(v -> v.id.equals(id));
    }
    public void listVehiclesByType() {
        System.out.println("\n🚗 Cars:");
        for (Vehicle v : vehicles) if (v instanceof Car) System.out.println(v);
        System.out.println("\n🏍️ Bikes:");
        for (Vehicle v : vehicles) if (v instanceof Bike) System.out.println(v);
        System.out.println("\n🚚 Trucks:");
        for (Vehicle v : vehicles) if (v instanceof Truck) System.out.println(v);
    }
    public void listAvailableVehiclesByType() {
        System.out.println("\n🚗 Available Cars:");
        for (Vehicle v : vehicles) if (v instanceof Car && v.available) System.out.println(v);
        System.out.println("\n🏍️ Available Bikes:");
        for (Vehicle v : vehicles) if (v instanceof Bike && v.available) System.out.println(v);
        System.out.println("\n🚚 Available Trucks:");
        for (Vehicle v : vehicles) if (v instanceof Truck && v.available) System.out.println(v);
    }
}