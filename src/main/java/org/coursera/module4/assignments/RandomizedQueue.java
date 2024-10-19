package org.coursera.module4.assignments;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[size++] = item;
    }

    private void resize(int i) {
        Item[] copy = (Item[]) new Object[i];
        if (size >= 0) {
            System.arraycopy(queue, 0, copy, 0, size);
        }
        queue = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size);
        Item item = queue[index];
        queue[index] = queue[size - 1];
        queue[size] = null;
        size--;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return queue[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] copy;
        private int current = 0;

        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[size];
            System.arraycopy(queue, 0, copy, 0, size);
            StdRandom.shuffle(copy);
        }

        @Override
        public boolean hasNext() {
            return current < copy.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return copy[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        StdOut.println(rq.dequeue());
        StdOut.println(rq.sample());

        for (int item : rq) {
            StdOut.print(item + " ");
        }
    }

}
