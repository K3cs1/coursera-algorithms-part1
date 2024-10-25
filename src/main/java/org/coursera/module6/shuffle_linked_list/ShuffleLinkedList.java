package org.coursera.module6.shuffle_linked_list;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShuffleLinkedList {

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node shuffle(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node mid = findMiddle(head);
        Node secondHalf = mid.next;
        mid.next = null;

        Node left = shuffle(head);
        Node right = shuffle(secondHalf);

        return mergeRandomly(left, right);
    }

    private static Node findMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node mergeRandomly(Node left, Node right) {
        Node dummyHead = new Node(0);
        Node current = dummyHead;

        while (left != null && right != null) {
            if (StdRandom.bernoulli()) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if (left != null) {
            current.next = left;
        } else if (right != null) {
            current.next = right;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node shuffledHead = shuffle(head);

        printList(shuffledHead);
    }

    private static void printList(Node head) {
        Node current = head;
        while (current != null) {
            StdOut.print(current.val + " ");
            current = current.next;
        }
        StdOut.println();
    }

}
