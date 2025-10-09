//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
// Case9_HotelBooking.java
import java.util.*;

class Room {
    private String roomNumber;
    private String type; // Standard, Deluxe, Suite
    private double pricePerNight;

    public Room(String roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber; this.type = type; this.pricePerNight = pricePerNight;
    }

    public double calculateCost(int nights) {
        double total = pricePerNight * nights;
        if (nights > 5) {
            total *= 0.85; // 15% discount
        }
        return total;
    }

    @Override
    public String toString() {
        return type + " " + roomNumber + " @ " + pricePerNight;
    }
}

public class Case9_HotelBooking {
    public static void main(String[] args) {
        Room r1 = new Room("101", "Standard", 5000);
        Room r2 = new Room("201", "Deluxe", 8000);
        Room r3 = new Room("301", "Suite", 15000);

        // Guest stays 6 nights in Deluxe room
        int nights = 6;
        Room chosen = r2; // Deluxe
        double bill = chosen.calculateCost(nights);
        System.out.println("Room: " + chosen);
        System.out.println("Nights: " + nights);
        System.out.printf("Total bill (after discount if any): %.2f%n", bill);
    }
}

