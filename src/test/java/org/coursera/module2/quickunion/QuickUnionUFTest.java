package org.coursera.module2.quickunion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionUFTest {

    @Test
    void testUnion() {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
    }

    @Test
    void testConnected() {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
        assertTrue(uf.connected(0, 2));
    }

    @Test
    void testNotConnected() {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
        assertFalse(uf.connected(0, 3));
    }

}