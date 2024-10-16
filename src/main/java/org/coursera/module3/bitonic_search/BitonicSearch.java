package org.coursera.module3.bitonic_search;

/*
Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers.
Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.

Standard version: Use ~3lgn compares in the worst case.

Signing bonus: Use ~2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than
~2lgn compares in the worst case).
*/
public class BitonicSearch {

    private int findPeak(int[] arr, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[mid + 1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int binarySearchAscending(int[] arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearchDescending(int[] arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public int bitonicSearch(int[] arr, int target) {
        int n = arr.length;
        int peak = findPeak(arr, 0, n - 1);
        int result = binarySearchAscending(arr, 0, peak, target);
        if (result != -1) {
            return result;
        }
        return binarySearchDescending(arr, peak + 1, n - 1, target);
    }

}
