package oop.class_problems;

public class OrchestraWarmUpRoutine {

    public static void main(String[] args) {

        StringInstrument s =
                new StringInstrument();

        Violin v =
                new Violin();

        System.out.println(s.play());

        System.out.println(v.play());
    }
}


// Abstract parent
abstract class Instrument {

    public Instrument() {
    }

    public abstract String play();
}


// StringInstrument extends Instrument
class StringInstrument extends Instrument {

    public StringInstrument() {

        super();
    }

    @Override
    public String play() {

        return "Strumming the strings";
    }
}


// Violin extends StringInstrument
class Violin extends StringInstrument {

    public Violin() {

        super();
    }

    @Override
    public String play() {

        String result = super.play();

        return result
                + ", with a bow drawn across four strings";
    }
}
