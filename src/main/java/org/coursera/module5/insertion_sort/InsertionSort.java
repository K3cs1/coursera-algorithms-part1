package org.coursera.module5.insertion_sort;

import java.util.Arrays;

public class InsertionSort {

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

    public InsertionSort(int[] array) {
        elements = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            elements[i] = new Element(array[i]);
        }
    }

    public int[] sort() {
        for (int i = 0; i < elements.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(elements[j], elements[j - 1])) {
                    exch(elements, j, j - 1);
                }
            }
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
