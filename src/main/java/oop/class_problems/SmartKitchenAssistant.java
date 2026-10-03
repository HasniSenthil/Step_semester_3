package oop.class_problems;

public class SmartKitchenAssistant {

    public static void main(String[] args) {

        Blender b =
                new Blender();

        b.setSpeedLevel(3);

        System.out.println(
                b.getSpeedLevel()
        );

        b.setSpeedLevel(9);

        System.out.println(
                "Speed after invalid value: "
                        + b.getSpeedLevel()
        );

        System.out.println(
                b.prepare()
        );

        System.out.println(
                b.clean()
        );
    }
}


// Abstract KitchenTool
abstract class KitchenTool {

    private int speedLevel;

    public KitchenTool() {

        speedLevel = 1;
    }

    public abstract String prepare();

    // JavaBean getter
    public int getSpeedLevel() {

        return speedLevel;
    }

    // JavaBean setter
    public void setSpeedLevel(int speedLevel) {

        if (speedLevel < 1 ||
                speedLevel > 5) {

            System.out.println(
                    "rejected, speed level stays "
                            + this.speedLevel
            );

            return;
        }

        this.speedLevel = speedLevel;
    }
}


// Washable interface
interface Washable {

    String clean();
}


// Blender extends KitchenTool
// and implements Washable
class Blender
        extends KitchenTool
        implements Washable {

    public Blender() {

        super();
    }

    @Override
    public String prepare() {

        return "Blending at speed "
                + getSpeedLevel();
    }

    @Override
    public String clean() {

        return "Blender rinsed and dried";
    }
}
