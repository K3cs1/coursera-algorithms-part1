package org.coursera.module9.interview_questions.inorder_traversal_with_constant_extra_space;

import edu.princeton.cs.algs4.StdOut;

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class MorrisTraversal {

    public static void inorderTraversal(Node root) {
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                StdOut.print(current.key + " ");
                current = current.right;
            } else {
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    StdOut.print(current.key + " ");
                    current = current.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(17);

        StdOut.println("Inorder traversal of the BST using Morris Traversal:");
        inorderTraversal(root);
    }
}
