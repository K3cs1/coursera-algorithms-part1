package org.coursera.module4.linked_stack_of_strings;

public class LinkedStackOfStrings { // 16 bytes (object overhead)
    private Node first = null;

    private class Node {            // 8 bytes (inner class extra overhead)
        String item;                // 8 bytes reference to String
        Node next;                  // 8 bytes reference to Node
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
}

// Sum 40 bytes per stack node
