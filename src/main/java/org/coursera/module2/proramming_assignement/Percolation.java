package org.coursera.module2.proramming_assignement;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final int topVirtual;
    private final int bottomVirtual;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
        topVirtual = n * n;
        bottomVirtual = n * n + 1;
        openSites = 0;
    }

    // Converts (row, col) to a single index for WeightedQuickUnionUF
    private int coordinateToIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;

            int index = coordinateToIndex(row, col);

            // Connect to adjacent open sites
            if (row == 1) {
                // connect to top virtual site
                uf.union(index, topVirtual);
            }
            if (row == n) {
                // connect to bottom virtual site
                uf.union(index, bottomVirtual);
            }

            // Connect to open neighboring sites (up, down, left, right)
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, coordinateToIndex(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, coordinateToIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(index, coordinateToIndex(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(index, coordinateToIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return uf.find(coordinateToIndex(row, col)) == uf.find(topVirtual);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(topVirtual) == uf.find(bottomVirtual);
    }

    // Validate that (row, col) is within bounds
    private void validateIndices(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
