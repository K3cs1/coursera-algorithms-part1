package org.coursera.module6.assignements.collinear_points;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private Point[] points;
    private LineSegment[] segments;
    private int segmentCount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        int n = points.length;
        this.points = new Point[n];
        System.arraycopy(points, 0, this.points, 0, n);

        mergeSort(this.points, 0, n - 1);

        for (int i = 0; i < n; i++) {
            if (this.points[i] == null) {
                throw new IllegalArgumentException();
            }
            if (i > 0 && this.points[i].compareTo(this.points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        segments = new LineSegment[(n * (n - 1) / 2)];
    }

    public int numberOfSegments() {
        int n = points.length;
        segmentCount = 0; // Reset segment count

        for (int i = 0; i < n; i++) {
            Point origin = points[i];
            Point[] otherPoints = points.clone();
            mergeSortBySlope(otherPoints, 0, n - 1, origin);

            int count = 1;
            for (int j = 1; j < n; j++) {
                double slope = origin.slopeTo(otherPoints[j]);
                while (j < n - 1 && origin.slopeTo(otherPoints[j + 1]) == slope) {
                    count++;
                    j++;
                }

                if (count >= 3) {
                    Point[] collinearPoints = new Point[count + 1];
                    collinearPoints[0] = origin;
                    for (int k = 0; k < count; k++) {
                        collinearPoints[k + 1] = otherPoints[j - count + 1 + k];
                    }
                    mergeSort(collinearPoints, 0, count);
                    if (origin.compareTo(collinearPoints[0]) == 0) {
                        segments[segmentCount++] = new LineSegment(collinearPoints[0], collinearPoints[count]);
                    }
                }
                count = 1;
            }
        }
        return segmentCount;
    }

    public LineSegment[] segments() {
        numberOfSegments();
        return Arrays.copyOf(segments, segmentCount);
    }

    private void mergeSort(Point[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(Point[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Point[] leftArray = new Point[n1];
        Point[] rightArray = new Point[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < n1) array[k++] = leftArray[i++];
        while (j < n2) array[k++] = rightArray[j++];
    }

    private void mergeSortBySlope(Point[] array, int left, int right, Point origin) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortBySlope(array, left, mid, origin);
            mergeSortBySlope(array, mid + 1, right, origin);
            mergeBySlope(array, left, mid, right, origin);
        }
    }

    private void mergeBySlope(Point[] array, int left, int mid, int right, Point origin) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Point[] leftArray = new Point[n1];
        Point[] rightArray = new Point[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (origin.slopeTo(leftArray[i]) <= origin.slopeTo(rightArray[j])) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < n1) {
            array[k++] = leftArray[i++];
        }
        while (j < n2) {
            array[k++] = rightArray[j++];
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
