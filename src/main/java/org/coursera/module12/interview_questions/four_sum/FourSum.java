package org.coursera.module12.interview_questions.four_sum;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.ArrayList;

public class FourSum {

    public static boolean fourSum(int[] a) {
        int n = a.length;
        // HashMap to store sums of pairs and the list of index pairs
        HashMap<Integer, ArrayList<int[]>> sumPairs = new HashMap<>();

        // Step 1: Store all pairs (i, j) and their sums in the hash map
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = a[i] + a[j];
                if (!sumPairs.containsKey(sum)) {
                    sumPairs.put(sum, new ArrayList<>());
                }
                sumPairs.get(sum).add(new int[]{i, j});
            }
        }

        // Step 2: Check for distinct pairs (i, j) and (k, l) with the same sum
        for (int sum : sumPairs.keySet()) {
            ArrayList<int[]> pairs = sumPairs.get(sum);
            for (int[] pair1 : pairs) {
                for (int[] pair2 : pairs) {
                    int i = pair1[0], j = pair1[1];
                    int k = pair2[0], l = pair2[1];
                    if (i != k && i != l && j != k && j != l) {
                        // Distinct indices found
                        StdOut.printf("Found indices: (%d, %d), (%d, %d)%n", i, j, k, l);
                        return true;
                    }
                }
            }
        }

        // No such indices found
        return false;
    }

    public static void main(String[] args) {
        int[] a = {10, 20, 30, 40, 1, 2, 3, 4};
        boolean result = fourSum(a);
        if (!result) {
            StdOut.println("No solution found.");
        }
    }
}
