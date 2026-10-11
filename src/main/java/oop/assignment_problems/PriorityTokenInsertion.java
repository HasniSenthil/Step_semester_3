package oop.assignment_problems;

import java.util.Scanner;

public class PriorityTokenInsertion {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        Node head;

        void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
        }

        void addLast(int token) {
            Node newNode = new Node(token);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        void insertAt(int index, int token) {
            if (index == 0) {
                addFirst(token);
                return;
            }

            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node newNode = new Node(token);
            newNode.next = current.next;
            current.next = newNode;
        }

        void display() {
            Node current = head;

            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.print("Enter number of starting tokens: ");
        int n = sc.nextInt();

        System.out.println("Enter starting tokens:");
        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        System.out.print("Enter number of operations: ");
        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            String operation = sc.next();

            switch (operation) {
                case "addFirst":
                    list.addFirst(sc.nextInt());
                    break;

                case "addLast":
                    list.addLast(sc.nextInt());
                    break;

                case "insertAt":
                    int index = sc.nextInt();
                    int token = sc.nextInt();
                    list.insertAt(index, token);
                    break;

                default:
                    System.out.println("Invalid operation");
            }
        }

        System.out.println("Final list:");
        list.display();

        sc.close();
    }
}