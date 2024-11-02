package org.coursera.module7.selection_in_two_sorted_arrays;

public class KthSmallest {

    public int findKthSmallest(int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;

        if (m > n) {
            return findKthSmallest(b, a, k);
        }

        int low = Math.max(0, k - n);
        int high = Math.min(k, m);

        while (low <= high) {
            int i = (low + high) / 2;
            int j = k - i;

            int aLeft = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : a[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : b[j];

            if (aLeft <= bRight && bLeft <= aRight) {
                return Math.max(aLeft, bLeft);
            } else if (aLeft > bRight) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        throw new IllegalArgumentException("No valid kth element");
    }
}

