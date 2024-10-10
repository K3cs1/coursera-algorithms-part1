package org.coursera.module2.quickfind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindUFTest {

    @Test
    void testUnion() {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
    }

    @Test
    void testConnected() {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
        assertTrue(uf.connected(0, 2));
    }

    @Test
    void testNotConnected() {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(0, 1);
        uf.union(1, 2);
        assertFalse(uf.connected(0, 3));
    }
}