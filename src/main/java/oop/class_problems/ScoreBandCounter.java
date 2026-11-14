
package oop.class_problems;

import java.util.Scanner;

public class ScoreBandCounter {

    // Find first index whose value is >= x
    static int lowerBound(int[] scores, int x) {

        int low = 0;
        int high = scores.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (scores[mid] < x) {

                low = mid + 1;

            } else {

                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] scores = new int[n];

        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {

            int x = sc.nextInt();

            int below = lowerBound(scores, x);

            int exactly = lowerBound(scores, x + 1) - below;

            System.out.println(x + ": exactly " + exactly
                    + ", below " + below);
        }

        sc.close();
    }
}
