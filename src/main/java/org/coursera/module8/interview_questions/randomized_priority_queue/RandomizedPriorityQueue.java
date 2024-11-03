package org.coursera.module8.interview_questions.randomized_priority_queue;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedPriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;

    public RandomizedPriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; // Index 0 is unused
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Key key) {
        pq[++size] = key;
        swim(size);
    }

    public Key sample() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        int randomIndex = StdRandom.uniformInt(1, size + 1);
        return pq[randomIndex];
    }

    public Key delRandom() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        int randomIndex = StdRandom.uniformInt(1, size + 1);
        Key randomKey = pq[randomIndex];

        swap(randomIndex, size);
        pq[size--] = null;

        if (randomIndex <= size) {
            sink(randomIndex);
            swim(randomIndex);
        }

        return randomKey;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        RandomizedPriorityQueue<Integer> rpq = new RandomizedPriorityQueue<>(10);
        rpq.insert(10);
        rpq.insert(20);
        rpq.insert(5);
        rpq.insert(15);

        StdOut.println("Sampled element: " + rpq.sample());
        StdOut.println("Removed random element: " + rpq.delRandom());
        StdOut.println("Removed random element: " + rpq.delRandom());
    }
}

