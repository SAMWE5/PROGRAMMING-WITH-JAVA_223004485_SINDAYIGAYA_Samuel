package COM.GRADINGSYSTEM;

import java.util.Scanner;

public class StudentGradingSystem {

	
	public static void main(String[] args) {
		   Scanner sc = new Scanner(System.in);

	        int totalStudents = 0;
	        int passCount = 0;
	        int failCount = 0;

	        System.out.println("Enter student marks (0-100). Enter -1 to stop.");

	        while (true) {
	            System.out.print("Enter marks: ");
	            int marks = sc.nextInt();

	            
	            if (marks == -1) {
	                break;
	            }

	            if (marks < 0 || marks > 100) {
	                System.out.println("Invalid marks! Enter a value between 0 and 100.");
	                continue; // skip this iteration
	            }

	            totalStudents++;

	            char grade;
	            if (marks >= 80) {
	                grade = 'A';
	            } else if (marks >= 70) {
	                grade = 'B';
	            } else if (marks >= 60) {
	                grade = 'C';
	            } else if (marks >= 50) {
	                grade = 'D';
	            } else {
	                grade = 'F';
	            }

	           
	            if (marks >= 50) {
	                passCount++;
	            } else {
	                failCount++;
	            }

	          
	            System.out.println("Student Grade: " + grade + "\n");
	        }

	      
	        System.out.println("\n========== CLASS SUMMARY ==========");
	        System.out.println("Total Students: " + totalStudents);
	        System.out.println("Passed: " + passCount);
	        System.out.println("Failed: " + failCount);

	        double passRate = 0;
	        if (totalStudents > 0) {
	            passRate = (passCount * 100.0) / totalStudents; // percentage
	        }
	        System.out.println("Class Pass Rate: " + (int)passRate + "%");
	        

	        sc.close();
	    }
	}