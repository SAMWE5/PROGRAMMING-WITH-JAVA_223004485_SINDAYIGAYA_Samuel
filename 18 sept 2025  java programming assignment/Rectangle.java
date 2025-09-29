//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033

package Rectcalculator;

import java.util.Scanner;

public class Rectangle {
	private double length;
	private double width;
	public Rectangle(double length,double width){
		this.length=length;
		this.width=width;
		
	}
	public double calculateArea(){
		return length*width;
		
		}
	public boolean isSquare(){
		return length==width;
		
	}
	public static void main(String[] args) {
		Scanner Sc =new Scanner(System.in);
	System.out.println("Enter the length of Rectangle: ");
	 
	double length=Sc.nextDouble();
	System.out.println("Enter the width of Rectangle: ");
	double width = Sc.nextDouble();
	Rectangle rectangle = new Rectangle(length, width);
	double Area = rectangle.calculateArea();
	System.out.println("Area of rectangle: "+ Area);
	if(rectangle.isSquare()){
		System.out.println("this rectangle is a square.");
		
	}else{
		System.out.println("this rectangle is not a square.");
		
	}
	System.out.println("thank you for using the program.");
	Sc.close();
	}

}


