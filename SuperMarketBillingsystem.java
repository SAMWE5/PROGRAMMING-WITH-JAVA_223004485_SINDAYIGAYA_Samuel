package supermarketbilling;

import java.util.Scanner;

public class SuperMarketBillingsystem {

	public static void main(String[] args) {
	          Scanner sc = new Scanner(System.in);

		        // Step 1: Ask the cashier how many items
		        System.out.print("Enter the number of different items bought: ");
		        int n = sc.nextInt();
		        sc.nextLine(); // consume newline

		        // Step 2: Create arrays to store details
		        String[] itemNames = new String[n];
		        double[] pricePerUnit = new double[n];
		        int[] quantities = new int[n];
		        double[] subtotals = new double[n];
2
		        double totalBill = 0;

		        // Step 3: Use for loop to process all items
		        for (int i = 0; i < n; i++) {
		            System.out.println("\nEnter details for item " + (i + 1) + ":");
		            
		            System.out.print("Item name: ");
		            itemNames[i] = sc.nextLine();

		            System.out.print("Price per unit (RWF): ");
		            pricePerUnit[i] = sc.nextDouble();

		            System.out.print("Quantity purchased (kg): ");
		            quantities[i] = sc.nextInt();
		            sc.nextLine(); // consume newline

		            // Step 4: Calculate subtotal and add to total
		            subtotals[i] = pricePerUnit[i] * quantities[i];
		            totalBill += subtotals[i];
		        }

		        // Step 5: Apply discount if total > 50,000
		        double discount = 0;
		        if (totalBill > 50000) {
		            discount = totalBill * 0.05; // 5% discount
		        }
		        double finalAmount = totalBill - discount;

		        // Step 6: Print receipt with tabs
		        System.out.println("\n========== SUPERMARKET RECEIPT ==========");
		        System.out.println("Item Name\tQuantity\tPrice/Unit\tSubtotal");
		        System.out.println("---------------------------------------------------");
		        for (int i = 0; i < n; i++) {
		            System.out.println(itemNames[i] + "\t\t" 
		                    + quantities[i] + " kg\t\t" 
		                    + pricePerUnit[i] + " RWF\t\t" 
		                    + subtotals[i] + " RWF");
		        }
		        System.out.println("---------------------------------------------------");
		        System.out.println("Grand Total (Before Discount): " + totalBill + " RWF");
		        System.out.println("Discount: " + discount + " RWF");
		        System.out.println("Final Amount Payable: " + finalAmount + " RWF");
		        System.out.println("==========================================");

		        sc.close();
		    }
		

	}


