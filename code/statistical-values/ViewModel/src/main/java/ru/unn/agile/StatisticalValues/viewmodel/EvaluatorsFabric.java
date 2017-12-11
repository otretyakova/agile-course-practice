package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.StringProperty;

import java.util.*;

public final class EvaluatorsFabric {

    public static Computable create(final ViewModel viewModel) {
        String statistic = viewModel.statisticProperty().get();
        if (!viewModel.isSupported(statistic)) {
            throw new IllegalArgumentException("Undefined statistic type");
        }

        if ("Mean".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            return new MeanEvaluator(inputSample);
        } else if ("Median".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            return new MedianEvaluator(inputSample);
        } else if ("Mode".equals(statistic)) {
            int[] inputSample = StringPropertyConverter.convertToIntegers(
                viewModel.valuesProperty()
            );
            return new ModeEvaluator(inputSample);
        } else if ("Variance".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            boolean isBiased = viewModel.isBiasedProperty().get();
            return new VarianceEvaluator(inputSample, isBiased);
        } else if ("Std".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            boolean isBiased = viewModel.isBiasedProperty().get();
            return new StdEvaluator(inputSample, isBiased);
        } else if ("Moment".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            int order = StringPropertyConverter.convertToInteger(
                viewModel.orderProperty()
            );
            return new MomentEvaluator(inputSample, order);
        } else if ("Central moment".equals(statistic)) {
            double[] inputSample = StringPropertyConverter.convertToDoubles(
                viewModel.valuesProperty()
            );
            int order = StringPropertyConverter.convertToInteger(viewModel.orderProperty());
            return new CentralMomentEvaluator(inputSample, order);
        }

        throw new IllegalArgumentException("Cannot create statistic evaluator");
    }

    private static class StringPropertyConverter {
        private static double[] convertToDoubles(final StringProperty inputSample) {
            return convertToDoubles(inputSample, ",");
        }

        private static double[] convertToDoubles(final StringProperty inputSample,
                                                 final String sep) {
            String input = inputSample.get();
            String[] values = input.replaceAll("\\s+", "").split(sep);
            ArrayList<Double> sample = new ArrayList<>();
            for (String value : values) {
                sample.add(Double.valueOf(value));
            }

            double[] result = new double[sample.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = sample.get(i);
            }
            return result;
        }

        private static int[] convertToIntegers(final StringProperty inputSample) {
            return convertToIntegers(inputSample, ",");
        }

        private static int[] convertToIntegers(final StringProperty inputSample,
                                               final String sep) {
            String input = inputSample.get();
            String[] values = input.replaceAll("\\s+", "").split(sep);
            ArrayList<Integer> sample = new ArrayList<>();
            for (String value : values) {
                sample.add(Integer.valueOf(value));
            }

            int[] result = new int[sample.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = sample.get(i);
            }
            return result;
        }

        private static int convertToInteger(final StringProperty stringProperty) {
            return Integer.valueOf(stringProperty.get());
        }
    }

    private EvaluatorsFabric() {

    }
}
