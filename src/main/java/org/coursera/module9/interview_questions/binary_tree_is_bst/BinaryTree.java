package org.coursera.module9.interview_questions.binary_tree_is_bst;

import edu.princeton.cs.algs4.StdOut;

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class BinaryTree {
    public static boolean isBST(Node root) {
        return isBSTUtil(root, null, null);
    }

    private static boolean isBSTUtil(Node node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.key <= min) || (max != null && node.key >= max)) {
            return false;
        }

        return isBSTUtil(node.left, min, node.key) && isBSTUtil(node.right, node.key, max);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(17);

        if (isBST(root)) {
            StdOut.println("The tree is a Binary Search Tree");
        } else {
            StdOut.println("The tree is NOT a Binary Search Tree");
        }
    }
}
