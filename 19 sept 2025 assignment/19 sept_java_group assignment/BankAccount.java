//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618
// Case1_BankAccount.java
import java.util.*;

class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited %.2f into account %s. New balance: %.2f%n", amount, accountNumber, balance);
        checkLowBalance();
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient Funds");
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f from account %s. New balance: %.2f%n", amount, accountNumber, balance);
        checkLowBalance();
    }

    public double getBalance() {
        return balance;
    }

    private void checkLowBalance() {
        if (balance < 1000) {
            System.out.println("Warning: Low Balance");
        }
    }

    @Override
    public String toString() {
        return holderName + " (" + accountNumber + ") - Balance: " + String.format("%.2f", balance);
    }

    // Demo in main
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount("ACC1001", "Alice", 2000);
        BankAccount a2 = new BankAccount("ACC1002", "Bob", 800);

        // deposit 5000 in one (Alice)
        a1.deposit(5000); // Alice: 7000

        // withdraw 2000 from Bob (but Bob has 800 -> insufficient)
        a2.withdraw(2000); // should print "Insufficient Funds"

        // Let's deposit to Bob then withdraw
        a2.deposit(1500); // Bob: 2300
        a2.withdraw(2000); // Bob: 300 (then Low Balance)

        System.out.println("Final balances:");
        System.out.println(a1);
        System.out.println(a2);
    }
}


