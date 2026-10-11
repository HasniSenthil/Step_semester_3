package oop.assignment_problems;
import java.util.Scanner;

public class CancelledOrdersCleanup {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node removeAll(Node head, int code) {

        // Remove matching nodes from the front
        while (head != null && head.data == code) {
            head = head.next;
        }

        // Remove matching nodes from the remaining list
        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        System.out.println("Enter order codes:");

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

        System.out.print("Enter code to remove: ");
        int code = sc.nextInt();

        head = removeAll(head, code);

        System.out.println("Final list:");
        display(head);

        sc.close();
    }
}
