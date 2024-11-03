package org.coursera.module8.programming_assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Solver {
    private final boolean solvable;
    private final int moves;
    private final Board[] solutionPath;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final int priority;
        private final SearchNode previous;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }
    }

    // Find a solution to the initial board using the A* algorithm
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Initial board cannot be null");

        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> twinPq = new MinPQ<>();

        pq.insert(new SearchNode(initial, 0, null));
        twinPq.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode node = null;
        boolean isSolvable = false;

        while (true) {
            node = processNode(pq);
            if (node != null) {
                isSolvable = true;
                break;
            }
            if (processNode(twinPq) != null) break;
        }

        this.solvable = isSolvable;
        if (isSolvable) {
            this.moves = node.moves;
            Board[] tempSolution = new Board[node.moves + 1];
            for (int i = node.moves; i >= 0; i--) {
                tempSolution[i] = node.board;
                node = node.previous;
            }
            this.solutionPath = tempSolution;
        } else {
            this.moves = -1;
            this.solutionPath = null;
        }
    }

    private SearchNode processNode(MinPQ<SearchNode> pq) {
        if (pq.isEmpty()) return null;

        SearchNode node = pq.delMin();
        if (node.board.isGoal()) return node;

        for (Board neighbor : node.board.neighbors()) {
            if (node.previous == null || !neighbor.equals(node.previous.board)) {
                pq.insert(new SearchNode(neighbor, node.moves + 1, node));
            }
        }
        return null;
    }

    // Is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // Min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // Sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solutionPath != null ? Arrays.asList(solutionPath) : null;
    }

    // Test client
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}


