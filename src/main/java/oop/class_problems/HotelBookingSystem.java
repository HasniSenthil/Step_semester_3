package oop.class_problems;

import java.util.ArrayList;

public class HotelBookingSystem {

    public static void main(String[] args) {

        Room standard101 = new StandardRoom(101);
        Room deluxe201 = new DeluxeRoom(201);

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        BookingManager manager = new BookingManager();

        System.out.println(
                "Standard Room 101 is available from Jan 1 to Jan 5."
        );

        Reservation reservationA = manager.book(
                customerA,
                standard101,
                "Jan 1",
                "Jan 5"
        );

        if (reservationA != null) {
            System.out.println(
                    "Reservation confirmed for Customer A, "
                    + "Standard Room 101 (Jan 1-5). Price: $"
                    + reservationA.calculateAmount()
            );
        }

        Reservation reservationB = manager.book(
                customerB,
                standard101,
                "Jan 3",
                "Jan 7"
        );

        if (reservationB == null) {
            System.out.println(
                    "Standard Room 101 is not available from Jan 3 to Jan 7."
            );
        }

        if (manager.cancel(reservationA)) {
            System.out.println(
                    "Reservation for Customer A, Standard Room 101 "
                    + "(Jan 1-5) cancelled successfully."
            );
        }

        Reservation reservationC = manager.book(
                customerC,
                deluxe201,
                "Feb 10",
                "Feb 12"
        );

        if (reservationC != null) {
            System.out.println(
                    "Reservation confirmed for Customer C, "
                    + "Deluxe Room 201 (Feb 10-12). Price: $"
                    + reservationC.calculateAmount()
            );
        }
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

abstract class Room {

    protected int roomNumber;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(int days);

    public abstract String getRoomType();
}

class StandardRoom extends Room {

    public StandardRoom(int roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 100;
    }

    @Override
    public String getRoomType() {
        return "Standard Room";
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 150;
    }

    @Override
    public String getRoomType() {
        return "Deluxe Room";
    }
}

class SuiteRoom extends Room {

    public SuiteRoom(int roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 250;
    }

    @Override
    public String getRoomType() {
        return "Suite";
    }
}

class Reservation {

    private Customer customer;
    private Room room;
    private String startDate;
    private String endDate;
    private int days;
    private boolean active;

    public Reservation(
            Customer customer,
            Room room,
            String startDate,
            String endDate,
            int days) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.active = true;
    }

    public Room getRoom() {
        return room;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        active = false;
    }

    public double calculateAmount() {
        return room.calculatePrice(days);
    }
}

class BookingManager {

    private ArrayList<Reservation> reservations =
            new ArrayList<>();

    public Reservation book(
            Customer customer,
            Room room,
            String startDate,
            String endDate) {

        for (Reservation reservation : reservations) {

            if (reservation.isActive()
                    && reservation.getRoom() == room
                    && datesOverlap(
                            startDate,
                            endDate,
                            reservation.getStartDate(),
                            reservation.getEndDate())) {

                return null;
            }
        }

        int days = calculateDays(startDate, endDate);

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate,
                        days
                );

        reservations.add(reservation);

        return reservation;
    }

    public boolean cancel(Reservation reservation) {

        if (reservation != null && reservation.isActive()) {
            reservation.cancel();
            return true;
        }

        return false;
    }

    private boolean datesOverlap(
            String start1,
            String end1,
            String start2,
            String end2) {

        int s1 = convertDate(start1);
        int e1 = convertDate(end1);
        int s2 = convertDate(start2);
        int e2 = convertDate(end2);

        return s1 < e2 && s2 < e1;
    }

    private int calculateDays(String start, String end) {

        return convertDate(end) - convertDate(start);
    }

    private int convertDate(String date) {

        String[] parts = date.split(" ");

        int month;

        if (parts[0].equalsIgnoreCase("Jan")) {
            month = 1;
        } else if (parts[0].equalsIgnoreCase("Feb")) {
            month = 2;
        } else {
            month = 1;
        }

        return month * 31 + Integer.parseInt(parts[1]);
    }
}