package oop.class_problems;

public class PackageDropOffLog {

    public static void main(String[] args) {

        ParcelNote p =
                new ParcelNote("TRK-1");

        LetterNote l =
                new LetterNote("TRK-2");

        System.out.println(
                p.confirmDelivery()
        );

        System.out.println(
                p.confirmDelivery("J. Smith")
        );

        DeliveryNote ref = p;

        logAll(
                new DeliveryNote[]{
                        ref,
                        l
                }
        );
    }


    static void logAll(
            DeliveryNote[] notes) {

        for (int i = 0; i < notes.length; i++) {

            System.out.println(
                    notes[i].confirmDelivery()
            );
        }
    }
}


// Abstract DeliveryNote
abstract class DeliveryNote {

    public DeliveryNote() {
    }

    public abstract String confirmDelivery();

    // Overloaded method
    public String confirmDelivery(
            String signature) {

        return confirmDelivery()
                + ", signed by "
                + signature;
    }
}


// ParcelNote
class ParcelNote extends DeliveryNote {

    private String trackingId;

    public ParcelNote(String trackingId) {

        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {

        return "Parcel "
                + trackingId
                + " delivered";
    }
}


// LetterNote
class LetterNote extends DeliveryNote {

    private String trackingId;

    public LetterNote(String trackingId) {

        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {

        return "Letter "
                + trackingId
                + " delivered";
    }
}
