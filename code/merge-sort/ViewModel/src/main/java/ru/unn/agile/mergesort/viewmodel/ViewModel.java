package ru.unn.agile.mergesort.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//import ru.unn.agile.mergesort.model.MergeSort;

import java.util.ArrayList;
import java.util.Collections;

public class ViewModel {
    private final StringProperty input = new SimpleStringProperty();

    private final BooleanProperty sortDisabled = new SimpleBooleanProperty();

    private final StringProperty output = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private static final int MAX_NUMBERS_COUNT_IN_INPUT = 10;

    public ViewModel() {
        input.set("");
        output.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(input);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        sortDisabled.bind(couldCalculate.not());

        final ValueChangeListener listener = new ValueChangeListener();
        input.addListener(listener);
    }

    public void calculate() {
        if (sortDisabled.get()) {
            return;
        }

        String[] words = input.get().split(" ");
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String word : words) {
            numbers.add(Integer.parseInt(word));
        }
        Collections.sort(numbers);
        String result = "";
        for (Integer number : numbers) {
            result += number.toString() + " ";
        }
        output.set(result.substring(0, result.length() - 1));

        status.set(Status.SUCCESS.toString());
    }

    public StringProperty inputProperty() {
        return input;
    }

    public BooleanProperty sortDisabledProperty() {
        return sortDisabled;
    }

    public StringProperty outputProperty() {
        return output;
    }
    public StringProperty statusProperty() {
        return status;
    }

    private Status getInputStatus() {
        String inputStr = input.get();
        if (inputStr.isEmpty()) {
            return Status.WAITING;
        }

        String[] words = inputStr.split(" ");
        if (words.length > MAX_NUMBERS_COUNT_IN_INPUT) {
            return Status.BAD_FORMAT;
        }
        for (String word : words) {
            try {
                Integer.parseInt(word);
            } catch (NumberFormatException nfe) {
                return Status.BAD_FORMAT;
            }
        }

        return Status.READY;
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
    READY("Press 'Sort'"),
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
