package ru.unn.agile.Vectors3d.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Vectors3d.Model.Vector3d;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VectorViewModel {

    public VectorViewModel() {
        x.set("0.0");
        y.set("0.0");
        z.set("0.0");

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(x);
            add(y);
            add(z);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty getXProperty() {
        return x;
    }

    public StringProperty getYProperty() {
        return y;
    }

    public StringProperty getZProperty() {
        return z;
    }

    public BooleanProperty normalizeDisabledProperty() {
        return isNormalizeDisabled;
    }

    public final boolean isNormalizeDisabled() {
        return isNormalizeDisabled.get();
    }

    public void setX(final String x) {
        this.x.set(x);
    }

    public void setY(final String y) {
        this.y.set(y);
    }

    public void setZ(final String z) {
        this.z.set(z);
    }

    public void setCoordinates(final String x, final String y, final String z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void normalize() {
        final int maxNumberOfDecimals = 16;

        Vector3d normalizedVector = getVector().normalize();
        BigDecimal xValue = BigDecimal.valueOf(normalizedVector.getX())
                .setScale(maxNumberOfDecimals, BigDecimal.ROUND_HALF_UP);
        BigDecimal yValue = BigDecimal.valueOf(normalizedVector.getY())
                .setScale(maxNumberOfDecimals, BigDecimal.ROUND_HALF_UP);
        BigDecimal zValue = BigDecimal.valueOf(normalizedVector.getZ())
                .setScale(maxNumberOfDecimals, BigDecimal.ROUND_HALF_UP);

        x.set(xValue.toString());
        y.set(yValue.toString());
        z.set(zValue.toString());
    }

    public Vector3d getVector() {
        return new Vector3d(Double.parseDouble(x.get()),
                Double.parseDouble(y.get()),
                Double.parseDouble(z.get()));
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            try {
                Double.parseDouble(x.get());
                Double.parseDouble(y.get());
                Double.parseDouble(z.get());
                isNormalizeDisabled.set(false);
            } catch (Exception e) {
                isNormalizeDisabled.set(true);
            }
        }
    }

    private final StringProperty x = new SimpleStringProperty();
    private final StringProperty y = new SimpleStringProperty();
    private final StringProperty z = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private final BooleanProperty isNormalizeDisabled = new SimpleBooleanProperty();
}
