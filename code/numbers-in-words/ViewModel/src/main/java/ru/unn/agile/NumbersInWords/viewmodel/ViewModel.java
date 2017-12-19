package ru.unn.agile.NumbersInWords.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import ru.unn.agile.NumbersInWords.model.NumbersInWordsConverter;

public class ViewModel {

    public ViewModel() {
        number.set("");
        resultWord.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldTranslate = new BooleanBinding() {
            {
                super.bind(number);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        translateButtonDisabled.bind(couldTranslate.not());

        number.addListener(inputNumberChangedListener);
    }

    public StringProperty numberProperty() {
        return number;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty resultWordProperty() {
        return resultWord;
    }

    public BooleanProperty translateButtonDisabledProperty() {
        return translateButtonDisabled;
    }

    public final void setNumber(final String inputNumber) {
        number.set(inputNumber);
    }

    public final String getInputNumber() {
        return number.get();
    }

    public final String getResultWord() {
        return resultWord.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public final boolean isTranslateButtonDisabled() {
        return translateButtonDisabled.get();
    }

    public void translate() {
        if (isTranslateButtonDisabled()) {
            return;
        }
        resultWord.set(NumbersInWordsConverter.convert(getInputNumber()));
        status.set(Status.SUCCESS.toString());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;

        if ((getInputNumber().isEmpty()) || (getInputNumber().equals("-"))) {
            inputStatus = Status.WAITING;
        } else {
            try {
                double inputNumber = Double.parseDouble(getInputNumber());
                double borderTop = NumbersInWordsConverter.getBorderTop();
                double borderBottom = NumbersInWordsConverter.getBorderBottom();
                if ((inputNumber <= borderBottom) || (inputNumber >= borderTop)) {
                    inputStatus = Status.BAD_FORMAT;
                }
            } catch (NumberFormatException nfe) {
                inputStatus = Status.BAD_FORMAT;
            }
        }
        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
            resultWord.set("");
        }
    }

    private final StringProperty number = new SimpleStringProperty();
    private final StringProperty resultWord = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty translateButtonDisabled = new SimpleBooleanProperty();
    private final ValueChangeListener inputNumberChangedListener = new ValueChangeListener();
}

enum Status {
    WAITING("Please enter a number"),
    READY("Press 'Translate'"),
    BAD_FORMAT("Error in input number"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
