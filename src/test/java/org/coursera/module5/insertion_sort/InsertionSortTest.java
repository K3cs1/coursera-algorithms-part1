package org.coursera.module5.insertion_sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void testInsertionSort() {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
        int[] expected = { 0, 1, 2, 4, 6, 9, 12, 23, 34 };

        InsertionSort is = new InsertionSort(input);
        assertArrayEquals(expected, is.sort());
    }

}