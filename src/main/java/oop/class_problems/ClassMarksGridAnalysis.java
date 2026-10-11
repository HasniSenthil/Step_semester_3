package oop.class_problems;

import java.util.*;

public class ClassMarksGridAnalysis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            int bracketStart = line.indexOf('[');
            int bracketEnd = line.indexOf(']');

            if (bracketStart == -1 || bracketEnd == -1) {
                continue;
            }

            String name = line.substring(0, bracketStart).trim();
            String[] values = line.substring(
                    bracketStart + 1, bracketEnd
            ).split(",");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int[] subjectTotals = new int[3];
        int topperIndex = 0;
        int highestTotal = -1;

        StringBuilder totals = new StringBuilder("Totals ");

        for (int i = 0; i < marksList.size(); i++) {
            int[] marks = marksList.get(i);
            int total = 0;

            for (int j = 0; j < 3; j++) {
                total += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (i > 0) {
                totals.append(", ");
            }

            totals.append(names.get(i)).append(" ").append(total);

            if (total > highestTotal) {
                highestTotal = total;
                topperIndex = i;
            }
        }

        System.out.println(totals);

        if (!marksList.isEmpty()) {
            int count = marksList.size();

            System.out.printf(
                    "averages %.2f, %.2f, %.2f%n",
                    subjectTotals[0] / (double) count,
                    subjectTotals[1] / (double) count,
                    subjectTotals[2] / (double) count
            );

            System.out.println(
                    "topper " + names.get(topperIndex)
                            + " (" + highestTotal + ")"
            );
        }

        sc.close();
    }
}