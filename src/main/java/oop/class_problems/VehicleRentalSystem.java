package oop.class_problems;

import java.util.*;

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Customer customer1 = new Customer("Customer1");
        Customer customer2 = new Customer("Customer2");
        Customer customer3 = new Customer("Customer3");

        RentalManager manager = new RentalManager();

        Rental r1 = manager.rentVehicle(customer1, sedanA, 3);
        if (r1 != null) {
            System.out.println("Sedan A rented successfully by Customer 1.");
            System.out.println("Rental charge: $" + r1.calculateAmount());
        }

        Rental r2 = manager.rentVehicle(customer2, sedanA, 2);
        if (r2 == null) {
            System.out.println("Sedan A is currently unavailable.");
        }

        manager.returnVehicle(r1);
        System.out.println("Sedan A returned by Customer 1.");

        Rental r3 = manager.rentVehicle(customer3, suvB, 5);
        if (r3 != null) {
            System.out.println("SUV B rented successfully by Customer 3.");
            System.out.println("Rental charge:$" + r3.calculateAmount());
        }
    }
}

abstract class Vehicle {
    protected String vehicleId;
    protected boolean available;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 10;
    }
}

class SUV extends Vehicle {
    public SUV(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 15;
    }
}

class Truck extends Vehicle {
    public Truck(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 2000;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public double calculateAmount() {
        return vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class RentalManager {

    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            return null;
        }

        vehicle.setAvailable(false);
        return new Rental(customer, vehicle, days);
    }

    public void returnVehicle(Rental rental) {
        if (rental != null) {
            rental.getVehicle().setAvailable(true);
        }
    }
}
