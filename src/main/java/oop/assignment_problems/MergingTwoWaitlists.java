package oop.assignment_problems;

import java.util.Scanner;

public class MergingTwoWaitlists {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Merge two sorted linked lists
    static Node merge(Node first, Node second) {

        Node dummy = new Node(0);
        Node tail = dummy;

        while (first != null && second != null) {

            if (first.data <= second.data) {

                tail.next = first;
                first = first.next;

            } else {

                tail.next = second;
                second = second.next;
            }

            tail = tail.next;
        }

        // Attach remaining nodes
        if (first != null) {
            tail.next = first;
        } else {
            tail.next = second;
        }

        return dummy.next;
    }

    // Create linked list
    static Node createList(Scanner sc, int n) {

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {

            Node newNode = new Node(sc.nextInt());

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }

    // Display linked list
    static void display(Node head) {

        Node temp = head;

        while (temp != null) {

            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // First linked list
        int n1 = sc.nextInt();
        Node first = createList(sc, n1);

        // Second linked list
        int n2 = sc.nextInt();
        Node second = createList(sc, n2);

        // Merge both lists
        Node result = merge(first, second);

        System.out.println("Merged List:");

        display(result);

        sc.close();
    }
}
