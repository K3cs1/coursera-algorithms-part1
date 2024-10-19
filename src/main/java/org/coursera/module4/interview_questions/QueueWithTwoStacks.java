package org.coursera.module4.interview_questions;

import edu.princeton.cs.algs4.Stack;

/*
Queue with two stacks. Implement a queue with two stacks so that each queue operations
takes a constant amortized number of stack operations.
*/
public class QueueWithTwoStacks<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;

    public QueueWithTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(T item) {
        stack1.push(item);
    }

    public T dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

}
