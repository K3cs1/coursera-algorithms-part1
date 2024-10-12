package org.coursera.module2.proramming_assignement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PercolationStatsTest {

    private static Stream<Arguments> provideParamsForPercolation() {
        return Stream.of(
                Arguments.of(200, 100),
                Arguments.of(2,10_000),
                Arguments.of(2, 100_000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForPercolation")
    public void testPercolationStats(int n , int trials) {
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.printf("n = %d, trials = %d%n", n, trials);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
        System.out.println("-".repeat(60));
    }


    @Test
    public void test_n200_t100() {
        PercolationStats stats = new PercolationStats(200, 100);
        assertEquals(0.5929934999999997, stats.mean(), 0.05);
        assertEquals(0.00876990421552567, stats.stddev(), 0.05);
        assertEquals(0.5912745987737567, stats.confidenceLo(), 0.05);
        assertEquals(0.5947124012262428, stats.confidenceHi(), 0.05);
    }

    @Test
    public void test_n200_t100_2() {
        PercolationStats stats = new PercolationStats(200, 100);
        assertEquals(0.592877, stats.mean(), 0.05);
        assertEquals(0.009990523717073799, stats.stddev(), 0.05);
        assertEquals(0.5909188573514536, stats.confidenceLo(), 0.05);
        assertEquals(0.5948351426485464, stats.confidenceHi(), 0.05);
    }

    @Test
    public void test_n2_t10000() {
        PercolationStats stats = new PercolationStats(2, 10_000);
        assertEquals(0.666925, stats.mean(), 0.05);
        assertEquals(0.11776536521033558, stats.stddev(), 0.05);
        assertEquals(0.6646167988418774, stats.confidenceLo(), 0.05);
        assertEquals(0.6692332011581226, stats.confidenceHi(), 0.05);
    }

    @Test
    public void test_n2_t100000() {
        PercolationStats stats = new PercolationStats(2, 100_000);
        assertEquals(0.6669475, stats.mean(), 0.05);
        assertEquals(0.11775205263262094, stats.stddev(), 0.05);
        assertEquals(0.666217665216461, stats.confidenceLo(), 0.05);
        assertEquals(0.6676773347835391, stats.confidenceHi(), 0.05);
    }

}