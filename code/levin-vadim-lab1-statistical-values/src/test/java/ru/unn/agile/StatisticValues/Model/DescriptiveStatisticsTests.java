package ru.unn.agile.StatisticValues.Model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class DescriptiveStatisticsTests {
    @Test
    public void meanForArrayIsCalculatedCorrectly() {
        final double[] inputSample = {1.0, 2.0, 3.0};

        final double mean = DescriptiveStatistics.mean(inputSample);

        assertEquals(2.0, mean);
    }

    @Test(expected = IllegalArgumentException.class)
    public void meanForNullThrowsIllegalArgumentException() {
        final double[] nullArray = null;

        DescriptiveStatistics.mean(nullArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void meanForEmptyArrayThrowsIllegalArgumentException() {
        final double[] emptyArray = {};

        DescriptiveStatistics.mean(emptyArray);
    }

    @Test
    public void varianceIsOverZero() {
        final double[] inputSample = {1.0, 2.0, 3.0};

        final double variance = DescriptiveStatistics.variance(inputSample);

        assertTrue(variance > 0.0);
    }

    @Test
    public void varianceForArrayIsCalculatedCorrectly() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

        final double variance = DescriptiveStatistics.variance(inputSample);

        assertEquals(8.25, variance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void varianceForNullThrowsIllegalArgumentException() {
        final double[] nullArray = null;

        DescriptiveStatistics.variance(nullArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void varianceForEmptyArrayThrowsIllegalArgumentException() {
        final double[] emptyArray = {};

        DescriptiveStatistics.variance(emptyArray);
    }

    @Test
    public void unbiasedVarianceIsCalculatedCorrectly() {
        final double[] inputSample = {0.0, 1.0, 2.0, 3.0, 4.0};

        final double unbiasedVariance = DescriptiveStatistics.variance(inputSample, false);

        assertEquals(2.5, unbiasedVariance);
    }

    @Test
    public void unbiasedVarianceForArrayWithSingleElementIsZero() {
        final double[] sampleWithOneElement = {2.0};

        final double unbiasedVariance = DescriptiveStatistics.variance(sampleWithOneElement, false);

        assertEquals(0.0, unbiasedVariance);
    }

}
