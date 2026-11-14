package oop.assignment_problems;
import java.util.Scanner;

public class LastButKAnnouncement {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static String kthFromEnd(Node head, int k) {

        Node lead = head;
        Node trail = head;

        // Move lead k nodes ahead
        for (int i = 0; i < k; i++) {

            if (lead == null) {
                return "Invalid k";
            }

            lead = lead.next;
        }

        // Move both pointers together
        while (lead != null) {

            lead = lead.next;
            trail = trail.next;
        }

        return String.valueOf(trail.data);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {

            int value = sc.nextInt();

            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int k = sc.nextInt();

        String result = kthFromEnd(head, k);

        if (result.equals("Invalid k")) {
            System.out.println("Invalid k");
        } else {
            System.out.println(k + " from the end: " + result);
        }

        sc.close();
    }
}
