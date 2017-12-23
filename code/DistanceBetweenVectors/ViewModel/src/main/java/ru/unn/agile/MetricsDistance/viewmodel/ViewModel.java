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
import ru.unn.agile.MetricsDistance.Model.MetricsDistance;
import ru.unn.agile.MetricsDistance.Model.MetricsDistance.Metric;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {

    public ViewModel() {
        vec1X.set("");
        vec1Y.set("");
        vec2X.set("");
        vec2Y.set("");
        dim.set("");
        metric.set(Metric.Chebyshev);

        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vec1X, vec1Y, vec2X, vec2Y, dim);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(vec1X);
            add(vec1Y);
            add(vec2X);
            add(vec2Y);
            add(dim);
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

        float floatVec1x = Float.parseFloat(vec1X.get());
        float floatVec1y = Float.parseFloat(vec1Y.get());
        float floatVec2x = Float.parseFloat(vec2X.get());
        float floatVec2y = Float.parseFloat(vec2Y.get());
        final float[] vec1 = {floatVec1x, floatVec1y};
        final float[] vec2 = {floatVec2x, floatVec2y};

        float floatResult;
        if (getMetric() == Metric.Chebyshev) {
            floatResult = MetricsDistance.calculateDistanceChebyshev(vec1, vec2);
        } else {
            int intDim = Integer.parseInt(dim.get());
            floatResult = MetricsDistance.calculateDistanceMinkowski(vec1, vec2, intDim);
        }
        result.set(Float.toString(floatResult));
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty vec1XProperty() {
        return vec1X;
    }

    public StringProperty vec1YProperty() {
        return vec1Y;
    }

    public StringProperty vec2XProperty() {
        return vec2X;
    }

    public StringProperty vec2YProperty() {
        return vec2Y;
    }

    public StringProperty dimProperty() {
        return dim;
    }

    public String getVec1X() {
        return vec1X.get();
    }

    public String getVec1Y() {
        return vec1Y.get();
    }

    public String getVec2X() {
        return vec2X.get();
    }

    public String getVec2Y() {
        return vec2Y.get();
    }

    public String getDim() {
        return dim.get();
    }

    public void setVec1X(final String str) {
        vec1X.set(str);
    }

    public void setVec1Y(final String str) {
        vec1Y.set(str);
    }

    public void setVec2X(final String str) {
        vec2X.set(str);
    }

    public void setVec2Y(final String str) {
        vec2Y.set(str);
    }

    public void setDim(final String str) {
        dim.set(str);
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

    public final boolean isChebyshevMetric() {
        return getMetric() == Metric.Chebyshev;
    }

    public final boolean isMinkowskiMetric() {
        return getMetric() == Metric.Minkowski;
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

    private final StringProperty vec1X = new SimpleStringProperty();
    private final StringProperty vec1Y = new SimpleStringProperty();
    private final StringProperty vec2X = new SimpleStringProperty();
    private final StringProperty vec2Y = new SimpleStringProperty();
    private final StringProperty dim = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();
    private final ObjectProperty<ObservableList<Metric>> metrics =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Metric.values()));
    private final ObjectProperty<Metric> metric = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private boolean isParamsEmpty() {
        return getVec1X().isEmpty() || getVec1Y().isEmpty()
                || getVec2X().isEmpty() || getVec2Y().isEmpty()
                || (isMinkowskiMetric() && getDim().isEmpty());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (isParamsEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!getVec1X().isEmpty()) {
                Float.parseFloat(getVec1X());
            }
            if (!getVec1Y().isEmpty()) {
                Float.parseFloat(getVec1Y());
            }
            if (!getVec2X().isEmpty()) {
                Float.parseFloat(getVec2X());
            }
            if (!getVec2Y().isEmpty()) {
                Float.parseFloat(getVec2Y());
            }
            if (isMinkowskiMetric() && !getDim().isEmpty()) {
                Integer.parseInt(getDim());
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

