package ru.unn.agile.MetricsDistance.viewmodel;

import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.binding.BooleanBinding;
import ru.unn.agile.MetricsDistance.Model.MetricsDistance.Metric;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    public ViewModel() {
        x1.set("");
        y1.set("");
        x2.set("");
        y2.set("");
        metric.set(Metric.Chebyshev);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(x1, y1, x2, y2);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(x1);
            add(y1);
            add(x2);
            add(y2);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (isCalculationDisabled()) {
            return;
        }

        float float_vec1x = Float.parseFloat(x1.get());
        float float_vec1y = Float.parseFloat(y1.get());
        float float_vec2x = Float.parseFloat(x2.get());
        float float_vec2y = Float.parseFloat(y2.get());
        final float[] vec1 = {float_vec1x, float_vec1y};
        final float[] vec2 = {float_vec2x, float_vec2y};

        float floatResult = getMetric().apply(vec1, vec2);
        result.set(Float.toString(floatResult));
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty x1Property() {
        return x1;
    }

    public StringProperty y1Property() {
        return y1;
    }

    public StringProperty x2Property() {
        return x2;
    }

    public StringProperty y2Property() {
        return y2;
    }

    public String getX1() {
        return x1.get();
    }

    public String getY1() {
        return y1.get();
    }

    public String getX2() {
        return x2.get();
    }

    public String getY2() {
        return y2.get();
    }

    public void setX1(final String str) {
        x1.set(str);
    }

    public void setY1(final String str) {
        y1.set(str);
    }

    public void setX2(final String str) {
        x2.set(str);
    }

    public void setY2(final String str) {
        y2.set(str);
    }

    public ObjectProperty<Metric> metricProperty() {
        return metric;
    }

    public Metric getMetric() {
        return metric.get();
    }

    public void setMetric(final Metric met) {
        metric.set(met);
    }

    public final ObservableList<Metric> getMetrics() {
        return metrics.get();
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private final StringProperty x1 = new SimpleStringProperty();
    private final StringProperty y1 = new SimpleStringProperty();
    private final StringProperty x2 = new SimpleStringProperty();
    private final StringProperty y2 = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final ObjectProperty<ObservableList<Metric>> metrics =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Metric.values()));
    private final ObjectProperty<Metric> metric = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (getX1().isEmpty() || getY1().isEmpty()
                || getX2().isEmpty() || getY2().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!getX1().isEmpty()) {
                Float.parseFloat(getX1());
            }
            if (!getY1().isEmpty()) {
                Float.parseFloat(getY1());
            }
            if (!getX2().isEmpty()) {
                Float.parseFloat(getX2());
            }
            if (!getY2().isEmpty()) {
                Float.parseFloat(getY2());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

