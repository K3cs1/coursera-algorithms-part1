package org.coursera.module6.assignements.collinear_points;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] points;
    private LineSegment[] segments;
    private int segmentCount = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            this.points[i] = points[i];
        }

        mergeSort(this.points);

        for (int i = 1; i < this.points.length; i++) {
            if (this.points[i].compareTo(this.points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    // Counts the number of line segments and initializes the segments array
    public int numberOfSegments() {
        int n = points.length;
        segments = new LineSegment[n * n];

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        // Check if p, q, r, and s are collinear
                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);

                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            segments[segmentCount++] = new LineSegment(p, s);
                        }
                    }
                }
            }
        }

        segments = Arrays.copyOf(segments, segmentCount);
        return segmentCount;
    }

    public LineSegment[] segments() {
        if (segments == null) {
            numberOfSegments();
        }
        return Arrays.copyOf(segments, segmentCount);
    }

    // Merge sort implementation
    private void mergeSort(Point[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        Point[] left = Arrays.copyOfRange(array, 0, mid);
        Point[] right = Arrays.copyOfRange(array, mid, array.length);
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    // Merges two halves
    private void merge(Point[] array, Point[] left, Point[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
