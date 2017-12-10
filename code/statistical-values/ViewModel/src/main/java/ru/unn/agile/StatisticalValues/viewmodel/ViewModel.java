package ru.unn.agile.StatisticalValues.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewModel {
    public ViewModel() {
        statistic.set("Statistic");

        order.set("0");
        orderVisibility.set(false);

        isBiased.set(false);
        isBiasedVisibility.set(false);

        values.set("0.0, 0.0");
        valuesVisibility.set(false);

        calculateVisibility.set(false);

        result.set("");
        resultVisibility.set(false);

        status.set(Status.WAIT.toString());

        statistic.addListener(new PropertyChangeListener<>());
        order.addListener(new PropertyChangeListener<>());
        isBiased.addListener(new PropertyChangeListener<>());
        values.addListener(new PropertyChangeListener<>());
    }

    public StringProperty statisticProperty() {
        return statistic;
    }

    public boolean isSupported(final String statistic) {
        return AVAILABLE_STATISTICS.contains(statistic);
    }

    public final ObservableList<String> getStatistics() {
        return statistics.get();
    }

    public StringProperty orderProperty() {
        return order;
    }

    public BooleanProperty orderVisibilityProperty() {
        return orderVisibility;
    }

    public BooleanProperty isBiasedProperty() {
        return isBiased;
    }

    public BooleanProperty isBiasedVisibilityProperty() {
        return isBiasedVisibility;
    }

    public StringProperty valuesProperty() {
        return values;
    }

    public BooleanProperty valuesVisibilityProperty() {
        return valuesVisibility;
    }

    public BooleanProperty calculateVisibilityProperty() {
        return calculateVisibility;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public BooleanProperty resultVisibilityProperty() {
        return resultVisibility;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void calculate() {
        if (!statusProperty().get().equals(Status.READY.toString())) {
            return;
        }

        Printable value;
        try {
            value = evaluator.compute();
        } catch (IllegalArgumentException exception) {
            status.set("Bad format: " + exception.getMessage());
            return;
        }

        printResult(value.print());
        status.set(Status.SUCCESS.toString());
    }

    private boolean isOrderEmpty() {
        return orderVisibilityProperty().get() && orderProperty().get().isEmpty();
    }

    private boolean isValuesEmpty() {
        return valuesVisibilityProperty().get() && valuesProperty().get().isEmpty();
    }

    private void enableCalculation() {
        orderVisibility.set(evaluator.isOrderUsing());
        isBiasedVisibility.set(evaluator.isBiasUsing());

        calculateVisibility.set(true);

        status.set(Status.READY.toString());
    }

    private void disableCalculation() {
        calculateVisibility.set(false);
        resultVisibility.set(false);
    }

    private void printResult(final String resultText) {
        resultVisibility.set(true);
        result.set(resultText);
    }

    private final StringProperty statistic = new SimpleStringProperty();
    private static final List<String> AVAILABLE_STATISTICS = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("Mean");
            add("Median");
            add("Mode");
            add("Variance");
            add("Std");
            add("Moment");
            add("Central moment");
        }}
    );

    private static List<String> getAvailableStatistics() {
        return AVAILABLE_STATISTICS;
    }

    private final ObjectProperty<ObservableList<String>> statistics =
        new SimpleObjectProperty<>(FXCollections.observableArrayList(getAvailableStatistics()));

    private final StringProperty  order = new SimpleStringProperty();
    private final BooleanProperty orderVisibility = new SimpleBooleanProperty();

    private final BooleanProperty isBiased = new SimpleBooleanProperty();
    private final BooleanProperty isBiasedVisibility = new SimpleBooleanProperty();

    private final StringProperty  values = new SimpleStringProperty();
    private final BooleanProperty valuesVisibility = new SimpleBooleanProperty();

    private final BooleanProperty calculateVisibility = new SimpleBooleanProperty();

    private final StringProperty  result = new SimpleStringProperty();
    private final BooleanProperty resultVisibility = new SimpleBooleanProperty();

    private final StringProperty status = new SimpleStringProperty();

    private Computable evaluator;

    private class PropertyChangeListener<T> implements ChangeListener<T> {
        @Override
        public void changed(final ObservableValue<? extends T> observable,
                            final T oldValue, final T newValue) {
            valuesVisibility.set(true);

            if (isOrderEmpty() || isValuesEmpty()) {
                status.set(Status.WAIT.toString());
                disableCalculation();
                return;
            }

            try {
                evaluator = EvaluatorsFabric.create(ViewModel.this);
            } catch (Exception exception) {
                status.set("Bad format: " + exception.getMessage());
                disableCalculation();
                return;
            }

            enableCalculation();
        }
    }
}

enum Status {
    WAIT("Wait for input"),
    READY("Press 'Calculate'"),
    SUCCESS("Success");

    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
    private final String name;
}
