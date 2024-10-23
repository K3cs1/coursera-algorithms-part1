package org.coursera.module5.shell_short;

import java.util.Arrays;

public class ShellShort {

    static class Element implements Comparable<Element> {

        private Integer value;

        public Element(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Element o) {
            return this.value.compareTo(o.value);
        }

        public Integer getValue() {
            return value;
        }
    }

    private Element[] elements;

    public ShellShort(int[] a) {
        elements = new Element[a.length];
        for (int i = 0; i < a.length; i++) {
            elements[i] = new Element(a[i]);
        }
    }

    public int[] sort() {
        int N = elements.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(elements[j], elements[j - h]); j -= h) {
                    exch(elements, j, j - h);
                }
            }
            h = h / 3;
        }
        return Arrays.stream(elements).mapToInt(Element::getValue).toArray();
    }

    private void exch(Comparable<Element>[] a, int i, int j) {
        Comparable<Element> swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private boolean less(Comparable<Element> v, Comparable<Element> w) {
        return ((Element) v).getValue().compareTo(((Element) w).getValue()) < 0;
    }

}
