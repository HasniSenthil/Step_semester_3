package oop.assignment_problems;

public class HostelLaundryQueue {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType heavy = new HeavyWash();
        WashType normal = new NormalWash();

        WashCycle cycle1 = m1.startWash(asha, quick);

        if (cycle1 != null) {
            System.out.println(
                    "Quick wash started on M1 for Asha ("
                            + quick.getDuration()
                            + " min). Charge: Rs."
                            + String.format("%.2f", quick.getCharge())
                    + "."
            );
        }

        WashCycle cycle2 = m1.startWash(ravi, heavy);

        if (cycle2 == null) {
            System.out.println("Machine M1 is currently busy.");
        }

        WashCycle cycle3 = m2.startWash(ravi, heavy);

        if (cycle3 != null) {
            System.out.println(
                    "Heavy wash started on M2 for Ravi ("
                            + heavy.getDuration()
                            + " min). Charge: Rs."
                            + String.format("%.2f", heavy.getCharge())
                    + "."
            );
        }

        m1.completeWash();

        System.out.println("M1 cycle completed.");
        System.out.println("M1 is now free.");

        WashCycle cycle4 = m1.startWash(neha, normal);

        if (cycle4 != null) {
            System.out.println(
                    "Normal wash started on M1 for Neha ("
                            + normal.getDuration()
                            + " min). Charge: Rs."
                            + String.format("%.2f", normal.getCharge())
                    + "."
            );
        }
    }
}

class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface WashType {

    int getDuration();

    double getCharge();

    String getName();
}

class QuickWash implements WashType {

    @Override
    public int getDuration() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20;
    }

    @Override
    public String getName() {
        return "Quick";
    }
}

class NormalWash implements WashType {

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}

class HeavyWash implements WashType {

    @Override
    public int getDuration() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45;
    }

    @Override
    public String getName() {
        return "Heavy";
    }
}

class DelicateWash implements WashType {

    @Override
    public int getDuration() {
        return 50;
    }

    @Override
    public double getCharge() {
        return 40;
    }

    @Override
    public String getName() {
        return "Delicate";
    }
}

class WashCycle {

    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(
            Student student,
            WashingMachine machine,
            WashType washType) {

        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public double calculateCharge() {
        return washType.getCharge();
    }
}

class WashingMachine {

    private String machineId;
    private boolean busy;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public WashCycle startWash(
            Student student,
            WashType washType) {

        if (busy) {
            return null;
        }

        busy = true;

        currentCycle =
                new WashCycle(
                        student,
                        this,
                        washType
                );

        return currentCycle;
    }

    public void completeWash() {

        if (busy) {
            currentCycle = null;
            busy = false;
        }
    }

    public boolean isFree() {
        return !busy;
    }
}
