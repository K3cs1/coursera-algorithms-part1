package org.coursera.module8.interview_questions.taxicab_numbers;

import edu.princeton.cs.algs4.StdOut;

public class TaxicabNumberV1 {

    static class Pair {
        int a, b;
        long sum;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
            this.sum = (long) a * a * a + (long) b * b * b;
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Pair[] pairs = new Pair[n * n];
        int index = 0;
        for (int a = 1; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                pairs[index++] = new Pair(a, b);
            }
        }

        for (int i = 1; i < index; i++) {
            Pair temp = pairs[i];
            int j = i - 1;
            while (j >= 0 && pairs[j].sum > temp.sum) {
                pairs[j + 1] = pairs[j];
                j--;
            }
            pairs[j + 1] = temp;
        }

        for (int i = 1; i < index; i++) {
            if (pairs[i].sum == pairs[i - 1].sum) {
                StdOut.println(pairs[i - 1].a + "^3 + " + pairs[i - 1].b + "^3 = "
                        + pairs[i].a + "^3 + " + pairs[i].b + "^3 = " + pairs[i].sum);
            }
        }
    }
}

