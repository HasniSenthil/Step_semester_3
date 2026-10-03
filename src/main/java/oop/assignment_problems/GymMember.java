package oop.assignment_problems;

public class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    // Problem 3 - Late fee history
    private int[] lateFeeHistory;
    private int lateFeeCount;

    // Problem 5 - Membership counter
    private static int membersEnrolled = 2000;

    // Problem 5 - Final membership number
    public final String membershipNumber;

    // Problem 5 - Fees paid
    private int feesPaid;


    // =====================================================
    // PROBLEM 1
    // =====================================================

    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null ||
                memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "construction rejected"
            );
        }

        if (monthlyFee <= 0) {

            throw new IllegalArgumentException(
                    "monthlyFee must be positive"
            );
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        // Problem 3
        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;

        // Problem 5
        membersEnrolled++;
        membershipNumber =
                "GYM-" + membersEnrolled;
    }


    // Problem 5 constructor
    public GymMember(int monthlyFee) {

        if (monthlyFee <= 0) {

            throw new IllegalArgumentException(
                    "monthlyFee must be positive"
            );
        }

        this.memberId = null;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        // Problem 3
        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;

        // Problem 5
        membersEnrolled++;
        membershipNumber =
                "GYM-" + membersEnrolled;
    }


    public void attendSession() {

        sessionsAttended++;
    }


    public int getSessionsAttended() {

        return sessionsAttended;
    }


    // Problem 2
    public String displayInfo() {

        return "Standard Member | Sessions: "
                + sessionsAttended;
    }


    // =====================================================
    // PROBLEM 3
    // =====================================================

    protected void chargeLateFee(int amount) {

        if (lateFeeCount < lateFeeHistory.length) {

            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }


    public int[] getLateFeeHistory() {

        int[] copy =
                new int[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {

            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }


    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {

            total += lateFeeHistory[i];
        }

        return total;
    }


    // =====================================================
    // PROBLEM 1
    // =====================================================

    static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {

            try {

                new GymMember(
                        memberIds[i],
                        monthlyFee
                );

                signedUp++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Signed Up: "
                + signedUp
                + " | Rejected: "
                + rejected;
    }


    // =====================================================
    // PROBLEM 2
    // =====================================================

    static String classifyGeneration(
            GymMember member) {

        if (member instanceof EliteMember) {

            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {

            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {

            return "Premium branch";
        }

        return "Standard Member";
    }


    static int getTotalSessionsAttended(
            GymMember[] members) {

        int total = 0;

        for (int i = 0; i < members.length; i++) {

            total += members[i].getSessionsAttended();
        }

        return total;
    }


    // =====================================================
    // PROBLEM 4
    // =====================================================

    static String batchPrint(
            GymMember[] members) {

        StringBuilder announcement =
                new StringBuilder();

        for (int i = 0; i < members.length; i++) {

            announcement.append(
                    members[i].displayInfo()
            );

            if (members[i] instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) members[i];

                announcement.append(
                        " [Trainer via downcast: "
                                + premium.trainerName
                                + "]"
                );
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }


    // =====================================================
    // PROBLEM 5
    // =====================================================

    void payFee(int amount) {

        feesPaid += amount;
    }


    void payFee(
            int amount,
            String mode) {

        // Mode is accepted here.
        // Actual payment logic is reused.
        payFee(amount);
    }


    int getFeesPaid() {

        return feesPaid;
    }


    static boolean isValidReferralCode(
            String code) {

        if (code == null ||
                code.length() != 4) {

            return false;
        }

        if (code.charAt(0) != 'G') {

            return false;
        }

        if (!Character.isDigit(
                code.charAt(1))) {

            return false;
        }

        if (!Character.isDigit(
                code.charAt(2))) {

            return false;
        }

        if (!Character.isUpperCase(
                code.charAt(3))) {

            return false;
        }

        return true;
    }


    static int getMembersEnrolled() {

        return membersEnrolled - 2000;
    }


    static String processWeeklyCheckIn(
            GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (int i = 0; i < members.length; i++) {

            if (members[i] == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (members[i]
                    instanceof GroupClassMember) {

                group++;

            } else {

                individual++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + group
                + " group | "
                + individual
                + " individual";
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        // -------------------------------------------------
        // PROBLEM 1
        // -------------------------------------------------

        System.out.println("===== PROBLEM 1 =====");

        PremiumMember p1 =
                new PremiumMember(
                        "MEM01",
                        2000,
                        "Coach Riya"
                );

        p1.attendSession();
        p1.attendSession();

        System.out.println(
                p1.getSessionsAttended()
        );

        String[] memberIds = {
                "MEM1",
                "GM1",
                "MEM2",
                " ",
                "MEM3"
        };

        System.out.println(
                signUpBatch(
                        memberIds,
                        1000
                )
        );


        // -------------------------------------------------
        // PROBLEM 2
        // -------------------------------------------------

        System.out.println("\n===== PROBLEM 2 =====");

        GymMember standard =
                new GymMember(
                        "MEM10",
                        1000
                );

        PremiumMember premium =
                new PremiumMember(
                        "MEM11",
                        2000,
                        "Coach Riya"
                );

        EliteMember elite =
                new EliteMember(
                        "MEM12",
                        3000,
                        "Coach Arjun",
                        "L12"
                );

        GroupClassMember group =
                new GroupClassMember(
                        "MEM13",
                        1500,
                        "Zumba"
                );

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        System.out.println(
                standard.displayInfo()
        );

        System.out.println(
                premium.displayInfo()
        );

        System.out.println(
                elite.displayInfo()
        );

        System.out.println(
                group.displayInfo()
        );

        System.out.println(
                classifyGeneration(elite)
        );

        System.out.println(
                classifyGeneration(group)
        );

        GymMember[] mixedMembers = {
                premium,
                elite,
                group
        };

        System.out.println(
                getTotalSessionsAttended(
                        mixedMembers
                )
        );


        // -------------------------------------------------
        // PROBLEM 3
        // -------------------------------------------------

        System.out.println("\n===== PROBLEM 3 =====");

        PremiumMember p3 =
                new PremiumMember(
                        "MEM20",
                        2000,
                        "Coach Riya"
                );

        p3.chargeLateFee(200);

        System.out.println(
                p3.getTotalLateFees()
        );

        int[] history =
                p3.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
                "Actual history: "
                        + p3.getLateFeeHistory()[0]
        );


        // -------------------------------------------------
        // PROBLEM 4
        // -------------------------------------------------

        System.out.println("\n===== PROBLEM 4 =====");

        GymMember[] announcementMembers = {

                new GymMember(
                        "MEM30",
                        1000
                ),

                new PremiumMember(
                        "MEM31",
                        2000,
                        "Coach Riya"
                )
        };

        System.out.println(
                batchPrint(
                        announcementMembers
                )
        );


        // -------------------------------------------------
        // PROBLEM 5
        // -------------------------------------------------

        System.out.println("\n===== PROBLEM 5 =====");

        GymMember m1 =
                new GymMember(1000);

        System.out.println(
                m1.membershipNumber
        );

        System.out.println(
                getMembersEnrolled()
        );

        System.out.println(
                isValidReferralCode("G45B")
        );

        System.out.println(
                isValidReferralCode("G4B")
        );

        System.out.println(
                isValidReferralCode("X45B")
        );

        m1.payFee(500);

        m1.payFee(
                500,
                "UPI"
        );

        System.out.println(
                m1.getFeesPaid()
        );

        GymMember[] checkInMembers = {

                new GroupClassMember(
                        1500,
                        "Zumba"
                ),

                null,

                new GymMember(1000)
        };

        System.out.println(
                processWeeklyCheckIn(
                        checkInMembers
                )
        );
    }
}


// =========================================================
// PREMIUM MEMBER
// =========================================================

class PremiumMember extends GymMember {

    protected String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }


    @Override
    public String displayInfo() {

        return "Premium Member | Trainer: "
                + trainerName
                + " | Sessions: "
                + sessionsAttended;
    }


    // Problem 3
    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(
                amount / 2
        );
    }
}


// =========================================================
// ELITE MEMBER
// =========================================================

class EliteMember extends PremiumMember {

    private String lockerNumber;

    public EliteMember(
            String memberId,
            int monthlyFee,
            String trainerName,
            String lockerNumber) {

        super(
                memberId,
                monthlyFee,
                trainerName
        );

        this.lockerNumber = lockerNumber;
    }


    @Override
    public String displayInfo() {

        return "Elite Member | Trainer: "
                + trainerName
                + " | Locker: "
                + lockerNumber
                + " | Sessions: "
                + sessionsAttended;
    }
}


// =========================================================
// GROUP CLASS MEMBER
// =========================================================

class GroupClassMember extends GymMember {

    private String className;

    // Problem 2 constructor
    public GroupClassMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(
                memberId,
                monthlyFee
        );

        this.className = className;
    }


    // Problem 5 constructor
    public GroupClassMember(
            int monthlyFee,
            String className) {

        super(monthlyFee);

        this.className = className;
    }


    @Override
    public String displayInfo() {

        return "Group Class Member | Class: "
                + className
                + " | Sessions: "
                + sessionsAttended;
    }
}