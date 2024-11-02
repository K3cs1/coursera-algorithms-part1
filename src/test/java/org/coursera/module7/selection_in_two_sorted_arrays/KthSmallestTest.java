package org.coursera.module7.selection_in_two_sorted_arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KthSmallestTest {

    @Test
    void testFindKthSmallest() {
        KthSmallest kthSmallest = new KthSmallest();
        assertEquals(1, kthSmallest.findKthSmallest(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 1));
        assertEquals(2, kthSmallest.findKthSmallest(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 2));
    }

    @Test
    void testFindKthSmallest2() {
        KthSmallest kthSmallest = new KthSmallest();
        assertEquals(9, kthSmallest.findKthSmallest(new int[]{1, 3, 8, 9, 15},
                new int[]{7, 11, 18, 19, 21, 25}, 5));
    }

}