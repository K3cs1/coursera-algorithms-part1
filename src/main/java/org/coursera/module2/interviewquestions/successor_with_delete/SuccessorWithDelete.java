package org.coursera.module2.interviewquestions.successor_with_delete;

public class SuccessorWithDelete {
    private int[] id;
    private int[] sz;
    private int[] sucessor;
    private int N;

    public SuccessorWithDelete(int N) {
        this.N = N;
        id = new int[N];
        sz = new int[N];
        sucessor = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            sucessor[i] = i;
        }
    }

    private int root(int x) {
        while (x != id[x]) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = root(x);
        int rootY = root(y);

        if (rootX == rootY) {
            return;
        }

        if (sz[rootX] < sz[rootY]) {
            id[rootX] = rootY;
            sz[rootY] += sz[rootX];
            sucessor[rootY] = Math.max(sucessor[rootY], sucessor[rootX]);
        } else {
            id[rootY] = rootX;
            sz[rootX] += sz[rootY];
            sucessor[rootX] = Math.max(sucessor[rootX], sucessor[rootY]);
        }
    }

    public void remove(int x) {
        if (x < N - 1) {
            union(x, x + 1);
        }
    }

    public int findSuccessor(int x) {
        int rootX = root(x);
        return sucessor[rootX];
    }
}

