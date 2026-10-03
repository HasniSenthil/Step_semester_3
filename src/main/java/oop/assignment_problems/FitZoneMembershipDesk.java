package oop.assignment_problems;

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        MembershipPlan quarterly =
                new QuarterlyPlan();

        MembershipPlan monthly =
                new MonthlyPlan();

        Membership ashaMembership =
                asha.buyMembership(quarterly);

        System.out.println(
                "Quarterly membership created for Asha. "
                        + "Fee: Rs."
                        + String.format(
                        "%.2f",
                        ashaMembership.getFee()
                )
                        + ". Status: "
                        + ashaMembership.getStatus()
                        + "."
        );

        Membership raviMembership =
                ravi.buyMembership(monthly);

        System.out.println(
                "Monthly membership created for Ravi. "
                        + "Fee: Rs."
                        + String.format(
                        "%.2f",
                        raviMembership.getFee()
                )
                        + ". Status: "
                        + raviMembership.getStatus()
                        + "."
        );

        ashaMembership.checkIn();

        ashaMembership.freeze();

        System.out.println(
                "Asha's membership frozen. Status: "
                        + ashaMembership.getStatus()
                        + "."
        );

        ashaMembership.checkIn();

        raviMembership.expire();

        System.out.println(
                "Ravi's membership expired. Status: "
                        + raviMembership.getStatus()
                        + "."
        );

        raviMembership.freeze();
    }
}

class Member {

    private String name;
    private Membership membership;

    public Member(String name) {
        this.name = name;
    }

    public Membership buyMembership(
            MembershipPlan plan) {

        membership =
                new Membership(this, plan);

        return membership;
    }

    public String getName() {
        return name;
    }
}

interface MembershipPlan {

    double calculateFee();

    int getMonths();
}

class MonthlyPlan implements MembershipPlan {

    @Override
    public double calculateFee() {
        return 1000;
    }

    @Override
    public int getMonths() {
        return 1;
    }
}

class QuarterlyPlan implements MembershipPlan {

    @Override
    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }

    @Override
    public int getMonths() {
        return 3;
    }
}

class AnnualPlan implements MembershipPlan {

    @Override
    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }

    @Override
    public int getMonths() {
        return 12;
    }
}

class HalfYearlyPlan implements MembershipPlan {

    @Override
    public double calculateFee() {
        return 1000 * 6 * 0.85;
    }

    @Override
    public int getMonths() {
        return 6;
    }
}

enum MembershipStatus {
    Active,
    Frozen,
    Expired
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private double fee;
    private MembershipStatus status;

    public Membership(
            Member member,
            MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.Active;
    }

    public double getFee() {
        return fee;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public void checkIn() {

        if (status == MembershipStatus.Active) {
            System.out.println(
                    member.getName()
                            + " checked in successfully."
            );
        } else {
            System.out.println(
                    "Check-in denied: "
                            + member.getName()
                            + "'s membership is "
                            + status
                            + "."
            );
        }
    }

    public void freeze() {

        if (status == MembershipStatus.Active) {
            status = MembershipStatus.Frozen;
        } else if (status == MembershipStatus.Expired) {
            System.out.println(
                    "Cannot freeze an Expired membership."
            );
        }
    }

    public void unfreeze() {

        if (status == MembershipStatus.Frozen) {
            status = MembershipStatus.Active;
        } else if (status == MembershipStatus.Expired) {
            System.out.println(
                    "Cannot unfreeze an Expired membership."
            );
        }
    }

    public void expire() {

        if (status != MembershipStatus.Expired) {
            status = MembershipStatus.Expired;
        }
    }
}
