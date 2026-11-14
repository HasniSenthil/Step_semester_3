package oop.class_problems;

public class InsertCircularEnd {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node insertAtEndCircular(Node head, int value) {

        Node newNode = new Node(value);

        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node temp = head;

        while (temp.next != head) {
            temp = temp.next;
        }

        temp.next = newNode;

        newNode.next = head;

        return head;
    }

    static void display(Node head) {

        if (head == null) {
            System.out.println();
            return;
        }

        Node temp = head;

        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);

        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head;

        head = insertAtEndCircular(head, 4);

        display(head);

        Node empty = null;

        empty = insertAtEndCircular(empty, 9);

        display(empty);
    }
}
