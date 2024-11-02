package org.coursera.module7.decimal_dominants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalDominantsTest {

    @Test
    void testDecimalDominants() {
        int[] arr = {4, 5, 6, 7, 8, 4, 4, 4, 5, 6, 4, 7, 8, 4, 4};
        DecimalDominants dd = new DecimalDominants();
        assertArrayEquals(new int[]{4, 5, 6, 7, 8}, dd.findDecimalDominants(arr));
    }

}