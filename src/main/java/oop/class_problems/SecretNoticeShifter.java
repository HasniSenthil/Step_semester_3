
package oop.class_problems;

import java.util.Scanner;

public class SecretNoticeShifter {

    static String shiftNotice(String text, int k) {

        StringBuilder result = new StringBuilder();

        k = k % 26;

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                ch = (char) ((ch - 'a' + k) % 26 + 'a');

            }

            else if (ch >= 'A' && ch <= 'Z') {

                ch = (char) ((ch - 'A' + k) % 26 + 'A');

            }

            result.append(ch);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int k = sc.nextInt();

        System.out.println("Encrypted text: " + shiftNotice(text, k));

        sc.close();
    }
}
