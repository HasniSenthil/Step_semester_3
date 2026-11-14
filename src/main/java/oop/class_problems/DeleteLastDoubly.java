package oop.class_problems;

public class DeleteLastDoubly {

    static class DNode {
        int data;
        DNode prev, next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode deleteLastNodeDoubly(DNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return null;
        }

        DNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.prev.next = null;

        temp.prev = null;

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

        DNode second = new DNode(2);
        DNode third = new DNode(3);

        head.next = second;
        second.prev = head;

        second.next = third;
        third.prev = second;

        head = deleteLastNodeDoubly(head);

        display(head);

        DNode single = new DNode(5);

        single = deleteLastNodeDoubly(single);

        display(single);
    }
}
