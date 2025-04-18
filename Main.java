import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String CYAN = "\u001B[36m";
    static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleManager vehicleManager = new VehicleManager();
        RentalManager rentalManager = new RentalManager();
        UserManager userManager = new UserManager();

        userManager.loadUsers();
        rentalManager.loadRentalsFromFile();

        // Add sample vehicles (5 of each type)
        vehicleManager.addVehicle(new Car("C1", "Toyota", 45.0));
        vehicleManager.addVehicle(new Car("C2", "Honda", 48.0));
        vehicleManager.addVehicle(new Car("C3", "BMW", 85.0));
        vehicleManager.addVehicle(new Car("C4", "Ford", 50.0));
        vehicleManager.addVehicle(new Car("C5", "Chevy", 40.0));

        vehicleManager.addVehicle(new Bike("B1", "Yamaha", 25.0));
        vehicleManager.addVehicle(new Bike("B2", "Suzuki", 28.0));
        vehicleManager.addVehicle(new Bike("B3", "Bajaj", 20.0));
        vehicleManager.addVehicle(new Bike("B4", "Hero", 22.0));
        vehicleManager.addVehicle(new Bike("B5", "TVS", 23.0));

        vehicleManager.addVehicle(new Truck("T1", "Ford", 70.0));
        vehicleManager.addVehicle(new Truck("T2", "Tata", 75.0));
        vehicleManager.addVehicle(new Truck("T3", "Volvo", 90.0));
        vehicleManager.addVehicle(new Truck("T4", "Ashok Leyland", 80.0));
        vehicleManager.addVehicle(new Truck("T5", "Isuzu", 85.0));

        System.out.println(CYAN + "Welcome to Smart Vehicle Rental System!" + RESET);

        while (true) {
            User currentUser = null;
            while (currentUser == null) {
                System.out.println("\n1. Login\n2. Register\n3. Exit");
                System.out.print("Choose option: ");
                int opt = scanner.nextInt();
                scanner.nextLine();
                if (opt == 1) {
                    System.out.print("Username: ");
                    String name = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    currentUser = userManager.login(name, pass);
                    if (currentUser == null) System.out.println(RED + "Invalid login!" + RESET);
                } else if (opt == 2) {
                    System.out.print("Choose a username: ");
                    String name = scanner.nextLine();
                    System.out.print("Choose a password: ");
                    String pass = scanner.nextLine();
                    System.out.print("Role (admin/customer): ");
                    String role = scanner.nextLine().toLowerCase();
                    currentUser = new User(name, role);
                    userManager.register(currentUser, pass);
                    System.out.println(GREEN + "Registration successful!" + RESET);
                } else if (opt == 3) {
                    System.out.println(GREEN + "Goodbye!" + RESET);
                    System.exit(0);
                } else {
                    System.out.println(RED + "Invalid option." + RESET);
                }
            }

            boolean logout = false;
            while (!logout) {
                System.out.println("\n" + CYAN + "What would you like to do?" + RESET);
                if (currentUser.role.equals("admin")) {
                    System.out.println("1. View all vehicles");
                    System.out.println("2. Add a vehicle");
                    System.out.println("3. Delete a vehicle");
                    System.out.println("4. View all rentals");
                    System.out.println("5. View all users");
                    System.out.println("6. View earnings report");
                    System.out.println("7. Logout");
                } else {
                    System.out.println("1. View available vehicles");
                    System.out.println("2. Rent a vehicle");
                    System.out.println("3. Return a vehicle");
                    System.out.println("4. View my rentals");
                    System.out.println("5. Logout");
                }

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (currentUser.role.equals("admin")) {
                    switch (choice) {
                        case 1 -> vehicleManager.listVehiclesByType();
                        case 2 -> {
                            System.out.print("Enter ID: ");
                            String id = scanner.nextLine();
                            System.out.print("Enter brand: ");
                            String brand = scanner.nextLine();
                            System.out.print("Enter price per day: ");
                            double price = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Type (car/bike/truck): ");
                            String type = scanner.nextLine();
                            switch (type) {
                                case "car" -> vehicleManager.addVehicle(new Car(id, brand, price));
                                case "bike" -> vehicleManager.addVehicle(new Bike(id, brand, price));
                                case "truck" -> vehicleManager.addVehicle(new Truck(id, brand, price));
                                default -> System.out.println(RED + "Invalid type." + RESET);
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter vehicle ID to delete: ");
                            String delId = scanner.nextLine();
                            vehicleManager.removeVehicle(delId);
                            System.out.println(GREEN + "Vehicle removed if existed." + RESET);
                        }
                        case 4 -> rentalManager.viewAllRentals();
                        case 5 -> userManager.listUsersTable();
                        case 6 -> rentalManager.showEarningsReport();
                        case 7 -> logout = true;
                        default -> System.out.println(RED + "Invalid option." + RESET);
                    }
                } else {
                    switch (choice) {
                        case 1 -> vehicleManager.listAvailableVehiclesByType();
                        case 2 -> {
                            System.out.print("Enter vehicle ID: ");
                            String id = scanner.nextLine();
                            Vehicle v = vehicleManager.findVehicleById(id);
                            if (v == null || !v.available) {
                                System.out.println(RED + "Vehicle not found or unavailable." + RESET);
                            } else {
                                System.out.print("Enter number of days: ");
                                int days = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Payment method (Bkash/Card): ");
                                String payment = scanner.nextLine();
                                rentalManager.rentVehicle(currentUser, v, days, payment);
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter vehicle ID to return: ");
                            String retId = scanner.nextLine();
                            System.out.print("Leave feedback: ");
                            String fb = scanner.nextLine();
                            rentalManager.returnVehicle(currentUser.name, retId, fb);
                        }
                        case 4 -> rentalManager.viewUserRentals(currentUser.name);
                        case 5 -> logout = true;
                        default -> System.out.println(RED + "Invalid option." + RESET);
                    }
                }
            }
        }
    }
}
