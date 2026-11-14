
package oop.class_problems;

import java.util.*;

public class LeaderboardRanking {

    static class Participant {
        String name;
        int score;

        Participant(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    // Stable insertion sort in descending order
    static void sort( ArrayList<Participant> entries) {

        for (int i = 1; i < entries.size(); i++) {

            Participant key = entries.get(i);

            int j = i - 1;

            while (j >= 0 && entries.get(j).score < key.score) {

                entries.set(j + 1, entries.get(j));

                j--;
            }

            entries.set(j + 1, key);
        }
    }

    // Print competition ranks
    static void printRanks(ArrayList<Participant> entries) {

        int rank = 1;

        for (int i = 0; i < entries.size(); i++) {

            if (i > 0 &&
                entries.get(i).score != entries.get(i - 1).score) {

                rank = i + 1;
            }

            System.out.println(
                rank + " " +
                entries.get(i).name + " " +
                entries.get(i).score
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Participant> entries = new ArrayList<>();

        // Read participants until input ends
        while (sc.hasNext()) {

            String name = sc.next();
            int score = sc.nextInt();

            entries.add(new Participant(name, score));
        }

        sort(entries);

        printRanks(entries);

        sc.close();
    }
}
