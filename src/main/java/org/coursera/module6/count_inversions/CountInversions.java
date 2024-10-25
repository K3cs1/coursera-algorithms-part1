package org.coursera.module6.count_inversions;

import edu.princeton.cs.algs4.StdOut;

public class CountInversions {

    public static int countInversions(int[] array) {
        int[] aux = new int[array.length];
        return countInversions(array, aux, 0, array.length - 1);
    }

    private static int countInversions(int[] array, int[] aux, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;
        int inversions = 0;

        inversions += countInversions(array, aux, low, mid);
        inversions += countInversions(array, aux, mid + 1, high);

        inversions += mergeAndCount(array, aux, low, mid, high);

        return inversions;
    }

    private static int mergeAndCount(int[] array, int[] aux, int low, int mid, int high) {

        for (int k = low; k <= high; k++) {
            aux[k] = array[k];
        }

        int i = low, j = mid + 1, inversions = 0;

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
                inversions += (mid - i + 1);
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 3, 5};
        int inversionCount = countInversions(array);
        StdOut.println("Number of inversions: " + inversionCount);
    }
}

