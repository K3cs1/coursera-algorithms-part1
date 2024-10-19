package org.coursera.module4.assignments;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        StdOut.println("Deque is empty: " + deque.isEmpty());
        StdOut.println("Deque size: " + deque.size());

        deque.addFirst(9);
        deque.addLast(4);
        deque.addFirst(2);
        deque.addLast(7);
        deque.addFirst(0);
        deque.addLast(3);

        StdOut.println("Deque size after adding 6 items: " + deque.size());

        StdOut.println("Removed from front: " + deque.removeFirst());  // 0
        StdOut.println("Removed from back: " + deque.removeLast());    // 3
        StdOut.println("Removed from front: " + deque.removeFirst());  // 2
        StdOut.println("Removed from back: " + deque.removeLast());    // 7
        StdOut.println("Removed from front: " + deque.removeFirst());  // 9
        StdOut.println("Removed from back: " + deque.removeLast());    // 4

        StdOut.println("Deque is empty after removals: " + deque.isEmpty());  // true
        StdOut.println("Deque size after removals: " + deque.size());         // 0

        deque.addFirst(6);
        deque.addLast(7);
        deque.addFirst(3);

        for (int item : deque) {
            StdOut.print(item + " ");  // 3 6 7
        }
        StdOut.println();
    }

}
