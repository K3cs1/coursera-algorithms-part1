package org.coursera.module4.interview_questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackWithMaxTest {

    @Test
    void testStackWithMax() {
        StackWithMax stack = new StackWithMax();
        stack.push(1.6);
        stack.push(4.2);
        stack.push(2.7);
        stack.push(3.1);
        stack.push(1.1);

        assertEquals(4.2, stack.max());
        assertEquals(1.1, stack.pop());
        assertEquals(4.2, stack.max());
        assertEquals(3.1, stack.pop());
        assertEquals(4.2, stack.max());
        assertEquals(2.7, stack.pop());
        assertEquals(4.2, stack.max());
        assertEquals(4.2, stack.pop());
        assertEquals(1.6, stack.max());
        assertEquals(1.6, stack.pop());
        assertThrows(IllegalStateException.class, stack::pop);
    }

}