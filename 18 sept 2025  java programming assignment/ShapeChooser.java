package Casestudy4;

import java.util.Scanner;

public class ShapeChooser {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose a shape to calculate area:");
		System.out.println("1. Rectangle");
		System.out.println("2. Circle");
		System.out.print("Enter your choice (1 or 2): ");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			System.out.print("Enter length of rectangle: ");
			double length = sc.nextDouble();
			System.out.print("Enter width of rectangle: ");
			double width = sc.nextDouble();

			Rectangle rect = new Rectangle(length, width);
			System.out.println("Area of Rectangle = " + rect.calculateArea());
			break;

		case 2:
			System.out.print("Enter radius of circle: ");
			double radius = sc.nextDouble();

			Circle circ = new Circle(radius);
			System.out.println("Area of Circle = " + circ.calculateArea());
			break;

		default:
			System.out.println("Invalid choice! Please run the program again.");
		}

		sc.close();
	}
}
