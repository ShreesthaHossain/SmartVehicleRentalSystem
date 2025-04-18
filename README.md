# SmartVehicleRentalSystem

🚗 Smart Vehicle Rental System

A Java-based CLI application that simulates a smart and scalable vehicle rental system. Built using Object-Oriented Programming (OOP) principles such as inheritance, abstraction, encapsulation, and file handling.

🧠 Features-----------------------------

🔐 User Authentication
- Register as `Admin` or `Customer`
- Secure login system with role-based access

👤 Customer Functionalities
- View available vehicles by category (Car, Bike, Truck)
- Rent vehicles with payment simulation (`Bkash` or `Card`)
- Return vehicles with optional feedback
- View rental history in a tabular format

🛠️ Admin Functionalities
- Add or delete vehicles by type
- View all vehicles categorized by type
- View all rental history
- View all registered users
- Generate earnings report (monthly & total)

✅ Status Indicators
- ✅ Available / Returned
- ❌ Unavailable / Not Returned

💾 Persistent Storage
- Rentals and Users are stored in `rentals.txt` and `users.txt`

💻 Technologies Used
- Java 17+
- Command-line Interface (CLI)
- Java Collections Framework
- File I/O (`BufferedReader`, `BufferedWriter`)
- `LocalDate` for date handling
- ASCII table formatting

📂 Project Structure
SmartVehicleRentalSystem/
│
├── Main.java                # Entry point of the application
├── Vehicle.java             # Abstract base class
├── Car.java | Bike.java | Truck.java
├── Rental.java              # Represents a single rental instance
├── User.java                # Represents user information
├── VehicleManager.java      # Manages vehicle-related operations
├── RentalManager.java       # Manages rental operations and reports
├── UserManager.java         # Manages login/registration
├── rentals.txt              # Rental history (auto-created)
├── users.txt                # Registered users (auto-created)


