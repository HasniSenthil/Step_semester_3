package oop.class_problems;

public class PrintCircularList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void printCircular(Node head) {

        if (head == null) {
            System.out.println();
            return;
        }

        Node current = head;

        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);

        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = head;

        printCircular(head);

        printCircular(null);
    }
}

