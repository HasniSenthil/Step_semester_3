package oop.class_problems;

public class InsertTailDoubly {

    static class DNode {
        int data;
        DNode prev, next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode insertAtTailDoubly(DNode head, int value) {

        DNode newNode = new DNode(value);

        if (head == null) {
            return newNode;
        }

        DNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.prev = temp;

        return head;
    }

    static void display(DNode head) {
        DNode temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;

        head = insertAtTailDoubly(head, 4);

        display(head);

        DNode empty = null;
        empty = insertAtTailDoubly(empty, 7);
        display(empty);
    }
}
