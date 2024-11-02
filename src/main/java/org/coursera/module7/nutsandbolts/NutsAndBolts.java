package org.coursera.module7.nutsandbolts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class NutsAndBolts {

    public void matchPairs(Comparable[] nuts, Comparable[] bolts, int low, int high) {
        if (low < high) {

            int randomIndex = StdRandom.uniformInt(low, high + 1);
            exch(bolts, low, randomIndex);

            int pivotIndex = partition(nuts, low, high, bolts[low]);

            partition(bolts, low, high, nuts[pivotIndex]);

            matchPairs(nuts, bolts, low, pivotIndex - 1);
            matchPairs(nuts, bolts, pivotIndex + 1, high);
        }
    }

    private int partition(Comparable[] arr, int low, int high, Comparable pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (less(arr[j], pivot)) {
                exch(arr, i, j);
                i++;
            } else if (arr[j].compareTo(pivot) == 0) {
                exch(arr, j, high);
                j--;
            }
        }
        exch(arr, i, high);
        return i;
    }

    private void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void printArray(Comparable[] arr) {
        Arrays.stream(arr).forEach(ch -> StdOut.print(ch + " "));
        StdOut.println();
    }

}

