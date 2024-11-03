package org.coursera.module8.programming_assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private final int[][] tiles;
    private final int n;

    // Create a board from an n-by-n array of tiles
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, n);
        }
    }

    // Board dimension n
    public int dimension() {
        return n;
    }

    // Number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != (i * n + j + 1)) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // Sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = tiles[i][j];
                if (value != 0) {
                    int targetRow = (value - 1) / n;
                    int targetCol = (value - 1) % n;
                    manhattan += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // Does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (!(y instanceof Board)) return false;
        Board that = (Board) y;
        return Arrays.deepEquals(this.tiles, that.tiles);
    }

    // All neighboring boards
    public Iterable<Board> neighbors() {
        Board[] neighbors = new Board[4];
        int blankRow = 0, blankCol = 0;
        int count = 0;

        // Find the blank square
        outerLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break outerLoop;
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int newRow = blankRow + direction[0];
            int newCol = blankCol + direction[1];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                int[][] newTiles = copyTiles();
                newTiles[blankRow][blankCol] = newTiles[newRow][newCol];
                newTiles[newRow][newCol] = 0;
                neighbors[count++] = new Board(newTiles);
            }
        }
        return Arrays.asList(Arrays.copyOf(neighbors, count));
    }

    // A board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] newTiles = copyTiles();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (newTiles[i][j] != 0 && newTiles[i][j + 1] != 0) {
                    int temp = newTiles[i][j];
                    newTiles[i][j] = newTiles[i][j + 1];
                    newTiles[i][j + 1] = temp;
                    return new Board(newTiles);
                }
            }
        }
        return null;
    }

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(tiles[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int[] row : tiles) {
            for (int value : row) {
                sb.append(String.format("%2d ", value));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Unit testing
    public static void main(String[] args) {
        int[][] tiles = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board board = new Board(tiles);
        StdOut.println("Board:");
        StdOut.println(board);
        StdOut.println("Hamming: " + board.hamming());
        StdOut.println("Manhattan: " + board.manhattan());
        StdOut.println("Is Goal: " + board.isGoal());
    }
}


