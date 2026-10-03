package oop.assignment_problems;

public class SkylineDeliveryFleet {

    public static void main(String[] args) {

        DeliveryDrone d =
                new DeliveryDrone("DR-1");

        ScoutDrone s =
                new ScoutDrone("SC-1");

        GroundRobot g =
                new GroundRobot("GR-1");

        System.out.println(
                getLocationIfTrackable(d)
        );

        System.out.println(
                getLocationIfTrackable(s)
        );

        System.out.println(
                getLocationIfTrackable(g)
        );
    }


    static String getLocationIfTrackable(
            Object o) {

        if (o instanceof Trackable) {

            Trackable tracked =
                    (Trackable) o;

            return tracked.getLocation();
        }

        return "Tracking not available";
    }
}


// Abstract Drone
abstract class Drone {

    protected String id;

    public Drone(String id) {

        this.id = id;
    }

    public abstract String fly();
}


// Trackable interface
interface Trackable {

    String getLocation();
}


// DeliveryDrone
class DeliveryDrone
        extends Drone
        implements Trackable {

    public DeliveryDrone(String id) {

        super(id);
    }

    @Override
    public String fly() {

        return "Delivery drone " + id + " flying";
    }

    @Override
    public String getLocation() {

        return id + " at Sector 4";
    }
}


// ScoutDrone
// Does NOT implement Trackable
class ScoutDrone extends Drone {

    public ScoutDrone(String id) {

        super(id);
    }

    @Override
    public String fly() {

        return "Scout drone " + id + " flying";
    }
}


// GroundRobot
// Not related to Drone
// Implements only Trackable
class GroundRobot implements Trackable {

    private String id;

    public GroundRobot(String id) {

        this.id = id;
    }

    @Override
    public String getLocation() {

        return id + " at Sector 4";
    }
}