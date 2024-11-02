package org.coursera.module8.bouncing_balls;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
    private double rx, ry;        // position
    private double vx, vy;        // velocity
    private final double radius;  // radius

    public Ball() {
        this(StdRandom.uniformDouble(0, 1), StdRandom.uniformDouble(0, 1), StdRandom.uniformDouble(0, 1),
                StdRandom.uniformDouble(0, 1), 0.005);
    }

    /* initialize position and velocity */
    public Ball(double rx, double ry, double vx, double vy, double radius) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void move(double dt) {
        if (rx + vx * dt < radius || rx + vx * dt > 1.0 - radius) {
            vx = -vx;
        }
        if (ry + vy * dt < radius || ry + vy * dt > 1.0 - radius) {
            vy = -vy;
        }
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
