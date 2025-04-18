
import java.time.LocalDate;

public class Rental {
    String user;
    String vehicleId;
    int days;
    double total;
    String paymentMethod;
    LocalDate rentDate;
    LocalDate returnDate;
    boolean returned;
    String feedback;

    public Rental(String user, String vehicleId, int days, double total, String paymentMethod) {
        this.user = user;
        this.vehicleId = vehicleId;
        this.days = days;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.rentDate = LocalDate.now();
        this.returnDate = rentDate.plusDays(days);
        this.returned = false;
        this.feedback = "";
    }

    public void returnRental(String feedback) {
        this.returned = true;
        this.feedback = feedback;
    }

    public boolean isReturned() {
        return returned;
    }

    public String toString() {
        String status = returned ? "✅" : "❌";
        return String.format("%-10s | %-7s | %2dd | $%-7.2f | %s | %s | %s | Feedback: %s",
                user, vehicleId, days, total, paymentMethod, returnDate, status, feedback);
    }
}
