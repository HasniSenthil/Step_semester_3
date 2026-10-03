package oop.assignment_problems;

public class DigitalClassroomSetup {

    public static void main(String[] args) {

        Tablet t =
                new Tablet("TAB-5");

        System.out.println(t.operate());

        System.out.println(t.charge());

        System.out.println(t.charge(30));
    }
}


// Abstract classroom device
abstract class ClassroomDevice {

    public ClassroomDevice() {
    }

    public abstract String operate();
}


// Chargeable interface
interface Chargeable {

    String charge();

    String charge(int minutes);
}


// Tablet extends ClassroomDevice
// and implements Chargeable
class Tablet
        extends ClassroomDevice
        implements Chargeable {

    private String assetTag;

    public Tablet(String assetTag) {

        this.assetTag = assetTag;
    }

    @Override
    public String operate() {

        return "Tablet "
                + assetTag
                + " displaying lesson";
    }

    @Override
    public String charge() {

        return assetTag
                + " charging";
    }

    @Override
    public String charge(int minutes) {

        return assetTag
                + " charging for "
                + minutes
                + " minutes";
    }
}