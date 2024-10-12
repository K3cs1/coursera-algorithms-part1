package org.coursera.module2.interviewquestions.union_find_with_specific_canonical_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindSpecificTest {

    @Test
    void testUnionFind() {
        UnionFindSpecific unionFind = new UnionFindSpecific(10);
        unionFind.union(1, 2);
        unionFind.union(2, 6);
        unionFind.union(6, 9);
        assertEquals(9, unionFind.find(1));
        assertEquals(9, unionFind.find(2));
        assertEquals(9, unionFind.find(6));
        assertEquals(9, unionFind.find(9));

        unionFind.union(3, 4);
        unionFind.union(4, 5);
        assertEquals(5, unionFind.find(3));

        unionFind.union(5, 9);
        assertEquals(9, unionFind.find(1));
    }

}