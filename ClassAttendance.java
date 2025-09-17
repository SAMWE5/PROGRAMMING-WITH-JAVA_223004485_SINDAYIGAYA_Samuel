package Case3;


	import java.util.Scanner;

	public class ClassAttendance {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Enter total number of students in the class: ");
	        int totalStudents = sc.nextInt();
	        sc.nextLine(); 

	        int[] dailyAttendance = new int[30];
	        int dayCount = 0;

	        String choice="aa";


	        do {
	            if (dayCount >= 30) {
	                System.out.println("Maximum of 30 days reached.");
	                break;
	            }

	            System.out.print("Enter number of students present for Day " + (dayCount + 1) + ": ");
	            int present = sc.nextInt();
	            sc.nextLine(); 
	            if (present < 0 || present > totalStudents) {
	                System.out.println("Invalid number! Must be between 0 and " + totalStudents);
	                continue; 
	            }

	            dailyAttendance[dayCount] = present;
	            dayCount++;

	            while (true) {
	                System.out.print("Do you want to enter attendance for another day? (yes/no): ");
	                choice = sc.nextLine().trim().toLowerCase();

	                if (choice.equals("yes") || choice.equals("no")) {
	                    break; 
	                } else {
	                    System.out.println("Invalid input! Please type 'yes' or 'no'.");
	                }
	            }

	        } while (choice.equals("yes"));

	        
	        int totalPresent = 0;
	        int lowAttendanceDays = 0;
	        System.out.println("\n========== ATTENDANCE RECORD ==========");
	        System.out.println("Day\tNumber Present");
	        
	        for (int i = 0; i < dayCount; i++) {
	            System.out.println((i + 1) + "\t" + dailyAttendance[i]);

	            totalPresent += dailyAttendance[i];

	            // Count days with attendance below 50%
	            if (dailyAttendance[i] < (totalStudents / 2.0)) {
	                lowAttendanceDays++;
	            }
	        }

	        double averageAttendance = totalPresent / (double) dayCount;
	        double lowAttendancePercentage = (lowAttendanceDays * 100.0) / dayCount;

	        
	        System.out.println("Average Attendance: " + (int)averageAttendance + " students");
	        System.out.println("Days with low attendance (<50%): " + lowAttendanceDays 
	                           + " (" + (int)lowAttendancePercentage + "%)");
	        

	        sc.close();
	    }
	}

