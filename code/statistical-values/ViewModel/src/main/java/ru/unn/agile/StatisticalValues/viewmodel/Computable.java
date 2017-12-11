package ru.unn.agile.StatisticalValues.viewmodel;

import ru.unn.agile.StatisticalValues.model.DescriptiveStatistics;

import java.util.Arrays;

interface Computable {
    Printable compute();

    boolean isOrderUsing();
    boolean isBiasUsing();
}

class MeanEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(DescriptiveStatistics.mean(inputSample));
    }

    @Override
    public boolean isOrderUsing() {
        return false;
    }

    @Override
    public boolean isBiasUsing() {
        return false;
    }

    MeanEvaluator(final double[] inputSample) {
        this.inputSample = inputSample;
    }

    private final double[] inputSample;
}

class MedianEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(DescriptiveStatistics.median(inputSample));
    }

    @Override
    public boolean isOrderUsing() {
        return false;
    }

    @Override
    public boolean isBiasUsing() {
        return false;
    }

    MedianEvaluator(final double[] inputSample) {
        this.inputSample = inputSample;
    }

    private final double[] inputSample;
}

class ModeEvaluator implements Computable {
    @Override
    public Printable compute() {
        Integer[] statistic = Arrays.stream(
            DescriptiveStatistics.mode(inputSample)
        ).boxed().toArray(Integer[]::new);
        return new VectorStatistic<>(statistic);
    }

    @Override
    public boolean isOrderUsing() {
        return false;
    }

    @Override
    public boolean isBiasUsing() {
        return false;
    }

    ModeEvaluator(final int[] inputSample) {
        this.inputSample = inputSample;
    }

    private final int[] inputSample;
}

class VarianceEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(DescriptiveStatistics.variance(inputSample, isBiased));
    }

    @Override
    public boolean isOrderUsing() {
        return false;
    }

    @Override
    public boolean isBiasUsing() {
        return true;
    }

    VarianceEvaluator(final double[] inputSample, final boolean isBiased) {
        this.inputSample = inputSample;
        this.isBiased = isBiased;
    }

    private final double[] inputSample;
    private final boolean isBiased;
}

class StdEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(
            DescriptiveStatistics.standardDeviation(inputSample, isBiased)
        );
    }

    @Override
    public boolean isOrderUsing() {
        return false;
    }

    @Override
    public boolean isBiasUsing() {
        return true;
    }

    StdEvaluator(final double[] inputSample, final boolean isBiased) {
        this.inputSample = inputSample;
        this.isBiased = isBiased;
    }

    private final double[] inputSample;
    private final boolean isBiased;
}

class MomentEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(DescriptiveStatistics.moment(inputSample, order));
    }

    @Override
    public boolean isOrderUsing() {
        return true;
    }

    @Override
    public boolean isBiasUsing() {
        return false;
    }

    MomentEvaluator(final double[] inputSample, final int order) {
        this.inputSample = inputSample;
        this.order = order;
    }

    private final double[] inputSample;
    private final int order;
}

class CentralMomentEvaluator implements Computable {
    @Override
    public Printable compute() {
        return new ScalarStatistic<>(DescriptiveStatistics.centralMoment(inputSample, order));
    }

    @Override
    public boolean isOrderUsing() {
        return true;
    }

    @Override
    public boolean isBiasUsing() {
        return false;
    }

    CentralMomentEvaluator(final double[] inputSample, final int order) {
        this.inputSample = inputSample;
        this.order = order;
    }

    private final double[] inputSample;
    private final int order;
}
