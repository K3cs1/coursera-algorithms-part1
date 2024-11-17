package org.coursera.module12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearProbingHashSTTest {

    @Test
    void testHashTableOperations() {
        LinearProbingHashST<String, Integer> hashTable = new LinearProbingHashST<>();
        hashTable.put("Alice", 25);
        hashTable.put("Bob", 30);
        hashTable.put("Charlie", 35);
        assertEquals(30, hashTable.get("Bob"));
        assertEquals(25, hashTable.get("Alice"));
        assertEquals(35, hashTable.get("Charlie"));
    }

}