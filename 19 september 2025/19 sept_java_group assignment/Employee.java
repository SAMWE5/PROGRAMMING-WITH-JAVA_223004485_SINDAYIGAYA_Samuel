// Case3_EmployeeSalary.java
import java.util.*;

abstract class Employee {
    protected String name;
    public Employee(String name) { this.name = name; }
    public abstract double calculateSalary();
    public String getName() { return name; }
}

class FullTimeEmployee extends Employee {
    private double baseSalary;

    public FullTimeEmployee(String name, double baseSalary) {
        super(name);
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        double allowances = 0.20 * baseSalary;
        return baseSalary + allowances;
    }

    @Override
    public String toString() {
        return "FullTime: " + name + " | Salary: " + String.format("%.2f", calculateSalary());
    }
}

class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, double hoursWorked, double hourlyRate) {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public String toString() {
        return "PartTime: " + name + " | Salary: " + String.format("%.2f", calculateSalary());
    }
}

public class Case3_EmployeeSalary {
    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee("Alice", 50000);
        Employee e2 = new PartTimeEmployee("Bob", 120, 200);
        Employee e3 = new FullTimeEmployee("Charlie", 70000);

        Employee[] employees = {e1, e2, e3};
        double total = 0;

        for (Employee e : employees) {
            double sal = e.calculateSalary();
            System.out.println(e + (sal > 60000 ? " -> High Earner" : ""));
            total += sal;
        }

        System.out.printf("Total salary expenditure: %.2f%n", total);
    }
}
