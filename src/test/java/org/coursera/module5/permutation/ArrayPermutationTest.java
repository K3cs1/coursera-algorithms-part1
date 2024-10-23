package org.coursera.module5.permutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPermutationTest {

    @Test
    void testPermutationPositiveCase() {
        ArrayPermutation arrayPermutation = new ArrayPermutation();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {5, 4, 3, 2, 1};
        assertTrue(arrayPermutation.arePermutations(a, b));
    }

    @Test
    void testPermutationNegativeCase() {
        ArrayPermutation arrayPermutation = new ArrayPermutation();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {5, 4, 3, 2, 6};
        assertFalse(arrayPermutation.arePermutations(a, b));
    }

}