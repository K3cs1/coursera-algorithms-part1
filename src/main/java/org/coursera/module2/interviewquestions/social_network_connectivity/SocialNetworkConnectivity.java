package org.coursera.module2.interviewquestions.social_network_connectivity;

/*
Social network connectivity. Given a social network containing
n members and a log file containing

m timestamps at which times pairs of members formed friendships,
design an algorithm to determine the earliest time at which all members are connected
(i.e., every member is a friend of a friend of a friend ... of a friend).
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
The running time of your algorithm should be mlogn or better and use extra space proportional to n.
*/
public class SocialNetworkConnectivity {
    private int[] id;
    private int[] sz;
    private int components;

    public SocialNetworkConnectivity(int N) {
        id = new int[N];
        sz = new int[N];
        components = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int x) {
        while (x != id[x]) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (connected(p, q)) {
            return;
        }

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
        components--;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int countComponents() {
        return components;
    }
}

