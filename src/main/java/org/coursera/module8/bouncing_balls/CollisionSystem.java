package org.coursera.module8.bouncing_balls;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class CollisionSystem {

    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particles
    private int N;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
        N = particles.length;
    }

    private void predict(Particle a) {
        if (a == null) {
            return;
        }
        for (int i = 0; i < N; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    private void redraw() {
        StdDraw.clear();
        for (Particle particle : particles) {
            particle.draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        pq.insert(new Event(t + 1.0 / 0.5, null, null));
    }

    public void simulate() {
        pq = new MinPQ<>();
        for (int i = 0; i < N; i++) {
            predict(particles[i]);
        }
        pq.insert(new Event(0, null, null));
        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) {
                continue;
            }
            Particle a = event.getA();
            Particle b = event.getB();

            for (int i = 0; i < N; i++) {
                particles[i].move(event.getTime() - t);
            }
            t = event.getTime();

            if (a != null && b != null) {
                a.bounceOff(b);
            } else if (a != null && b == null) {
                a.bounceOffVerticalWall();
            } else if (a == null && b != null) {
                b.bounceOffHorizontalWall();
            } else if (a == null && b == null) {
                redraw();
            }

            predict(a);
            predict(b);

            redraw();
        }
    }

    public static void main(String[] args) {
        int numParticles = Integer.parseInt(args[0]);
        Particle[] particles = new Particle[numParticles];

        for (int i = 0; i < numParticles; i++) {
            double rx = StdRandom.uniformDouble(0.0, 1.0);
            double ry = StdRandom.uniformDouble(0.0, 1.0);
            double vx = 0.01 * (StdRandom.uniformDouble(0.0, 1.0) - 0.5);
            double vy = 0.01 * (StdRandom.uniformDouble(0.0, 1.0) - 0.5);
            double radius = 0.02;
            double mass = 0.5;
            particles[i] = new Particle(rx, ry, vx, vy, radius, mass, 5);
        }

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate();
    }
}
