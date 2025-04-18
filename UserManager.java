
import java.util.*;
import java.io.*;

public class UserManager {
    private Map<String, String> credentials = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private final String FILE = "users.txt";

    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String pass = parts[1];
                    String role = parts[2];
                    credentials.put(name, pass);
                    users.put(name, new User(name, role));
                }
            }
        } catch (IOException e) {
            System.out.println("No user data found. Starting fresh.");
        }
    }

    public User login(String name, String pass) {
        if (credentials.containsKey(name) && credentials.get(name).equals(pass)) {
            return users.get(name);
        }
        return null;
    }

    public void register(User user, String pass) {
        credentials.put(user.name, pass);
        users.put(user.name, user);
        saveToFile(user.name, pass, user.role);
    }

    public void listUsersTable() {
        System.out.println("\nUsername    | Role");
        System.out.println("-------------------");
        for (User user : users.values()) {
            System.out.printf("%-11s| %-6s\n", user.name, user.role);
        }
    }

    private void saveToFile(String name, String pass, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(name + "," + pass + "," + role);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
}
