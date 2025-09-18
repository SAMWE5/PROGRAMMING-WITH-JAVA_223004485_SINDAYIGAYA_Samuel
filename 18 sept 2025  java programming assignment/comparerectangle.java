// SINDAYIGAYA Samuel 223004485
//NSABIYUMVA Jean Marie 223008033
import java.util.Scanner;
public class comparerectangle {
	 private double length;
	    private double width;
	    
	    public comparerectangle(double length2, double width2) {
			// TODO Auto-generated constructor stub
		}

		public void CompareRectangle(double length, double width) {
	        this.length = length;
	        this.width = width;
	    }
	    
	    public double calculateArea() {
	        return length * width;
	    }
	}

	class CompareCircle {
	    private double radius;
	    
	    public CompareCircle(double radius) {
	        this.radius = radius;
	    }
	    
	    public double calculateArea() {
	        return Math.PI * radius * radius;
	    }
	}

	public class CompareShapes {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        
	        System.out.println("Enter details for the rectangle:");
	        System.out.print("Enter length: ");
	        double length = scanner.nextDouble();
	        System.out.print("Enter width: ");
	        double width = scanner.nextDouble();
	        
	        comparerectangle rectangle = new comparerectangle(length, width);
	        double rectArea = rectangle.calculateArea();
	        
	        System.out.println("\nEnter details for the circle:");
	        System.out.print("Enter radius: ");
	        double radius = scanner.nextDouble();
	        
	        CompareCircle circle = new CompareCircle(radius);
	        double circleArea = circle.calculateArea();
	        
	        System.out.println("\nRectangle area: " + rectArea);
	        System.out.println("Circle area: " + circleArea);
	        
	        if (rectArea > circleArea) {
	            System.out.println("The rectangle has a larger area.");
	        } else if (circleArea > rectArea) {
	            System.out.println("The circle has a larger area.");
	        } else {
	            System.out.println("Both shapes have the same area.");
	        }
	    
	        scanner.close();
	    }
	}
	

