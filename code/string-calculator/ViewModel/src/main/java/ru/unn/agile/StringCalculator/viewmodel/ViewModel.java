package ru.unn.agile.StringCalculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ru.unn.agile.StringCalculator.model.StringCalculator;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    public ViewModel() {
        initialize();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        initialize();
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public void calculate() {
        if (isCalculationDisabled()) {
            return;
        }

        result.set(Integer.toString(StringCalculator.add(getInputString())));
        status.set(Status.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
        message.append("Input string = ").append(getInputString());
        logger.log(message.toString());
        updateLogs();
    }

    public void onFocusChanged(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }

        for (ValueModifyListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input string = ").append(getInputString());
                logger.log(message.toString());
                updateLogs();
                listener.cache();
                break;
            }
        }
    }

    public void setInputString(final String inputString) {
        this.inputString.set(inputString);
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

    public StringProperty logsProperty() {
        return logs;
    }

    public void setLogs(final String record) {
        this.logs.set(record);
    }

    public final String getLogs() {
        return logs.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    public final List<String> getLog() {
        return logger.getLog();
    }

    private final StringProperty inputString = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty logs = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private List<ValueModifyListener> valueChangedListeners;
    private ILogger logger;

    private void initialize() {
        inputString.set("");
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

        final List<StringProperty> values = new ArrayList<StringProperty>() { {
            add(inputString);
        } };

        valueChangedListeners = new ArrayList<>();
        for (StringProperty value : values) {
            final ValueModifyListener listener = new ValueModifyListener();
            value.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (getInputString().isEmpty()) {
            inputStatus = Status.WAITING;
        } else if (StringCalculator.isBadFormat(getInputString())) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String("");

        for (String log : fullLog) {
            record += log + "\n";
        }
        setLogs(record);
    }

    private class ValueModifyListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            if (oldValue.equals(newValue)) {
                return;
            }
            status.set(getInputStatus().toString());
            currentValue = newValue;
        }

        public boolean isChanged() {
            return !previousValue.equals(currentValue);
        }

        public void cache() {
            previousValue = currentValue;
        }

        private String previousValue = new String("");
        private String currentValue = new String("");
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

final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String EDITING_FINISHED = "Update input. ";

    private LogMessages() { }
}
