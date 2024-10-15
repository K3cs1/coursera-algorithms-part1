package org.coursera.module2.weightedquickunion;

//Memory calculation
public class WeightedQuickUnionUF { // 16 byte (Object overhead)
    private int[] id;               // 8 byte + (4N + 24 byte) each -> reference + int[] array
    private int[] sz;               // 8 byte + (4N + 24 byte) each -> reference + int[] array
    private int count;              // 4 bytes (int)
                                    // 4 bytes padding
    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    // ...
    // Sum 8N + 88 ~ 8N bytes
}
