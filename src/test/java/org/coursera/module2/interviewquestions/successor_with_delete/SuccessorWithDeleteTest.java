package org.coursera.module2.interviewquestions.successor_with_delete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuccessorWithDeleteTest {

    @Test
    void testFindSuccessor() {
        SuccessorWithDelete successor = new SuccessorWithDelete(10);
        successor.remove(2);
        successor.remove(3);
        successor.remove(5);

        assertEquals(4, successor.findSuccessor(2));
        assertEquals(4, successor.findSuccessor(3));
        assertEquals(6, successor.findSuccessor(5));
        assertEquals(7, successor.findSuccessor(7)); //Not removed
    }

}