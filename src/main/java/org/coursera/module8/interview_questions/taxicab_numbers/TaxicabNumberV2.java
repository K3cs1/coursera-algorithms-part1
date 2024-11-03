package org.coursera.module8.interview_questions.taxicab_numbers;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class TaxicabNumberV2 {

    static class Pair implements Comparable<Pair> {
        int a, b;
        long sum;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
            this.sum = (long) a * a * a + (long) b * b * b;
        }

        public int compareTo(Pair other) {
            return Long.compare(this.sum, other.sum);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        MinPQ<Pair> pq = new MinPQ<>(n);
        for (int i = 1; i < n; i++) {
            pq.insert(new Pair(i, i + 1));
        }

        Pair previous = null;

        while (!pq.isEmpty()) {
            Pair current = pq.delMin();

            if (previous != null && current.sum == previous.sum) {
                StdOut.println(previous.a + "^3 + " + previous.b + "^3 = "
                        + current.a + "^3 + " + current.b + "^3 = " + current.sum);
            }
            previous = current;

            if (current.b < n - 1) {
                pq.insert(new Pair(current.a, current.b + 1));
            }
        }
    }
}

