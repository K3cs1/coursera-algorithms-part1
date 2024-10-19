package org.coursera.module4.interview_questions;

import edu.princeton.cs.algs4.Stack;

/*
Stack with max.
Create a data structure that efficiently supports the stack operations (push and pop) and also a
return-the-maximum operation. Assume the elements are real numbers so that you can compare them.
*/
public class StackWithMax {
    private Stack<Double> stack;
    private Stack<Double> maxStack;

    public StackWithMax() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(double value) {
        stack.push(value);
        if (maxStack.isEmpty() || value >= maxStack.peek()) {
            maxStack.push(value);
        }
    }

    public double pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        double value = stack.pop();
        if (value == maxStack.peek()) {
            maxStack.pop();
        }
        return value;
    }

    public double max() {
        if (maxStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return maxStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
