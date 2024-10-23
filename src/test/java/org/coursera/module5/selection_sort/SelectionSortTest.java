package org.coursera.module5.selection_sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void sort() {
        int[] arr = {5, 2, 4, 6, 1, 3};
        SelectionSort selectionSort = new SelectionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, selectionSort.sort());
    }

}