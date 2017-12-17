package ru.unn.agile.MetricsDistance.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//import ru.unn.agile.MetricsDistance.Model.MetricsDistance;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty x1 = new SimpleStringProperty();
    private final StringProperty y1 = new SimpleStringProperty();
    private final StringProperty x2 = new SimpleStringProperty();
    private final StringProperty y2 = new SimpleStringProperty();
    private final StringProperty operation = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    // FXML needs default c-tor for binding
    public ViewModel() {
        x1.set("");
        y1.set("");
        x2.set("");
        y2.set("");
        operation.set("");
        result.set("Chebyshev");

        // Add listeners to the input text fields
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
    public StringProperty operationProperty() {
        return operation;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
//            status.set(getInputStatus().toString());
        }
    }
//}
}
