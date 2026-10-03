package oop.class_problems;

public class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    // Problem 3 - Fine history
    private int[] fineHistory;
    private int fineCount;

    // Problem 5 - Shared member counter
    private static int membersEnrolled = 100;

    // Problem 5 - Unique and immutable member number
    public final String memberNumber;

    // Problem 5 - Genre
    private String genre;

    // Problem 1 and Problems 2-4 constructor
    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                    "borrowLimit must be positive"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        fineHistory = new int[10];
        fineCount = 0;

        membersEnrolled++;
        memberNumber = "LIB-" + membersEnrolled;
    }

    // Problem 5 constructor
    public LibraryMember(int borrowLimit) {

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                    "borrowLimit must be positive"
            );
        }

        this.memberId = null;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        fineHistory = new int[10];
        fineCount = 0;

        membersEnrolled++;
        memberNumber = "LIB-" + membersEnrolled;
    }

    // Problem 1 and Problem 5
    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    // Problem 5 - overloaded method
    public void borrowBook(String genre) {

        this.genre = genre;

        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    // Problem 4
    public String displayInfo() {

        return "General | Books: " + booksBorrowed;
    }

    // Problem 3
    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    // Problem 3 - defensive copy
    public int[] getFineHistory() {

        int[] history = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            history[i] = fineHistory[i];
        }

        return history;
    }

    // Problem 3
    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }

    // Problem 1
    static String enrollBatch(
            String[] memberIds,
            int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {

            try {
                new LibraryMember(memberIds[i], borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled
                + " | Rejected: " + rejected;
    }

    // Problem 2
    static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember) {
            return "Student branch";
        }

        return "General Member";
    }

    // Problem 2
    static int getTotalBooksBorrowed(
            LibraryMember[] members) {

        int total = 0;

        for (int i = 0; i < members.length; i++) {
            total += members[i].getBooksBorrowed();
        }

        return total;
    }

    // Problem 4
    static String batchPrint(LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (int i = 0; i < members.length; i++) {

            // Polymorphic method call
            report.append(members[i].displayInfo());

            // Safe downcast
            if (members[i] instanceof StudentMember) {

                StudentMember student =
                        (StudentMember) members[i];

                report.append(
                        " [Course via downcast: "
                                + student.course
                                + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    // Problem 5
    static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    // Problem 5
    static int getMembersEnrolled() {

        return membersEnrolled - 100;
    }

    // Problem 5
    static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (int i = 0; i < members.length; i++) {

            if (members[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (members[i] instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + faculty
                + " faculty | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        // -------------------------
        // Problem 1
        // -------------------------

        LibraryMember general =
                new LibraryMember("STU1", 3);

        StudentMember student =
                new StudentMember("STU2", 3, "CSE");

        student.borrowBook();
        student.borrowBook();

        System.out.println(
                "Student books: "
                        + student.getBooksBorrowed()
        );

        String[] memberIds = {
                "STU1",
                "LB1",
                "STU2",
                " ",
                "STU3"
        };

        System.out.println(
                enrollBatch(memberIds, 3)
        );


        // -------------------------
        // Problem 2
        // -------------------------

        HonorsStudentMember honors =
                new HonorsStudentMember(
                        "STU3",
                        3,
                        "ECE",
                        2
                );

        FacultyMember faculty =
                new FacultyMember(
                        5,
                        "Physics"
                );

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        general.displayInfo();
        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(
                classifyGeneration(honors)
        );

        System.out.println(
                classifyGeneration(faculty)
        );

        LibraryMember[] members = {
                student,
                honors,
                faculty
        };

        System.out.println(
                "Total books: "
                        + getTotalBooksBorrowed(members)
        );


        // -------------------------
        // Problem 3
        // -------------------------

        StudentMember fineStudent =
                new StudentMember(
                        "STU5",
                        3,
                        "CSE"
                );

        fineStudent.chargeFine(100);

        System.out.println(
                "Total fine: "
                        + fineStudent.getTotalFine()
        );

        int[] history =
                fineStudent.getFineHistory();

        history[0] = 999;

        int[] actualHistory =
                fineStudent.getFineHistory();

        System.out.println(
                "Fine history: ["
                        + actualHistory[0]
                        + "]"
        );


        // -------------------------
        // Problem 4
        // -------------------------

        LibraryMember[] reportMembers = {
                new LibraryMember("LB05", 3),
                new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(
                batchPrint(reportMembers)
        );


        // -------------------------
        // Problem 5
        // -------------------------

        LibraryMember m1 =
                new LibraryMember(3);

        System.out.println(
                m1.memberNumber
        );

        System.out.println(
                LibraryMember.getMembersEnrolled()
        );

        System.out.println(
                isValidRenewalCode("R12A")
        );

        System.out.println(
                isValidRenewalCode("R1A")
        );

        System.out.println(
                isValidRenewalCode("X12A")
        );

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(
                m1.getBooksBorrowed()
        );

        LibraryMember[] auditMembers = {
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        };

        System.out.println(
                processNightlyAudit(auditMembers)
        );
    }
}


// =====================================================
// StudentMember
// =====================================================

class StudentMember extends LibraryMember {

    protected String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {

        return "Student | Course: "
                + course
                + " | Books: "
                + booksBorrowed;
    }

    // Problem 3
    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}


// =====================================================
// HonorsStudentMember
// =====================================================

class HonorsStudentMember extends StudentMember {

    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {

        return "Honors Student Member | Course: "
                + course
                + " | Bonus Limit: "
                + bonusLimit
                + " | Books Borrowed: "
                + booksBorrowed;
    }
}


// =====================================================
// FacultyMember
// =====================================================

class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {

        return "Faculty Member | Department: "
                + department
                + " | Books Borrowed: "
                + booksBorrowed;
    }
}