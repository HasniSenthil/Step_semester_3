package oop.class_problems;

public class RemoveFirstK {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeFirstKNodes(Node head, int k) {

        if (head == null || k == 0) {
            return head;
        }

        for (int i = 0; i < k && head != null; i++) {
            head = head.next;
        }

        return head;
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

        head = removeFirstKNodes(head, 2);

        display(head);

        Node head2 = new Node(1);
        head2.next = new Node(2);

        head2 = removeFirstKNodes(head2, 5);

        display(head2);
    }
}
