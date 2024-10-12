package org.coursera.module2.interviewquestions.union_find_with_specific_canonical_element;

/*
Union-find with specific canonical element. Add a method find() to the union-find data type so that

find(i) returns the largest element in the connected component containing i. The operations, union(), connected(), and

find() should all take logarithmic time or better.

For example, if one of the connected components is {1,2,6,9}, then the

find() method should return 9 for each of the four elements in the connected components.
*/
public class UnionFindSpecific {

    private int[] id;
    private int[] sz;
    private int[] max;

    public UnionFindSpecific(int N) {
        id = new int[N];
        sz = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            max[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (connected(i, j)) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            max[j] = Math.max(max[j], max[p]);
        } else {
            id[j] = i;
            sz[i] += sz[j];
            max[i] = Math.max(max[i], max[j]);
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int i) {
        return max[root(i)];
    }
}
