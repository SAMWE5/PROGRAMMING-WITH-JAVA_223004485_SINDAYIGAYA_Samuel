//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
// Case7_Vehicles.java
import java.util.*;

class Vehicle {
    protected String regNumber;
    protected double speed;

    public Vehicle(String regNumber, double speed) {
        this.regNumber = regNumber; this.speed = speed;
    }

    public String display() {
        return "Reg: " + regNumber + ", Speed: " + speed;
    }
}

class Car extends Vehicle {
    private double fuelEfficiency; // km per liter

    public Car(String regNumber, double speed, double fuelEfficiency) {
        super(regNumber, speed);
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public String display() {
        return "Car | " + super.display() + ", FuelEff: " + fuelEfficiency;
    }
}

class Bike extends Vehicle {
    private double fuelEfficiency;

    public Bike(String regNumber, double speed, double fuelEfficiency) {
        super(regNumber, speed);
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public String display() {
        return "Bike | " + super.display() + ", FuelEff: " + fuelEfficiency;
    }
}

class Truck extends Vehicle {
    private double capacity; // tons

    public Truck(String regNumber, double speed, double capacity) {
        super(regNumber, speed);
        this.capacity = capacity;
    }

    @Override
    public String display() {
        return "Truck | " + super.display() + ", Capacity: " + capacity + " tons";
    }
}

public class Case7_Vehicles {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Car("CAR-001", 120, 15.5),
            new Bike("BIKE-09", 80, 40.0),
            new Truck("TRK-77", 90, 12.0)
        };

        for (Vehicle v : vehicles) {
            // Demonstrate method overriding
            System.out.println(v.display());
        }
    }
}

