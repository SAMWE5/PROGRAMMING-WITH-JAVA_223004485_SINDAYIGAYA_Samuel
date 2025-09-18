// SINDAYIGAYA Samuel 223004485
//NSABIYUMVA Jean Marie 223008033
import java.util.Scanner;
public class Rectangle2 {
	  private double length;
	    private double width;
	    public Rectangle2(double length, double width) {
	        this.length = length;
	        this.width = width;
	    }
	    
	    public double calculateArea() {
	        return length * width;
	    }
	    
 public static void main(String[] args) {
	 Scanner scanner = new Scanner(System.in);
     Rectangle2[] rectangles = new Rectangle2[3];
     
     for (int i = 0; i < 3; i++) {
         System.out.println("Enter details for Rectangle " + (i + 1) + ":");
         System.out.print("Enter length: ");
         double length = scanner.nextDouble();
         
         System.out.print("Enter width: ");
         double width = scanner.nextDouble();
         
         rectangles[i] = new Rectangle2(length, width);
     }
     
     System.out.println("\nAreas of the rectangles:");
     for (int i = 0; i < 3; i++) {
         double area = rectangles[i].calculateArea();
         System.out.println("Rectangle " + (i + 1) + " area: " + area);
     }
     
     scanner.close();
 }

		

	}


