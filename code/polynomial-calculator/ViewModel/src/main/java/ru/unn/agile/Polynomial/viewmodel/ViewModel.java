package ru.unn.agile.Polynomial.viewmodel;

import ru.unn.agile.Polynomial.model.Parser;
import ru.unn.agile.Polynomial.model.Polynomial;

public class ViewModel {
    public ViewModel() {
        isCalculateButtonEnabled = false;
        firstPolynomial = "";
        secondPolynomial = "";
        operation = Operation.ADD;
        result = "";
        status = Status.WAITING;
    }

    public boolean isButtonCalculateEnabled() {
        return isCalculateButtonEnabled;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;
    }

    public String getFirstPolynomial() {
        return firstPolynomial;
    }

    public void setFirstPolynomial(final String txt) {
        firstPolynomial = txt;
    }

    public String getSecondPolynomial() {
        return secondPolynomial;
    }

    public void setSecondPolynomial(final String txt) {
        secondPolynomial = txt;
    }

    public void processKeyInTextField() {
        parseInput();
    }

    public void calculate() {
        if (parseInput()) {
            Polynomial firstOperand = Parser.getPolynomial(firstPolynomial);
            Polynomial secondOperand = Parser.getPolynomial(secondPolynomial);
            Polynomial resultOfOperation;
            switch (operation) {
                case ADD:
                    resultOfOperation = firstOperand.add(secondOperand);
                    break;
                case SUB:
                    resultOfOperation = firstOperand.sub(secondOperand);
                    break;
                case MULTIPLY:
                    resultOfOperation = firstOperand.multiply(secondOperand);
                    break;
                default:
                    throw new IllegalStateException("No such operation!");
            }
            result = resultOfOperation.toString();
            status = Status.SUCCESS;
        }
    }

    public enum Operation {
        ADD("Add"),
        SUB("Sub"),
        MULTIPLY("Mul");
        private final String name;

        Operation(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public final class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }

    private boolean isInputAvailable() {
        return !firstPolynomial.isEmpty() && !secondPolynomial.isEmpty();
    }

    private boolean parseInput() {
        isCalculateButtonEnabled = false;
        if (!isInputAvailable()) {
            status = Status.WAITING;
        } else {
            if (Parser.getPolynomial(firstPolynomial) != null
                    && Parser.getPolynomial(secondPolynomial) != null) {
                isCalculateButtonEnabled = true;
                status = Status.READY;
            } else {
                status = Status.BAD_FORMAT;
            }
        }
        return isCalculateButtonEnabled;
    }

    private boolean isCalculateButtonEnabled;
    private String firstPolynomial;
    private String secondPolynomial;
    private Operation operation;
    private String result;
    private String status;
}
