package org.coursera.module8.bouncing_balls;

import edu.princeton.cs.algs4.StdDraw;

public class Particle {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private int count; // number of collisions

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, int count) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        this.count = count;
    }

    public void move(double dt) { // move the particle in time dt
        rx += vx * dt;
        ry += vy * dt; // update position
        double d = Math.sqrt(vx * vx + vy * vy); // update velocity
        vx -= dt * rx * d;
        vy -= dt * ry * d;
        count++;
    }

    public void draw() { // draw the particle
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that) {
        if (this == that) {
            return Double.POSITIVE_INFINITY;
        }
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) {
            return Double.POSITIVE_INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() { // time to hit vertical wall
        if (vx > 0) {
            return (1.0 - rx - radius) / vx;
        } else if (vx < 0) {
            return (radius - rx) / vx;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public double timeToHitHorizontalWall() { // time to hit horizontal wall
        if (vy > 0) {
            return (1.0 - ry - radius) / vy;
        } else if (vy < 0) {
            return (radius - ry) / vy;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() { // bounce off vertical wall
        this.vx = -this.vx;
        this.count++;
    }

    public void bounceOffHorizontalWall() { // bounce off horizontal wall
        this.vy = -this.vy;
        this.count++;
    }

    public int getCount() {
        return count;
    }
}
