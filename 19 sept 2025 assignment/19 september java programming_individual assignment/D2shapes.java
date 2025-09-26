// SINDAYIGAYA Samuel 223004485
package Shapes;

import java.util.Scanner;

// Abstract base class
abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract void display();
}

// Rectangle class
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public void display() {
        System.out.println("Rectangle -> Length: " + length + ", Width: " + width);
    }
}

// Circle class
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void display() {
        System.out.println("Circle -> Radius: " + radius);
    }
}

// Square class
class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public void display() {
        System.out.println("Square -> Side: " + side);
    }
}

// Triangle class
class Triangle extends Shape {
    private double a, b, c, height;

    public Triangle(double a, double b, double c, double height) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * b * height; // base = b, height = h
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public void display() {
        System.out.println("Triangle -> Sides: " + a + ", " + b + ", " + c + " | Height: " + height);
    }
}

// Main Program
public class D2shapes2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\nSelect a Shape:");
            System.out.println("1. Rectangle");
            System.out.println("2. Circle");
            System.out.println("3. Square");
            System.out.println("4. Triangle");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            Shape shape = null;

            switch (choice) {
                case 1:
                    System.out.print("Enter length: ");
                    double length = sc.nextDouble();
                    System.out.print("Enter width: ");
                    double width = sc.nextDouble();
                    shape = new Rectangle(length, width);
                    break;
                case 2:
                    System.out.print("Enter radius: ");
                    double radius = sc.nextDouble();
                    shape = new Circle(radius);
                    break;
                case 3:
                    System.out.print("Enter side: ");
                    double side = sc.nextDouble();
                    shape = new Square(side);
                    break;
                case 4:
                    System.out.print("Enter side a: ");
                    double a = sc.nextDouble();
                    System.out.print("Enter side b (base): ");
                    double b = sc.nextDouble();
                    System.out.print("Enter side c: ");
                    double c = sc.nextDouble();
                    System.out.print("Enter height: ");
                    double height = sc.nextDouble();
                    shape = new Triangle(a, b, c, height);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            System.out.println("\nWould you like to calculate:");
            System.out.println("A. Area");
            System.out.println("B. Perimeter");
            System.out.print("Enter choice (A/B): ");
            char calcChoice = sc.next().toUpperCase().charAt(0);

            shape.display();
            if (calcChoice == 'A') {
                System.out.println("Area = " + shape.getArea());
            } else if (calcChoice == 'B') {
                System.out.println("Perimeter = " + shape.getPerimeter());
            } else {
                System.out.println("Invalid option!");
            }

            System.out.println("\nWould you like to continue?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int cont = sc.nextInt();

            if (cont != 1) {
                continueProgram = false;
                System.out.println("Thank you!");
            }
        }
        sc.close();
    }
}
