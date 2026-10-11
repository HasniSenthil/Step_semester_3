package oop.class_problems;

import java.util.*;

public class PayrollRegisterSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PaySlip> slips = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            switch (type) {
                case "FullTime": {
                    double salary = sc.nextDouble();
                    slips.add(new PaySlip(
                            name, type, salary
                    ));
                    break;
                }

                case "PartTime": {
                    double hours = sc.nextDouble();
                    double rate = sc.nextDouble();

                    slips.add(new PaySlip(
                            name, type, hours * rate
                    ));
                    break;
                }

                case "Intern": {
                    double stipend = sc.nextDouble();
                    slips.add(new PaySlip(
                            name, type, stipend
                    ));
                    break;
                }
            }
        }

        double total = 0;
        PaySlip topEarner = null;

        PaySlip[] payslips = slips.toArray(new PaySlip[0]);

        for (PaySlip slip : payslips) {
            System.out.println(slip);
            total += slip.getPay();

            if (topEarner == null
                    || slip.getPay() > topEarner.getPay()) {
                topEarner = slip;
            }
        }

        System.out.println("Total " + format(total));

        if (topEarner != null) {
            System.out.println(
                    "top earner " + topEarner.getName()
            );
        }

        sc.close();
    }

    private static String format(double value) {
        return value == (long) value
                ? String.valueOf((long) value)
                : String.valueOf(value);
    }
}

class PaySlip {
    private String name;
    private String type;
    private double pay;

    public PaySlip(String name, String type, double pay) {
        this.name = name;
        this.type = type;
        this.pay = pay;
    }

    public String getName() {
        return name;
    }

    public double getPay() {
        return pay;
    }

    @Override
    public String toString() {
        String formattedPay = pay == (long) pay
                ? String.valueOf((long) pay)
                : String.valueOf(pay);

        return "Payslip[name=" + name
                + ", type=" + type
                + ", pay=" + formattedPay + "]";
    }
}