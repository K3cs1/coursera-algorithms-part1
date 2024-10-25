package org.coursera.module6.assignements.collinear_points;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        if (x < 0 || y > 32767 || y < 0 || x > 32767) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
            return 1;
        } else {
            return 0;
        }
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if(this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if(this.y == that.y) {
            return +0.0;
        } else if(this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            return Double.compare(slope1, slope2);
        }
    }

}
