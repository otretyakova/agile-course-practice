package ru.unn.agile.StringCalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

import ru.unn.agile.StringCalculator.Model.StringCalculator;

public class ViewModel {

    public ViewModel() {
        inputString.set("");
        inputString.addListener(inputStringChangedListener);

        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(inputString);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }

        result.set(Integer.toString(StringCalculator.add(getInputString())));
        status.set(Status.SUCCESS.toString());
    }

    public final String getInputString() {
        return inputString.get();
    }

    public StringProperty inputStringProperty() {
        return inputString;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (getInputString().isEmpty()) {
            inputStatus = Status.WAITING;
        } else if (StringCalculator.isIncorrectData(getInputString())) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private final StringProperty inputString = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final ValueChangeListener inputStringChangedListener = new ValueChangeListener();

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

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    private final String name;
}
