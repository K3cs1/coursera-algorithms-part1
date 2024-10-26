package org.coursera.module7.quick_sort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSort() {
        int[] array = {9, 4, 5, 7, 6, 2, 1, 3, 8, 10};
        Comparable[] comparables = new Comparable[array.length];
        for (int i = 0; i < array.length; i++) {
            comparables[i] = array[i];
        }
        QuickSort quickSort = new QuickSort();
        quickSort.sort(comparables);
        int counter = 1;
        for (Comparable c : comparables) {
            assertEquals(counter++, c);
        }
    }

}