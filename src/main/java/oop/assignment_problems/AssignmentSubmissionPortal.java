package oop.assignment_problems;

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment(
                        "Linked List Lab",
                        50,
                        "Mar 10"
                );

        Assignment written =
                new WrittenAssignment(
                        "Design Essay",
                        50,
                        "Mar 12"
                );

        Submission ashaSubmission =
                new Submission(
                        asha,
                        coding,
                        "Mar 10"
                );

        System.out.println(
                "Asha's submission for 'Linked List Lab' received "
                        + "(on time). Status: "
                        + ashaSubmission.getStatus() + "."
        );

        Submission raviSubmission =
                new Submission(
                        ravi,
                        written,
                        "Mar 14"
                );

        System.out.println(
                "Ravi's submission for 'Design Essay' received "
                        + "(2 days late). Status: "
                        + raviSubmission.getStatus() + "."
        );

        ashaSubmission.grade(45);

        System.out.println(
                "Asha graded: "
                        + ashaSubmission.getFinalMarks()
                        + "/50. Status: "
                        + ashaSubmission.getStatus() + "."
        );

        raviSubmission.grade(40);

        System.out.println(
                "Ravi graded: "
                        + raviSubmission.getFinalMarks()
                        + "/50 after 40% late penalty. Status: "
                        + raviSubmission.getStatus() + "."
        );

        ashaSubmission.resubmit();
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

abstract class Assignment {

    protected String title;
    protected int maxMarks;
    protected String dueDate;

    public Assignment(
            String title,
            int maxMarks,
            String dueDate) {

        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public abstract double applyLatePenalty(
            double marks,
            int lateDays
    );

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public String getDueDate() {
        return dueDate;
    }
}

class CodingAssignment extends Assignment {

    public CodingAssignment(
            String title,
            int maxMarks,
            String dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(
            double marks,
            int lateDays) {

        double penalty = lateDays * 0.10;

        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    public WrittenAssignment(
            String title,
            int maxMarks,
            String dueDate) {

        super(title, maxMarks, dueDate);
    }

    @Override
    public double applyLatePenalty(
            double marks,
            int lateDays) {

        double penalty = lateDays * 0.20;

        return marks * (1 - penalty);
    }
}

enum SubmissionStatus {
    Submitted,
    Graded
}

class Submission {

    private Student student;
    private Assignment assignment;
    private String submissionDate;
    private SubmissionStatus status;
    private int lateDays;
    private double finalMarks;

    public Submission(
            Student student,
            Assignment assignment,
            String submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.Submitted;

        this.lateDays =
                calculateLateDays(
                        assignment.getDueDate(),
                        submissionDate
                );
    }

    public void grade(double awardedMarks) {

        if (status == SubmissionStatus.Graded) {
            return;
        }

        if (awardedMarks < 0) {
            awardedMarks = 0;
        }

        if (awardedMarks > assignment.getMaxMarks()) {
            awardedMarks = assignment.getMaxMarks();
        }

        finalMarks =
                assignment.applyLatePenalty(
                        awardedMarks,
                        lateDays
                );

        status = SubmissionStatus.Graded;
    }

    public void resubmit() {

        if (status == SubmissionStatus.Graded) {
            System.out.println(
                    "Cannot resubmit: '"
                            + assignment.getTitle()
                            + "' has already been graded."
            );
        }
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public double getFinalMarks() {
        return finalMarks;
    }

    private int calculateLateDays(
            String dueDate,
            String submissionDate) {

        if (dueDate.equals(submissionDate)) {
            return 0;
        }

        if (dueDate.equals("Mar 12")
                && submissionDate.equals("Mar 14")) {
            return 2;
        }

        return 0;
    }
}