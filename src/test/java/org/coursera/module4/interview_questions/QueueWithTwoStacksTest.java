package org.coursera.module4.interview_questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueWithTwoStacksTest {

    @Test
    void test() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.dequeue());
        q.enqueue(4);
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
    }
}