package oop.assignment_problems;

import java.util.Scanner;

public class TrainCoachesBothWays {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        void addLast(String name) {
            Node newNode = new Node(name);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        void remove(String name) {
            Node current = head;

            // Find the coach
            while (current != null && !current.name.equals(name)) {
                current = current.next;
            }

            // Coach not found
            if (current == null) {
                return;
            }

            // Update previous node or head
            if (current.prev == null) {
                head = current.next;
            } else {
                current.prev.next = current.next;
            }

            // Update next node or tail
            if (current.next == null) {
                tail = current.prev;
            } else {
                current.next.prev = current.prev;
            }
        }

        void printForward() {
            System.out.print("Forward: ");
            Node current = head;

            while (current != null) {
                System.out.print(current.name);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }

            System.out.println();
        }

        void printBackward() {
            System.out.print("Backward: ");
            Node current = tail;

            while (current != null) {
                System.out.print(current.name);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }
                current = current.prev;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList train = new DoublyLinkedList();

        System.out.print("Enter number of coaches: ");
        int n = sc.nextInt();

        System.out.println("Enter coach names:");
        for (int i = 0; i < n; i++) {
            train.addLast(sc.next());
        }

        System.out.print("Enter coach to remove: ");
        String name = sc.next();

        train.remove(name);

        train.printForward();
        train.printBackward();

        sc.close();
    }
}
