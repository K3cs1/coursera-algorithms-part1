package org.coursera.module7.decimal_dominants;

import java.util.Arrays;

public class DecimalDominants {

    public int[] findDecimalDominants(int[] arr) {
        int n = arr.length;
        int threshold = n / 10;

        int[] candidates = new int[9];
        int[] counts = new int[9];

        Arrays.fill(candidates, Integer.MIN_VALUE);

        for (int num : arr) {
            boolean found = false;

            for (int i = 0; i < 9; i++) {
                if (candidates[i] == num) {
                    counts[i]++;
                    found = true;
                    break;
                }
            }

            if (!found) {

                boolean placed = false;
                for (int i = 0; i < 9; i++) {
                    if (counts[i] == 0) {
                        candidates[i] = num;
                        counts[i] = 1;
                        placed = true;
                        break;
                    }
                }

                if (!placed) {
                    for (int i = 0; i < 9; i++) {
                        counts[i]--;
                    }
                }
            }
        }

        int[] finalCounts = new int[9];
        for (int num : arr) {
            for (int i = 0; i < 9; i++) {
                if (candidates[i] == num) {
                    finalCounts[i]++;
                }
            }
        }

        int[] result = new int[9];
        int resultCount = 0;
        for (int i = 0; i < 9; i++) {
            if (finalCounts[i] > threshold && candidates[i] != Integer.MIN_VALUE) {
                result[resultCount++] = candidates[i];
            }
        }

        return Arrays.copyOf(result, resultCount);
    }

}

