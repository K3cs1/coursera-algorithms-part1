package org.coursera.module3.threesum_in_quadratic_time;

import edu.princeton.cs.algs4.StdOut;

/*
3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to
n square in the worst case. You may assume that you can sort the n integers in time proportional to
n square or better.
*/
public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N - 2; i++) {
            if(i > 0 && a[i] == a[i-1]) {
                continue;
            }
            int lo = i + 1;
            int hi = N - 1;
            while (lo < hi) {
                int sum = a[i] + a[lo] + a[hi];
                if (sum > 0) {
                    hi--;
                } else if (sum < 0) {
                    lo++;
                } else {
                    StdOut.println("Triplet found: " + a[i] + ", " + a[lo] + ", " + a[hi]);
                    count++;
                    lo++;
                    hi--;
                }
            }
        }
        return count;
    }

}
