package org.coursera.module7.nutsandbolts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutsAndBoltsTest {

    @Test
    void testNutsAndBolts() {

        Character[] nuts = {'@', '#', '$', '%', '^', '&'};
        Character[] bolts = {'$', '%', '&', '^', '@', '#'};

        Comparable<Character>[] nutsComparable = new Comparable[nuts.length];
        System.arraycopy(nuts, 0, nutsComparable, 0, nuts.length);

        Comparable<Character>[] boltsComparable = new Comparable[bolts.length];
        System.arraycopy(bolts, 0, boltsComparable, 0, bolts.length);

        NutsAndBolts nb = new NutsAndBolts();
        nb.matchPairs(nutsComparable, boltsComparable, 0, nuts.length - 1);

        nb.printArray(nutsComparable);
        nb.printArray(boltsComparable);

        assertArrayEquals(nutsComparable, boltsComparable);
    }

}