package ru.unn.agile.QuadraticEquation.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

import ru.unn.agile.QuadraticEquation.model.QuadraticEquation;

public class ViewModel {

    public ViewModel() {
        a.set("");
        b.set("");
        c.set("");
        answer1.set("");
        answer2.set("");
        status.set(Status.WAITING.toString());
        BooleanBinding canSolve = new BooleanBinding() {
            {
                super.bind(a, b, c);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        solutionDisabled.bind(canSolve.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(a);
                add(b);
                add(c);
            }
        };

        for (StringProperty field : fields) {
            final ChangeStatus listener = new ChangeStatus();
            field.addListener(listener);
            inputChangedListeners.add(listener);
        }
    }

    public void solve() {
        if (solutionDisabled.get()) {
            return;
        }
        if (a.get().equals("")) {
            a.set("0");
        }
        if (b.get().equals("")) {
            b.set("0");
        }
        if (c.get().equals("")) {
            c.set("0");
        }
        QuadraticEquation mainProblem = new QuadraticEquation(a.get(), b.get(), c.get());
        List<String> res;

        res = mainProblem.solveQuadraticEquation();
        if (res.size() == 1) {
            answer1.set(res.get(0));
            answer2.set(res.get(0));
            result.add(answer1);
        }
        if (res.size() == 2) {
            answer1.set(res.get(0));
            answer2.set(res.get(1));
            result.add(answer1);
            result.add(answer2);
        }
        status.set(Status.SUCCESS.toString());
    }

    public StringProperty aProperty() {
        return a;
    }

    public StringProperty bProperty() {
        return b;
    }

    public StringProperty cProperty() {
        return c;
    }

    public StringProperty firstRootProperty() {
        return answer1;
    }

    public StringProperty secondRootProperty() {
        return answer2;
    }

    public List<StringProperty> resultProperty() {
        return result;
    }

    public BooleanProperty solutionDisabledProperty() {
        return solutionDisabled;
    }

    public final boolean isSolutionDisabled() {
        return solutionDisabled.get();
    }

    public final String getFirstRoot() {
        return answer1.get();
    }

    public final String getSecondRoot() {
        return answer2.get();
    }

    public final List<String> getResult() {
        List<String> res = new ArrayList<>();
        if (result.size() == 1) {
            res.add(result.get(0).getValue());
        }
        if (result.size() == 2) {
            res.add(result.get(0).getValue());
            res.add(result.get(1).getValue());
        }
        return res;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (a.get().isEmpty() && b.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            double localA = 0., localB = 0.;
            if (!a.get().isEmpty()) {
                localA = Double.parseDouble(a.get());
            }
            if (!b.get().isEmpty()) {
                localB = Double.parseDouble(b.get());
            }
            if (!c.get().isEmpty()) {
                Double.parseDouble(c.get());
            }
            inputStatus = this.conditionForBadFormat(localA, localB);
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private Status conditionForBadFormat(final double a, final double b) {
        if (Math.abs(a) < EPS && Math.abs(b) < EPS) {
            return Status.BAD_FORMAT;
        }
        double borderTop = QuadraticEquation.getBorderTop();
        double borderBottom = QuadraticEquation.getBorderBottom();
        if ((a < borderBottom) || (a > borderTop)) {
            return Status.BAD_FORMAT;
        }
        if ((b < borderBottom) || (b > borderTop)) {
            return Status.BAD_FORMAT;
        }
        return Status.READY;
    }

    private class ChangeStatus implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldInput, final String newInput) {
            status.set(getInputStatus().toString());
        }
    }

    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();
    private final List<StringProperty> result = new ArrayList<>();

    private final StringProperty answer1 = new SimpleStringProperty();
    private final StringProperty answer2 = new SimpleStringProperty();

    private final BooleanProperty solutionDisabled = new SimpleBooleanProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<ChangeStatus> inputChangedListeners = new ArrayList<>();
    private static final double EPS = 1e-6;
}

enum Status {
    WAITING("Please input data"),
    READY("Press 'Solve' or Enter"),
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


