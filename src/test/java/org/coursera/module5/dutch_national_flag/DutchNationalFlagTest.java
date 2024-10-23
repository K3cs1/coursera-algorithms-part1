package org.coursera.module5.dutch_national_flag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DutchNationalFlagTest {

    @Test
    void testDutchNationalFlag() {
        int[] array = new int[]{0, 1, 2, 0, 1, 2, 0, 1};
        DutchNationalFlag dnf = new DutchNationalFlag();
        dnf.dutchNationalFlagSort(array);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 1, 2, 2}, array);
    }

}