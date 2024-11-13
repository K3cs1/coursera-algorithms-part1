package org.coursera.module10.interview_questions.document_search;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class DocumentSearch {

    public static int[] findShortestInterval(String[] document, String[] query) {
        Map<String, Integer> lastPos = new HashMap<>();
        for (String word : query) {
            lastPos.put(word, -1);
        }

        int minIntervalLength = Integer.MAX_VALUE;
        int minStart = -1, minEnd = -1;

        for (int end = 0; end < document.length; end++) {
            String word = document[end];

            if (lastPos.containsKey(word)) {
                int queryIndex = -1;

                for (int i = 0; i < query.length; i++) {
                    if (query[i].equals(word)) {
                        queryIndex = i;
                        break;
                    }
                }

                if (queryIndex != -1) {
                    lastPos.put(query[queryIndex], end);

                    boolean allWordsFound = true;
                    int firstPos = Integer.MAX_VALUE;
                    int lastPosInInterval = Integer.MIN_VALUE;

                    for (int i = 0; i < query.length; i++) {
                        int pos = lastPos.get(query[i]);
                        if (pos == -1) {
                            allWordsFound = false;
                            break;
                        }
                        firstPos = Math.min(firstPos, pos);
                        lastPosInInterval = Math.max(lastPosInInterval, pos);
                    }

                    if (allWordsFound) {
                        int intervalLength = lastPosInInterval - firstPos + 1;
                        if (intervalLength < minIntervalLength) {
                            minIntervalLength = intervalLength;
                            minStart = firstPos;
                            minEnd = lastPosInInterval;
                        }
                    }
                }
            }
        }

        return minStart == -1 ? new int[]{} : new int[]{minStart, minEnd};
    }

    public static void main(String[] args) {
        String[] document = {"this", "is", "a", "test", "document", "and", "this", "is", "another", "test"};
        String[] query = {"this", "is", "test"};

        int[] interval = findShortestInterval(document, query);
        if (interval.length == 0) {
            StdOut.println("No valid interval found.");
        } else {
            StdOut.println("Shortest interval: [" + interval[0] + ", " + interval[1] + "]");
        }
    }

}

