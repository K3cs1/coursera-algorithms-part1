package org.coursera.module3.bitonic_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitonicSearchTest {

    @Test
    void testBitonicSearch() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1};
        int key = 7;
        int expected = 6;
        BitonicSearch bs = new BitonicSearch();
        int actual = bs.bitonicSearch(array, key);
        assertEquals(expected, actual);
    }

    @Test
    void testBitonicSearch2() {
        int[] array = {1, 3, 8, 12, 9, 5, 2};
        int key = 9;
        int expected = 4;
        BitonicSearch bs = new BitonicSearch();
        int actual = bs.bitonicSearch(array, key);
        assertEquals(expected, actual);
    }

}