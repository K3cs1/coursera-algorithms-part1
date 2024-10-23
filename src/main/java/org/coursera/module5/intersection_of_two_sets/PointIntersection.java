package org.coursera.module5.intersection_of_two_sets;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PointIntersection {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    public static int countIntersection(Point[] a, Point[] b) {

        Set<Point> set = new HashSet<>(Arrays.asList(a));

        int count = 0;
        for (Point p : b) {
            if (set.contains(p)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Point[] a = { new Point(1, 2), new Point(3, 4), new Point(5, 6) };
        Point[] b = { new Point(3, 4), new Point(7, 8), new Point(1, 2) };

        int result = countIntersection(a, b);
        StdOut.println("Number of common points: " + result);  // Output: 2
    }
}
