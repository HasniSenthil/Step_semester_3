package oop.assignment_problems;

import java.util.ArrayList;

public class CampusPremiereTicketCounter {

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        Booking ashaBooking =
                show.book(
                        asha,
                        a1,
                        a2,
                        f5
                );

        if (ashaBooking != null) {
            System.out.println(
                    "Booking confirmed for Asha: A1, A2, F5. "
                            + "Total: Rs."
                            + String.format(
                            "%.2f",
                            ashaBooking.calculateTotal()
                    )
            );
        }

        Booking raviA2 =
                show.book(ravi, a2);

        if (raviA2 == null) {
            System.out.println(
                    "Seat A2 is already booked for this show."
            );
        }

        Booking raviBooking =
                show.book(ravi, r1);

        if (raviBooking != null) {
            System.out.println(
                    "Booking confirmed for Ravi: R1. "
                            + "Total: Rs."
                            + String.format(
                            "%.2f",
                            raviBooking.calculateTotal()
                    )
            );
        }

        if (show.cancel(ashaBooking)) {
            System.out.println(
                    "Asha's booking cancelled. "
                            + "Seats A1, A2, F5 released."
            );
        }

        Booking nehaBooking =
                show.book(neha, a2);

        if (nehaBooking != null) {
            System.out.println(
                    "Booking confirmed for Neha: A2. "
                            + "Total: Rs."
                            + String.format(
                            "%.2f",
                            nehaBooking.calculateTotal()
                    )
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

abstract class Seat {

    protected String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {

    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400;
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private ArrayList<Seat> seats;
    private boolean cancelled;

    public Booking(
            Customer customer,
            Show show,
            ArrayList<Seat> seats) {

        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }

    public double calculateTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}

class Show {

    private String showTime;
    private ArrayList<Seat> bookedSeats;
    private boolean started;

    public Show(String showTime) {
        this.showTime = showTime;
        this.bookedSeats = new ArrayList<>();
        this.started = false;
    }

    public Booking book(
            Customer customer,
            Seat... seats) {

        if (seats.length == 0 || seats.length > 6) {
            return null;
        }

        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return null;
            }
        }

        ArrayList<Seat> selectedSeats =
                new ArrayList<>();

        for (Seat seat : seats) {
            bookedSeats.add(seat);
            selectedSeats.add(seat);
        }

        return new Booking(
                customer,
                this,
                selectedSeats
        );
    }

    public boolean cancel(Booking booking) {

        if (started || booking == null
                || booking.isCancelled()) {
            return false;
        }

        for (Seat seat : booking.getSeats()) {
            bookedSeats.remove(seat);
        }

        booking.cancel();

        return true;
    }

    public void startShow() {
        started = true;
    }
}
