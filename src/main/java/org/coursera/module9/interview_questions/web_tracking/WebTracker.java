package org.coursera.module9.interview_questions.web_tracking;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class WebTracker {
    private Map<Integer, Map<Integer, Integer>> userVisits;

    public WebTracker() {
        userVisits = new HashMap<>();
    }

    public void visit(int userID, int siteID) {
        userVisits.putIfAbsent(userID, new HashMap<>());
        Map<Integer, Integer> siteVisits = userVisits.get(userID);

        siteVisits.put(siteID, siteVisits.getOrDefault(siteID, 0) + 1);
    }

    public int getVisitCount(int userID, int siteID) {
        return userVisits.getOrDefault(userID, new HashMap<>()).getOrDefault(siteID, 0);
    }

    public static void main(String[] args) {
        WebTracker tracker = new WebTracker();

        tracker.visit(1, 101); // User 1 visits site 101
        tracker.visit(1, 101); // User 1 visits site 101 again
        tracker.visit(2, 101); // User 2 visits site 101
        tracker.visit(1, 102); // User 1 visits site 102

        StdOut.println("User 1 visited site 101: " + tracker.getVisitCount(1, 101) + " times");
        StdOut.println("User 2 visited site 101: " + tracker.getVisitCount(2, 101) + " times");
        StdOut.println("User 1 visited site 102: " + tracker.getVisitCount(1, 102) + " times");
        StdOut.println("User 2 visited site 102: " + tracker.getVisitCount(2, 102) + " times");
    }
}

