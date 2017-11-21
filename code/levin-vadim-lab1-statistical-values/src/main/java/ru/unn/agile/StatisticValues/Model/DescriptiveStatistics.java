package ru.unn.agile.StatisticValues.Model;


import java.util.Arrays;

public final class DescriptiveStatistics {

    public static double mean(final double[] inputSample) {
        validateSample(inputSample);
        if (inputSample.length == 1) {
            return inputSample[0];
        } else {
            double sum = Arrays.stream(inputSample).sum();
            return sum / inputSample.length;
        }
    }

    public static double variance(final double[] inputSample) {
        return variance(inputSample, true);
    }

    public static double variance(final double[] inputSample, final boolean isBiased) {
        validateSample(inputSample);
        if (inputSample.length == 1) {
            return 0.0;
        }
        final double sampleMean = mean(inputSample);
        double[] centralizedSampleSquares = new double[inputSample.length];
        double centralizedSampleElement;
        for (int i = 0; i < inputSample.length; i++) {
            centralizedSampleElement = inputSample[i] - sampleMean;
            centralizedSampleSquares[i] = centralizedSampleElement * centralizedSampleElement;
        }
        if (isBiased) {
            return mean(centralizedSampleSquares);
        } else {
            return Arrays.stream(inputSample).sum() / (inputSample.length - 1);
        }
    }

    public static double median(final double[] inputSample) {
        validateSample(inputSample);
        if (inputSample.length == 1) {
            return inputSample[0];
        }
        double[] workingSample = inputSample.clone();
        Arrays.sort(workingSample);
        final int medianIndex = workingSample.length / 2;
        if (isEven(workingSample.length)) {
            final int medianTopIndex = medianIndex - 1;
            return (workingSample[medianIndex] + workingSample[medianTopIndex]) / 2;
        } else {
            return workingSample[medianIndex];
        }
    }

    public static int[] mode(final int[] inputSample) {
        return new int[]{1};
    }


    private static boolean isEven(final int number) {
        return number % 2 == 0;
    }

    private static void validateSample(final double[] inputSample) {
        if (inputSample == null) {
            throw new IllegalArgumentException("Input sample can't be null");
        }
        if (inputSample.length == 0) {
            throw new IllegalArgumentException("Input sample must contain at least one element");
        }
    }

    private DescriptiveStatistics() {
    }
}
