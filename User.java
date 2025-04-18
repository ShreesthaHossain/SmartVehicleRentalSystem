public class User {
    String name;
    String role;
    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public String toString() {
        return name + " (" + role + ")";
    }
}