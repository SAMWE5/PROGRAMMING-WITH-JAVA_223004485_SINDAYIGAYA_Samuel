//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033

package Rectcalculator;

import java.util.Scanner;

public class circle {
private double radius;
public void Circle(double radius) {
    this.radius = radius;
}

public double calculateArea() {
    return Math.PI * radius * radius;
}

public double calculateCircumference() {
    return 2 * Math.PI * radius;
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter the radius of the circle: ");
    double radius = scanner.nextDouble();
    
    circle circle = new circle();
    
    System.out.println("Choose an option:");
    System.out.println("1. Calculate Area");
    System.out.println("2. Calculate Circumference");
    System.out.print("Enter your choice: ");
    
    int choice = scanner.nextInt();
    
    switch (choice) {
        case 1:
            System.out.println("Area: " + circle.calculateArea());
            break;
        case 2:
            System.out.println("Circumference: " + circle.calculateCircumference());
            break;
        default:
            System.out.println("Invalid choice!");
    }
    
    scanner.close();
}
}





