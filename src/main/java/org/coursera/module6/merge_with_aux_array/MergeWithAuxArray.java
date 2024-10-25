package org.coursera.module6.merge_with_aux_array;

import edu.princeton.cs.algs4.StdOut;

public class MergeWithAuxArray {

    public static void merge(int[] a, int n) {

        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            aux[i] = a[i];
        }

        int i = 0;
        int j = n;
        int k = 0;

        while (i < n && j < 2 * n) {
            if (aux[i] <= a[j]) {
                a[k++] = aux[i++];
            } else {
                a[k++] = a[j++];
            }
        }

        while (i < n) {
            a[k++] = aux[i++];
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 2, 4, 6, 8};
        int n = a.length / 2;
        merge(a, n);

        for (int num : a) {
            StdOut.print(num + " ");
        }
    }

}

