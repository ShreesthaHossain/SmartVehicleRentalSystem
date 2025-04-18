
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalManager {
    ArrayList<Rental> rentals = new ArrayList<>();
    private final String FILE = "rentals.txt";

    public void rentVehicle(User user, Vehicle vehicle, int days, String paymentMethod) {
        if (vehicle.available) {
            double cost = vehicle.pricePerDay * days;
            Rental rental = new Rental(user.name, vehicle.id, days, cost, paymentMethod);
            rentals.add(rental);
            vehicle.available = false;
            saveRentalToFile(rental);
            System.out.println("Rental successful!\n" + rental);
        } else {
            System.out.println("Vehicle is not available.");
        }
    }

    public void returnVehicle(String username, String vehicleId, String feedback) {
        for (Rental r : rentals) {
            if (r.user.equals(username) && r.vehicleId.equals(vehicleId) && !r.isReturned()) {
                r.returnRental(feedback);
                System.out.println("Vehicle returned. Thank you for your feedback!");
                return;
            }
        }
        System.out.println("No active rental found for this vehicle.");
    }

    public void viewAllRentals() {
        System.out.println("\nAll Rentals:");
        for (Rental r : rentals) {
            System.out.println(r);
        }
    }

    public void viewUserRentals(String username) {
        System.out.println("\nRental History for " + username + ":");
        System.out.println("User       | Vehicle | Days | Total   | Payment | ReturnDate | Status | Feedback");
        System.out.println("----------------------------------------------------------------------------------");
        for (Rental r : rentals) {
            if (r.user.equals(username)) {
                System.out.println(r);
            }
        }
    }

    public void showEarningsReport() {
        System.out.println("\nEarnings Report:");
        Map<String, Double> monthly = new TreeMap<>();
        double total = 0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Rental r : rentals) {
            if (r.returned) {
                String month = r.returnDate.format(fmt);
                monthly.put(month, monthly.getOrDefault(month, 0.0) + r.total);
                total += r.total;
            }
        }

        System.out.println("Month     | Earnings");
        System.out.println("----------------------");
        for (String m : monthly.keySet()) {
            System.out.printf("%-10s| $%.2f\n", m, monthly.get(m));
        }
        System.out.println("----------------------");
        System.out.printf("%-10s| $%.2f\n", "Total", total);
    }

    public void saveRentalToFile(Rental r) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(r.user + "," + r.vehicleId + "," + r.days + "," + r.total + "," +
                         r.paymentMethod + "," + r.rentDate + "," + r.returnDate + "," +
                         r.returned + "," + r.feedback);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving rental.");
        }
    }

    public void loadRentalsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 9) {
                    Rental r = new Rental(parts[0], parts[1], Integer.parseInt(parts[2]),
                                          Double.parseDouble(parts[3]), parts[4]);
                    r.rentDate = LocalDate.parse(parts[5]);
                    r.returnDate = LocalDate.parse(parts[6]);
                    r.returned = Boolean.parseBoolean(parts[7]);
                    r.feedback = parts[8];
                    rentals.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous rental data found.");
        }
    }
}
