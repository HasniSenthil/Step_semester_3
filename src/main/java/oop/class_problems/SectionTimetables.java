
package oop.class_problems;

import java.util.ArrayList;
import java.util.Scanner;

public class SectionTimetables {

    static class Timetable {

        String name;
        ArrayList<String> periods;

        // Constructor
        Timetable(String name, ArrayList<String> periods) {
            this.name = name;
            this.periods = periods;
        }

        // Deep copy method
        Timetable copy() {

            ArrayList<String> newPeriods =
                    new ArrayList<>(periods);

            return new Timetable(name, newPeriods);
        }

        // Change section name
        void setName(String name) {
            this.name = name;
        }

        // Change a period
        void setPeriod(int period, String subject) {

            if (period >= 1 && period <= periods.size()) {
                periods.set(period - 1, subject);
            }
        }

        // Display timetable
        public String toString() {
            return name + ": " + periods;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original section name: ");
        String originalName = sc.nextLine();

        System.out.print("Enter number of periods: ");
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<String> subjects = new ArrayList<>();

        System.out.println("Enter subjects:");

        for (int i = 0; i < n; i++) {
            subjects.add(sc.nextLine());
        }

        Timetable original =
                new Timetable(originalName, subjects);

        // Create deep copy
        Timetable copy = original.copy();

        System.out.print("Enter new section name: ");
        String newName = sc.nextLine();

        copy.setName(newName);

        System.out.print("Enter period number to change: ");
        int period = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new subject: ");
        String subject = sc.nextLine();

        copy.setPeriod(period, subject);

        System.out.println("\nOriginal Timetable: " + original);

        System.out.println("Copied Timetable: " + copy);

        sc.close();
    }
}
