package oop.class_problems;

public class ReverseFirstK {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {

        Node prev = null;
        Node current = head;
        Node next = null;

        int count = 0;

        while (current != null && count < k) {

            next = current.next;

            current.next = prev;

            prev = current;
            current = next;

            count++;
        }

        head.next = current;

        return prev;
    }

    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = reverseFirstKNodes(head, 3);

        display(head);
    }
}
