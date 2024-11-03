package org.coursera.module8.interview_questions.dynamic_median;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class DynamicMedian {
    private MaxPQ<Integer> left;
    private MaxPQ<Integer> right;

    public DynamicMedian() {
        left = new MaxPQ<>();
        right = new MaxPQ<>();
    }

    public void insert(int num) {
        if (left.isEmpty() || num <= left.max()) {
            left.insert(num);
        } else {
            right.insert(-num);
        }

        balanceHeaps();
    }

    public int findMedian() {
        if (left.isEmpty()) {
            throw new IllegalStateException("No elements available");
        }
        return left.max();
    }

    public int removeMedian() {
        if (left.isEmpty()) {
            throw new IllegalStateException("No elements to remove");
        }

        int median = left.delMax();
        balanceHeaps();
        return median;
    }

    private void balanceHeaps() {
        if (left.size() > right.size() + 1) {
            right.insert(-left.delMax());
        } else if (right.size() > left.size()) {
            left.insert(-right.delMax());
        }
    }

    public static void main(String[] args) {
        DynamicMedian dm = new DynamicMedian();
        dm.insert(5);
        dm.insert(2);
        dm.insert(10);
        StdOut.println("Median: " + dm.findMedian()); // Should print 5
        StdOut.println("Removed Median: " + dm.removeMedian()); // Should remove 5
        StdOut.println("New Median: " + dm.findMedian()); // Should print 2
    }

}