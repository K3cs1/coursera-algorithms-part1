package org.coursera.module4.stack_and_queue_applications;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Question {

    public static void main(String[] args) {
        int n = 50;
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        for (Integer digit : stack){
            StdOut.print(digit);
        }
        StdOut.println();
    }

}
