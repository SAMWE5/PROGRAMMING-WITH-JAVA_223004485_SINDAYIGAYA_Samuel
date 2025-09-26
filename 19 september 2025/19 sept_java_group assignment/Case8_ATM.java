// Case8_ATM.java
import java.util.*;

class ATM {
    private double balance;
    private final String pin; // stored PIN

    public ATM(String pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, balance);
    }
}

public class Case8_ATM {
    public static void main(String[] args) {
        ATM atm = new ATM("1234", 5000); // initial balance 5000
        String enteredPin = "1234"; // simulated input

        if (!atm.authenticate(enteredPin)) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        // simulate deposit 10000 then withdraw 3000
        atm.deposit(10000);
        atm.withdraw(3000);

        System.out.printf("Final balance: %.2f%n", atm.checkBalance());
    }
}
