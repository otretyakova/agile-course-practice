package ru.unn.agile.StatisticValues.Model;


public final class DescriptiveStatistics {

    public static double mean(final double[] inputSample) {
        validateSample(inputSample);
        if (inputSample.length == 1) {
            return inputSample[0];
        } else {
            double sum = sumOfArray(inputSample);
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
            return sumOfArray(inputSample) / (inputSample.length - 1);
        }
    }

    private static void validateSample(final double[] inputSample) {
        if (inputSample == null) {
            throw new IllegalArgumentException("Input sample can't be null");
        }
        if (inputSample.length == 0) {
            throw new IllegalArgumentException("Input sample must contain at least one element");
        }
    }

    private static double sumOfArray(final double[] inputArray) {
        double sum = 0.0;
        for (double element : inputArray) {
            sum += element;
        }
        return sum;
    }

    private DescriptiveStatistics() {
    }
}
