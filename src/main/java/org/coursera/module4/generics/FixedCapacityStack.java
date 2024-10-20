package org.coursera.module4.generics;

public class FixedCapacityStack<Item> {

    private Item[] s;
    private int N;

    @SuppressWarnings(value = "unchecked")
    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        return s[--N];
    }

}
