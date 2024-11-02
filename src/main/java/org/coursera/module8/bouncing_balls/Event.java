package org.coursera.module8.bouncing_balls;

public class Event implements Comparable<Event> {
    private double time; // time of event
    private Particle a, b; // particles involved in event
    private int countA, countB; // collision counts for a and b

    public Event(double t, Particle a, Particle b) {
        this.time = t;
        this.a = a;
        this.b = b;
    }

    public int compareTo(Event that) {
        double diff = this.time - that.time;
        if (diff == 0) {
            return 0;
        } else if (diff < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean isValid() {
        if (a != null && b != null) {
            countA = a.getCount();
            countB = b.getCount();
            return countA > 0 && countB > 0;
        }
        return false;
    }

    public double getTime() {
        return time;
    }

    public Particle getA() {
        return a;
    }

    public Particle getB() {
        return b;
    }
}
