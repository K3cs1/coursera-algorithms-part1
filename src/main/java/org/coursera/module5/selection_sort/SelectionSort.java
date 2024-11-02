package org.coursera.module5.selection_sort;

import java.util.Arrays;

/**
 * A Java implementation of Selection Sort algorithm that sorts an array of integers.
 * The class contains an inner Element class implementing Comparable interface for comparison operations.
 * It provides functionality to initialize the sorting algorithm with an integer array and
 * performs the selection sort by repeatedly finding the minimum element and placing it at the beginning.
 * The implementation includes helper methods for comparing and exchanging elements during the sort process.
 */
public class SelectionSort {

    private Element[] elements;

    class Element implements Comparable<Integer> {
        private Integer value;

        public Element(Integer value) {
            this.value = value;
        }

        @Override
        public int compareTo(Integer o) {
            return this.value.compareTo(o);
        }
    }

    public SelectionSort(int[] array) {
        elements = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            elements[i] = new Element(array[i]);
        }
    }

    public int[] sort() {
        int n = elements.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(elements[j], elements[min])) {
                    min = j;
                }
            }
            exch(elements, i, min);
        }
        return Arrays.stream(elements)
                .mapToInt(e -> e.value)
                .toArray();
    }

    private void exch(Comparable<Integer>[] array, int i, int j) {
        Comparable<Integer> temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean less(Comparable<Integer> v, Comparable<Integer> w) {
        return ((Element) v).value.compareTo(((Element) w).value) < 0;
    }

}
