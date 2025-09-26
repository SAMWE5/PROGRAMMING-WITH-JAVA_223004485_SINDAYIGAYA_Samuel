// Case5_ShoppingCart.java
import java.util.*;

class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        this.id = id; this.name = name; this.price = price; this.quantity = quantity;
    }

    public double cost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " @ " + price + " => " + String.format("%.2f", cost());
    }
}

public class Case5_ShoppingCart {
    public static void main(String[] args) {
        Product p1 = new Product("P001", "Laptop", 40000, 1);
        Product p2 = new Product("P002", "Mouse", 1000, 2);
        Product p3 = new Product("P003", "Keyboard", 3000, 1);

        Product[] cart = {p1, p2, p3};
        double total = 0;
        for (Product p : cart) {
            System.out.println(p);
            total += p.cost();
        }

        boolean discountApplied = false;
        if (total > 10000) {
            total = total * 0.90; // 10% discount
            discountApplied = true;
        }

        System.out.printf("Total bill (after any discount): %.2f%n", total);
        System.out.println("Discount applied? " + (discountApplied ? "Yes" : "No"));
    }
}
