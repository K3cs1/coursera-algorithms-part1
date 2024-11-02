package org.coursera.module8.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxPQTest {

    @Test
    void test() {
        MaxPQ<Integer> pq = new MaxPQ<>(10);
        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        pq.insert(4);
        pq.insert(5);
        pq.insert(6);
        pq.insert(7);
        pq.insert(8);
        pq.insert(9);
        pq.insert(10);

        assertEquals(10, pq.size());
        assertEquals(10, pq.delMax());
        assertEquals(9, pq.delMax());
        assertEquals(8, pq.delMax());

    }

}