package org.coursera.module8.heapsort;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HeapTest {

    @Test
    void testHeap() {
        int n = 10;
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++) {
            array[i] = StdRandom.uniformInt(1,n);
        }
        Heap heap = new Heap();
        heap.sort(array);

        Arrays.stream(array).forEach(num -> System.out.print(num + " "));
    }

}