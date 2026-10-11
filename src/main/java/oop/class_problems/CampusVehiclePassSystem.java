package oop.class_problems;

import java.util.*;

public class CampusVehiclePassSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String passNumber = sc.next();
                String owner = sc.next();

                Vehicle vehicle = null;

                switch (type) {
                    case "Bike":
                        vehicle = new Bike(passNumber, owner);
                        break;
                    case "Car":
                        vehicle = new Car(passNumber, owner);
                        break;
                    case "EBike":
                        vehicle = new ElectricBike(passNumber, owner);
                        break;
                    case "ECar":
                        vehicle = new ElectricCar(passNumber, owner);
                        break;
                }

                if (vehicle != null) {
                    vehicles.put(passNumber, vehicle);
                    System.out.println(
                            passNumber + " (" + type + ") pass fee "
                                    + vehicle.getPassFee()
                    );
                }

            } else if (operation.equals("CHARGE")) {
                String passNumber = sc.next();
                Vehicle vehicle = vehicles.get(passNumber);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                } else if (vehicle instanceof Chargeable) {
                    System.out.println(
                            passNumber + " charging bay allotted"
                    );
                } else {
                    System.out.println(
                            passNumber + " rejected: charging unsupported"
                    );
                }
            }
        }

        sc.close();
    }
}

abstract class Vehicle {
    protected String passNumber;
    protected String owner;

    public Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    public abstract int getPassFee();
}

interface Chargeable {
}

class Bike extends Vehicle {
    public Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    public int getPassFee() {
        return 300;
    }
}

class Car extends Vehicle {
    public Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    public int getPassFee() {
        return 1000;
    }
}

class ElectricBike extends Vehicle implements Chargeable {
    public ElectricBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    public int getPassFee() {
        return 300;
    }
}

class ElectricCar extends Vehicle implements Chargeable {
    public ElectricCar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    public int getPassFee() {
        return 1000;
    }
}