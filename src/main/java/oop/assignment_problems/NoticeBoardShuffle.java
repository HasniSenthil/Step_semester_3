package oop.assignment_problems;
public class NoticeBoardShuffle {

    static int[] bubbleStats(int[] scores) {

        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < scores.length - 1; i++) {

            boolean swapped = false;
            passes++;

            for (int j = 0; j < scores.length - 1 - i; j++) {

                if (scores[j] > scores[j + 1]) {

                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (swapped == false) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {

        int[] scores = {5, 1, 4, 2, 8};

        int[] result = bubbleStats(scores);

        System.out.print("Sorted Array: ");

        for (int x : scores) {
            System.out.print(x + " ");
        }

        System.out.println();

        System.out.println("Passes = " + result[0]);
        System.out.println("Swaps = " + result[1]);

        int[] scores2 = {1, 2, 3, 4};

        int[] result2 = bubbleStats(scores2);

        System.out.println("Passes = " + result2[0]);
        System.out.println("Swaps = " + result2[1]);
    }
}
