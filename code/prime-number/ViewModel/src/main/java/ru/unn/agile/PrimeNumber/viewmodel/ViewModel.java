package ru.unn.agile.PrimeNumber.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import ru.unn.agile.PrimeNumber.Model.PrimeNumber;
import ru.unn.agile.PrimeNumber.Model.PrimeNumber.Methods;

public class ViewModel {
    public ViewModel() {
        rangeFrom.set("");
        rangeTo.set("");
        maxCountPrimes.set("");
        currentAnswer.set("");
        status.set(Status.WAITING.toString());
        method.set(Methods.SIMPLE);
        answersList.set(FXCollections.observableArrayList());
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(rangeFrom, rangeTo, maxCountPrimes);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        // Add listeners to the input text fields
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(rangeFrom);
            add(rangeTo);
            add(maxCountPrimes);
        } };

        for (StringProperty field : fields) {
            final PropertyChangeListener listener = new PropertyChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        if (calculationDisabled.get()) {
            return;
        }
        Integer left = Integer.parseInt(rangeFrom.get());
        Integer right = Integer.parseInt(rangeTo.get());
        PrimeNumber primes = new PrimeNumber(left, right);
        primes.findPrimeNumberFromRange(method.get());
        ArrayList<Integer> primesList = new ArrayList<Integer>(primes.getPrimeList());

        Integer count = Integer.min(Integer.parseInt(maxCountPrimes.get()), primesList.size());
        String ans = "";
        if (!primesList.isEmpty()) {
            ans = "Found " + primesList.size() + " primes in the range from "
                    + left.toString() + " to " + right.toString() + ".\n";
            if (count > 0) {
                ans += "Here are " + count + " of them:\n";
                int j = 0;
                for (int i = 0; i < (count + 1) / 2; i++, j++) {
                    ans += primesList.get(i).toString();
                    if (j != count - 1) {
                        ans += ", ";
                    }
                }
                if (count != primesList.size() && j != count) {
                    ans += "..., ";
                }
                for (int i = 0; i < count / 2; i++, j++) {
                    ans += primesList.get(primesList.size() - count / 2 + i).toString();
                    if (j != count - 1) {
                        ans += ", ";
                    }
                }
                ans += "\n";
            }
        } else {
            ans = "There are no primes in the range from "
                    + left.toString() + " to " + right.toString() + ".\n";
        }
        currentAnswer.set(ans);
        status.set(Status.SUCCESS.toString());
        answersList.add(ans);
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (rangeFrom.get().isEmpty()
            || rangeTo.get().isEmpty()
            || maxCountPrimes.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!maxCountPrimes.get().isEmpty()) {
                Integer maxCount = Integer.parseInt(maxCountPrimes.get());
                if (maxCount < 0) {
                    throw new NumberFormatException("Negative max count");
                }
            }
            if (!rangeFrom.get().isEmpty()) {
                Integer.parseInt(rangeFrom.get());
            }
            if (!rangeTo.get().isEmpty()) {
                Integer.parseInt(rangeTo.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    public void chooseAnswerById(final Integer id) {
        currentAnswer.set(answersList.get(id));
    }

    public String getRangeFrom() {
        return rangeFrom.get();
    }
    public String getRangeTo() {
        return rangeTo.get();
    }
    public String getMaxCountPrimes() {
        return maxCountPrimes.get();
    }
    public String getCurrentAnswer() {
        return currentAnswer.get();
    }
    public final String getStatus() {
        return status.get();
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }
    public final ObservableList<Methods> getMethods() {
        return methods.get();
    }

    public StringProperty rangeFromProperty() {
        return rangeFrom;
    }
    public StringProperty rangeToProperty() {
        return rangeTo;
    }
    public StringProperty maxCountPrimesProperty() {
        return maxCountPrimes;
    }
    public StringProperty currentAnswerProperty() {
        return currentAnswer;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public ObjectProperty<Methods> methodProperty() {
        return method;
    }
    public ListProperty<String> answersListProperty() {
        return answersList;
    }

    private final StringProperty rangeFrom = new SimpleStringProperty();
    private final StringProperty rangeTo = new SimpleStringProperty();
    private final StringProperty maxCountPrimes = new SimpleStringProperty();
    private final StringProperty currentAnswer = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final List<PropertyChangeListener> valueChangedListeners = new ArrayList<>();
    private final ObjectProperty<Methods> method = new SimpleObjectProperty<>();
    private final ObjectProperty<ObservableList<Methods>> methods =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Methods.values()));
    private final ListProperty<String> answersList = new SimpleListProperty<>();

    private class PropertyChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}
