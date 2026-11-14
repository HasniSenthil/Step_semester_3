
package oop.class_problems;

import java.util.Scanner;

public class EditorUndoHistory {

    static class Editor {

        String[] stack;
        int top;
        int capacity;

        Editor(int n) {
            capacity = n;
            stack = new String[n];
            top = -1;
        }

        // TYPE operation
        void type(String word) {

            if (top == capacity - 1) {
                System.out.println("rejected: history full");
                display();
                return;
            }

            stack[++top] = word;

            display();
        }

        // UNDO operation
        void undo() {

            if (top == -1) {
                System.out.println("rejected: nothing to undo");
                display();
                return;
            }

            stack[top] = null;
            top--;

            display();
        }

        // Display history
        void display() {

            if (top == -1) {
                System.out.println("(empty)");
                return;
            }

            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i]);

                if (i < top) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim()
                .replace("Capacity", "").trim());

        Editor editor = new Editor(n);

        while (sc.hasNextLine()) {

            String line = sc.nextLine();

            String[] commands = line.split(";");

            for (String command : commands) {

                command = command.trim();

                if (command.isEmpty()) {
                    continue;
                }

                if (command.startsWith("TYPE ")) {

                    String word = command.substring(5).trim();

                    editor.type(word);
                }

                else if (command.equals("UNDO")) {

                    editor.undo();
                }
            }
        }

        sc.close();
    }
}
