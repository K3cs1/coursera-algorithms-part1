package org.coursera.module11.programming_assignement;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private static class Node {
        private final Point2D point;  // the point
        private final RectHV rect;   // the axis-aligned rectangle corresponding to this node
        private Node left;           // the left/bottom subtree
        private Node right;          // the right/top subtree

        public Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }
    }

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Null point");
        }
        root = insert(root, p, 0, 0, 1, 1, true);
    }

    private Node insert(Node node, Point2D p, double xmin, double ymin, double xmax, double ymax, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }
        if (node.point.equals(p)) {
            return node;
        }

        if (vertical) {
            if (p.x() < node.point.x()) {
                node.left = insert(node.left, p, xmin, ymin, node.point.x(), ymax, !vertical);
            } else {
                node.right = insert(node.right, p, node.point.x(), ymin, xmax, ymax, !vertical);
            }
        } else {
            if (p.y() < node.point.y()) {
                node.left = insert(node.left, p, xmin, ymin, xmax, node.point.y(), !vertical);
            } else {
                node.right = insert(node.right, p, xmin, node.point.y(), xmax, ymax, !vertical);
            }
        }
        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Null point");
        }
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            return false;
        }
        if (node.point.equals(p)) {
            return true;
        }

        if (vertical) {
            if (p.x() < node.point.x()) {
                return contains(node.left, p, !vertical);
            } else {
                return contains(node.right, p, !vertical);
            }
        } else {
            if (p.y() < node.point.y()) {
                return contains(node.left, p, !vertical);
            } else {
                return contains(node.right, p, !vertical);
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, true);
    }

    private void draw(Node node, boolean vertical) {
        if (node == null) return;

        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        // Draw the splitting line
        StdDraw.setPenRadius();
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
        }

        draw(node.left, !vertical);
        draw(node.right, !vertical);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Null rectangle");
        }
        List<Point2D> result = new ArrayList<>();
        range(root, rect, result);
        return result;
    }

    private void range(Node node, RectHV rect, List<Point2D> result) {
        if (node == null) return;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.point)) {
                result.add(node.point);
            }
            range(node.left, rect, result);
            range(node.right, rect, result);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Null point");
        }
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.point, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D closest, boolean vertical) {
        if (node == null) {
            return closest;
        }

        if (p.distanceSquaredTo(node.point) < p.distanceSquaredTo(closest)) {
            closest = node.point;
        }

        Node first = (vertical && p.x() < node.point.x()) || (!vertical && p.y() < node.point.y()) ? node.left : node.right;
        Node second = first == node.left ? node.right : node.left;

        closest = nearest(first, p, closest, !vertical);
        if (second != null && second.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest)) {
            closest = nearest(second, p, closest, !vertical);
        }

        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // Add unit testing here
    }
}

