package org.coursera.module10.interview_questions.generalized_queue;

public class GeneralizedQueue<T> {

    private class Node {
        T value;
        Node left, right;
        int height;
        int size;

        Node(T value) {
            this.value = value;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateNode(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNode(y);
        updateNode(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateNode(x);
        updateNode(y);

        return y;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    public void append(T value) {
        root = insert(root, value, size(root));
    }

    private Node insert(Node node, T value, int index) {
        if (node == null) {
            return new Node(value);
        }

        if (index <= size(node.left)) {
            node.left = insert(node.left, value, index);
        } else {
            node.right = insert(node.right, value, index - size(node.left) - 1);
        }

        updateNode(node);

        return balance(node);
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    public T removeFront() {
        if (root == null) return null;
        T value = get(0);
        root = remove(root, 0);
        return value;
    }

    public T get(int i) {
        return get(root, i);
    }

    private T get(Node node, int index) {
        int leftSize = size(node.left);
        if (index < leftSize) {
            return get(node.left, index);
        } else if (index > leftSize) {
            return get(node.right, index - leftSize - 1);
        } else {
            return node.value;
        }
    }

    public void remove(int i) {
        root = remove(root, i);
    }

    private Node remove(Node node, int index) {
        if (node == null) return null;

        int leftSize = size(node.left);
        if (index < leftSize) {
            node.left = remove(node.left, index);
        } else if (index > leftSize) {
            node.right = remove(node.right, index - leftSize - 1);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = getMin(node.right);
                node.value = minNode.value;
                node.right = remove(node.right, 0);
            }
        }

        if (node == null) return null;

        updateNode(node);
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }
}

