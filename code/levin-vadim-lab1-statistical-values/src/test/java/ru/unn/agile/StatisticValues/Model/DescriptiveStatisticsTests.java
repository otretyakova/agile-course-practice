package ru.unn.agile.StatisticValues.Model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class DescriptiveStatisticsTests {
    @Test
    public void meanIsCalculatedCorrectly() {
        final double[] inputSample = {1.0, 2.0, 3.0};

        final double mean = DescriptiveStatistics.mean(inputSample);

        assertEquals(2.0, mean);
    }

    @Test(expected = IllegalArgumentException.class)
    public void meanForNullThrowsIllegalArgumentException() {
        final double[] nullSample = null;

        DescriptiveStatistics.mean(nullSample);
    }

    @Test(expected = IllegalArgumentException.class)
    public void meanForEmptySampleThrowsIllegalArgumentException() {
        final double[] emptySample = {};

        DescriptiveStatistics.mean(emptySample);
    }

    @Test
    public void varianceIsOverZero() {
        final double[] inputSample = {1.0, 2.0, 3.0};

        final double variance = DescriptiveStatistics.variance(inputSample);

        assertTrue(variance > 0.0);
    }

    @Test
    public void varianceIsCalculatedCorrectly() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

        final double variance = DescriptiveStatistics.variance(inputSample);

        assertEquals(8.25, variance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void varianceForNullThrowsIllegalArgumentException() {
        final double[] nullSample = null;

        DescriptiveStatistics.variance(nullSample);
    }

    @Test(expected = IllegalArgumentException.class)
    public void varianceForEmptySampleThrowsIllegalArgumentException() {
        final double[] emptySample = {};

        DescriptiveStatistics.variance(emptySample);
    }

    @Test
    public void unbiasedVarianceIsCalculatedCorrectly() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0};

        final double unbiasedVariance = DescriptiveStatistics.variance(inputSample, false);

        assertEquals(2.5, unbiasedVariance);
    }

    @Test
    public void unbiasedVarianceForSampleWithSingleElementIsZero() {
        final double[] sampleWithOneElement = {2.0};

        final double unbiasedVariance = DescriptiveStatistics.variance(sampleWithOneElement, false);

        assertEquals(0.0, unbiasedVariance);
    }

    @Test
    public void medianIsCalculatedCorrectlyForSampleWithOddLength() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};

        final double median = DescriptiveStatistics.median(inputSample);

        assertEquals(5.0, median);
    }

    @Test
    public void medianIsCalculatedCorrectlyForSampleWithEvenLength() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

        final double median = DescriptiveStatistics.median(inputSample);

        assertEquals(4.5, median);
    }

    @Test(expected = IllegalArgumentException.class)
    public void medianForEmptySampleThrowsIllegalArgumentException() {
        final double[] emptySample = {};

        DescriptiveStatistics.median(emptySample);
    }

    @Test(expected = IllegalArgumentException.class)
    public void medianForNullThrowsIllegalArgumentException() {
        final double[] nullSample = null;

        DescriptiveStatistics.median(nullSample);
    }

    @Test
    public void medianForSampleWithSingleElementIsCalculatedCorrectly() {
        final double[] sampleWithOneElement = {3.5};

        final double median = DescriptiveStatistics.median(sampleWithOneElement);

        assertEquals(3.5, median);
    }

    @Test
    public void modeForIntegerSampleIsCalculatedCorrect() {
        final int[] inputSample = {4, 5, 3, 2, 1, 1};

        final int[] modes = DescriptiveStatistics.mode(inputSample);

        assertArrayEquals(new int[]{1}, modes);
    }
}
