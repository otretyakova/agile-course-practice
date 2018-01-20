package ru.unn.agile.mergesort.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.mergesort.Model.MergeSort;

import java.util.*;

public class ViewModel {
    public ViewModel() {
        input.set("");
        output.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldSort = new BooleanBinding() {
            {
                super.bind(input);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        sortDisabled.bind(couldSort.not());

        final InputChangeListener listener = new InputChangeListener();
        input.addListener(listener);
    }

    public void calculate() {
        if (sortDisabled.get()) {
            return;
        }

        String inputStr = input.get();
        inputStr = inputStr.trim();
        String[] words = inputStr.split("[ ]+");
        List<Integer> numbers = new LinkedList<Integer>();
        for (String word : words) {
            numbers.add(Integer.parseInt(word));
        }
        Collection<Integer> sortNumbers = MergeSort.sort(numbers);
        String result = "";
        for (Integer number : sortNumbers) {
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
    public boolean isSortDisabled() {
        return sortDisabled.get();
    }

    public StringProperty outputProperty() {
        return output;
    }
    public String getOutput() {
        return output.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        String inputStr = input.get();
        inputStr = inputStr.trim();
        if (inputStr.isEmpty()) {
            return Status.WAITING;
        }

        String[] words = inputStr.split("[ ]+");
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

    private final StringProperty input = new SimpleStringProperty();

    private final BooleanProperty sortDisabled = new SimpleBooleanProperty();

    private final StringProperty output = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private static final int MAX_NUMBERS_COUNT_IN_INPUT = 10;

    private class InputChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observ,
                            final String oldVal, final String newVal) {
            status.set(getInputStatus().toString());
        }
    }

}

enum Status {
    WAITING("Ожидаю ввода"),
    READY("Ввод корректен"),
    BAD_FORMAT("Ввод некорректен"),
    SUCCESS("Готово!");

    Status(final String name) {
        this.name = name;
    }
    private final String name;
    public String toString() {
        return name;
    }
}
