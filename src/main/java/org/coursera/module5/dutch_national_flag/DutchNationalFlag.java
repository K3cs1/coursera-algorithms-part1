package org.coursera.module5.dutch_national_flag;

public class DutchNationalFlag {

    public void dutchNationalFlagSort(int[] buckets) {
        int low = 0, mid = 0;
        int high = buckets.length - 1;

        while (mid <= high) {
            switch (buckets[mid]) {
                case 0: // red
                    swap(buckets, low, mid);
                    low++;
                    mid++;
                    break;
                case 1: // white
                    mid++;
                    break;
                case 2: // blue
                    swap(buckets, mid, high);
                    high--;
                    break;
            }
        }
    }

    private void swap(int[] buckets, int i, int j) {
        int temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

}
