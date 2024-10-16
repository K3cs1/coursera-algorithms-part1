package org.coursera.module3.threesum_in_quadratic_time;

import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ThreeSumTest {

    public static Stream<Arguments> arrayProvider() {
        return Stream.of(
                Arguments.of(new int[]{30, -40, -20, -10, 40, 0, 10, 5}),
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4}),
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}),
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4, -2, 0, 1, 1, 0})
        );
    }

    @ParameterizedTest
    @MethodSource("arrayProvider")
    void testThreeSum(int[] intArray) {
        int count = ThreeSum.count(intArray);
        StdOut.println("Number of triplet(s): " + count);
        StdOut.println("-----------------------------");
    }

}