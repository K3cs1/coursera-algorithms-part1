package org.coursera.module12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeparateChainingHashSTTest {

    @Test
    void testHashTableOperations() {
        SeparateChainingHashST<String, Integer> hashTable = new SeparateChainingHashST<>();
        hashTable.put("apple", 5);
        hashTable.put("banana", 10);
        hashTable.put("banana", 20);
        assertEquals(5, hashTable.get("apple"));
        assertEquals(20, hashTable.get("banana"));
    }

}