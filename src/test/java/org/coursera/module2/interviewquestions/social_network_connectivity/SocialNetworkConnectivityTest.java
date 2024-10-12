package org.coursera.module2.interviewquestions.social_network_connectivity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkConnectivityTest {

    @Test
    void testSocialNetworkConnectivity() {
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(10);
        String[][] logs = {
                {"2024-10-01 00:01:00", "0", "1"},
                {"2024-10-01 00:02:00", "1", "2"},
                {"2024-10-01 00:03:00", "2", "3"},
                {"2024-10-01 00:04:00", "3", "4"},
                {"2024-10-01 00:05:00", "4", "5"},
                {"2024-10-01 00:06:00", "5", "6"},
                {"2024-10-01 00:07:00", "6", "7"},
                {"2024-10-01 00:08:00", "7", "8"},
                {"2024-10-01 00:09:00", "8", "9"}
        };

        Arrays.stream(logs)
                .forEach(log -> {
                    String timestamp = log[0];
                    int member1 = Integer.parseInt(log[1]);
                    int member2 = Integer.parseInt(log[2]);
                    snc.union(member1, member2);
                    if (snc.countComponents() == 1) {
                        assertEquals("2024-10-01 00:09:00", timestamp, "All members are connected timestamp");
                    }
                });
    }

}