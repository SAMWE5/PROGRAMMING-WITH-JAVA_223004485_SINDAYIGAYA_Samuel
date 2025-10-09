//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
// Case6_Library.java
import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title; this.author = author; this.available = true;
    }

    public boolean borrow() {
        if (!available) {
            System.out.println(title + " is already borrowed.");
            return false;
        }
        available = false;
        System.out.println("You borrowed: " + title);
        return true;
    }

    public void returnBook() {
        if (available) {
            System.out.println(title + " was not borrowed.");
        } else {
            available = true;
            System.out.println("You returned: " + title);
        }
    }

    public boolean isAvailable() { return available; }

    @Override
    public String toString() {
        return title + " by " + author + " | " + (available ? "Available" : "Borrowed");
    }
}

public class Case6_Library {
    public static void main(String[] args) {
        Book b1 = new Book("1984", "George Orwell");
        Book b2 = new Book("The Alchemist", "Paulo Coelho");
        Book b3 = new Book("Clean Code", "Robert C. Martin");

        Book[] library = {b1, b2, b3};

        // Simulate borrowing and returning
        b1.borrow();          // borrow 1984
        b1.borrow();          // try again -> already borrowed
        b2.borrow();          // borrow The Alchemist
        b1.returnBook();      // return 1984
        b3.returnBook();      // return Clean Code (wasn't borrowed)

        // Print available books
        System.out.println("\nAvailable books:");
        for (Book b : library) {
            if (b.isAvailable()) System.out.println(b);
        }
    }
}

