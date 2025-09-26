// Case4_ShapeAreaCalculator.java
import java.util.*;

abstract class Shape {
    public abstract double area();
}

class Circle extends Shape {
    private double r;
    public Circle(double r) { this.r = r; }
    @Override public double area() { return Math.PI * r * r; }
    @Override public String toString() { return "Circle(radius=" + r + ") area=" + String.format("%.2f", area()); }
}

class Rectangle extends Shape {
    private double length, width;
    public Rectangle(double l, double w) { this.length = l; this.width = w; }
    @Override public double area() { return length * width; }
    @Override public String toString() { return "Rectangle(" + length + "x" + width + ") area=" + String.format("%.2f", area()); }
}

class Triangle extends Shape {
    private double base, height;
    public Triangle(double base, double height) { this.base = base; this.height = height; }
    @Override public double area() { return 0.5 * base * height; }
    @Override public String toString() { return "Triangle(base=" + base + ", height=" + height + ") area=" + String.format("%.2f", area()); }
}

public class Case4_ShapeAreaCalculator {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(3),
            new Rectangle(4, 5),
            new Triangle(6, 4)
        };

        double totalArea = 0;
        for (Shape s : shapes) {
            System.out.println(s);
            totalArea += s.area();
        }
        System.out.printf("Total area of all shapes: %.2f%n", totalArea);
    }
}
