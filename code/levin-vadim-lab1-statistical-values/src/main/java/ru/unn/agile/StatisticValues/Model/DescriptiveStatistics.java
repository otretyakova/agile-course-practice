package ru.unn.agile.StatisticValues.Model;


public final class DescriptiveStatistics {

    public static double mean(final double[] inputSample) {
        if (inputSample == null) {
            throw new IllegalArgumentException("Input sample can't be null");
        }
        if (inputSample.length == 0) {
            throw new IllegalArgumentException("Input sample must contain at least one element");
        }
        double sum = 0.0;
        for (double element : inputSample) {
            sum += element;
        }
        return sum / inputSample.length;
    }

    public static double variance(final double[] inputSample) {
        final double sampleMean = mean(inputSample);
        double[] centralizedSampleSquares = new double[inputSample.length];
        double centralizedSampleElement;
        for (int i = 0; i < inputSample.length; i++) {
            centralizedSampleElement = inputSample[i] - sampleMean;
            centralizedSampleSquares[i] = centralizedSampleElement * centralizedSampleElement;
        }
        return mean(centralizedSampleSquares);
    }

    private DescriptiveStatistics() {
    }
}
