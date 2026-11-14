package oop.assignment_problems;

import java.util.Scanner;

public class BrowserTabStrip {

    static class Node {
        String name;
        Node prev, next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static Node head = null;
    static Node current = null;

    // Open a new tab after current tab
    static void open(String name) {

        Node newNode = new Node(name);

        if (current == null) {
            head = newNode;
            current = newNode;
        } else {
            newNode.prev = current;
            newNode.next = current.next;

            if (current.next != null) {
                current.next.prev = newNode;
            }

            current.next = newNode;
            current = newNode;
        }

        System.out.println("OPEN " + name + ": " + display());
    }

    // Close current tab
    static void close() {

        if (current == null) {
            System.out.println("CLOSE: no tabs open");
            return;
        }

        String name = current.name;

        Node nextNode = current.next;
        Node prevNode = current.prev;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        }

        if (nextNode != null) {
            current = nextNode;
        } else {
            current = prevNode;
        }

        System.out.println("CLOSE " + name + ": " + display());
    }

    // Move to previous tab
    static void back() {

        if (current == null || current.prev == null) {
            System.out.println("BACK: already at first tab");
            return;
        }

        current = current.prev;

        System.out.println("BACK: " + display());
    }

    // Move to next tab
    static void forward() {

        if (current == null || current.next == null) {
            System.out.println("FORWARD: already at last tab");
            return;
        }

        current = current.next;

        System.out.println("FORWARD: " + display());
    }

    // Display tabs from first to last
    static String display() {

        StringBuilder result = new StringBuilder();

        Node temp = head;

        while (temp != null) {

            if (temp == current) {
                result.append("[").append(temp.name).append("]");
            } else {
                result.append(temp.name);
            }

            temp = temp.next;

            if (temp != null) {
                result.append(" <-> ");
            }
        }

        return result.length() == 0 ? "(empty)" : result.toString();
    }

    // Print tabs from last to first
    static void printBackward() {

        if (head == null) {
            System.out.println("Backward: (empty)");
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        StringBuilder result = new StringBuilder();

        while (temp != null) {

            result.append(temp.name);

            temp = temp.prev;

            if (temp != null) {
                result.append(" <-> ");
            }
        }

        System.out.println("Backward: " + result);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String line = sc.nextLine();

            String[] commands = line.split(",");

            for (String command : commands) {

                command = command.trim();

                if (command.startsWith("OPEN ")) {

                    String name = command.substring(5).trim();
                    open(name);

                } else if (command.equals("CLOSE")) {

                    close();

                } else if (command.equals("BACK")) {

                    back();

                } else if (command.equals("FORWARD")) {

                    forward();

                } else if (command.equals("PRINT BACKWARD")) {

                    printBackward();
                }
            }
        }

        sc.close();
    }
}
