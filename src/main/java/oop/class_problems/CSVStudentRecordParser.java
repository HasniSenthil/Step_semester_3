package oop.class_problems;
public class CSVStudentRecordParser {
    static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: " + fields[0]+ " | Roll No: " + fields[1]+ " | Dept: " + fields[2]);
    }
    public static void main(String[] args) {
        String csvLine = "Ananya Verma,RA2211003010123,CSE";
        String csvLine1 = "Ananya Verma,CSE";
        parseStudentRecord(csvLine);
        parseStudentRecord(csvLine1);
    }
}
