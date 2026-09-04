package oop.class_problems;
public class srmStudent {
    static String collegeName;
    static String academicYear;
    String studentName;
    static {
        collegeName = "SRM";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }
    public srmStudent(String studentName) {
        this.studentName = studentName;
    }
    public void printConfirmation() {
        System.out.println("Student record created: " + studentName);
    }
    public static void main(String[] args) {
        String[] names = {"Ravi","Meera","Karthik","Divya","Anitha" };
        for (int i = 0; i < names.length; i++) {
            srmStudent student = new srmStudent(names[i]);
            student.printConfirmation();
        }
    }
}