package ru.unn.agile.ConvertNumeral.ViewModel.legacy;

import ru.unn.agile.ConvertNumeral.Model.ConvertNumeral;

import java.security.InvalidParameterException;

public class ViewModel {
    public Boolean isConvertButtonEnabled() {
        return convertButtonEnabled;
    }

    public boolean isAvailableInsertInput(final int offs, final String str) {
        int newLengthInput = offs + str.length();
        if (newLengthInput > MAX_LENGTH_ARABIC && str.matches(REGEX_ARABIC)) {
            return false;
        }
        String newInput = inputNumber + str;
        return !newInput.isEmpty()
                && (newInput.matches(REGEX_ROMANS) || newInput.matches(REGEX_ARABIC));
    }

    public void setInputNumber(final String inputNumber) {
        this.inputNumber = inputNumber;
        convertButtonEnabled = parseInputNumber();
    }

    public void convert() {
        try {
            switch (currentInputType) {
                case ROMAN:
                    outputNumber = converter.convert(romanNumber).toString();
                    break;
                case ARABIC:
                    outputNumber = converter.convert(arabicNumber);
                    break;
                default:
                    break;
            }
        } catch (InvalidParameterException e) {
            outputNumber = "";
            messageText = Message.error(e);
        }
    }

    public String getOutputNumber() {
        return outputNumber;
    }

    public String getMessageText() {
        return messageText;
    }

    private boolean parseInputNumber() {
        arabicNumber = 0;
        romanNumber = "";

        if (inputNumber.matches(REGEX_ARABIC)) {
            arabicNumber = Integer.parseInt(inputNumber);
            currentInputType = NumberType.ARABIC;
            messageText = Message.ENTER_ARABIC;
            return true;
        }
        if (inputNumber.matches(REGEX_ROMANS)) {
            romanNumber = inputNumber;
            currentInputType = NumberType.ROMAN;
            messageText = Message.ENTER_ROMAN;
            return true;
        }
        if ("".equals(inputNumber)) {
            messageText = Message.DEFAULT;
            return false;
        }

        messageText = Message.INCORRECT_INPUT;
        return false;
    }

    private ConvertNumeral converter = new ConvertNumeral();
    private Boolean convertButtonEnabled = false;
    private String messageText = Message.DEFAULT;
    private NumberType currentInputType;
    private String inputNumber = "";
    private String outputNumber = "";
    private Integer arabicNumber = 0;
    private String romanNumber = "";
    private static final int MAX_LENGTH_ARABIC = 4;
    private static final String REGEX_ROMANS = "[IVXLCDM]+";
    private static final String REGEX_ARABIC = "[0-9]+";

    public enum NumberType {
        ROMAN("Roman"),
        ARABIC("Arabic");
        private final String name;

        NumberType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public static final class Message {
        public static final String DEFAULT = "Please enter a Roman or Arabic number!";
        public static final String ENTER_ROMAN = "You entered a Roman number";
        public static final String ENTER_ARABIC = "You entered a Arabic number";
        public static final String INCORRECT_INPUT = "Incorrect input: "
                + "Arabic numbers should consist only numbers from 0 to 9! \n"
                + "Roman numbers consist only of symbols: I, V, X, L, C, D, M!";

        public static String error(final Exception e) {
            return "Error: " + e.getMessage();
        }

        private Message() {
        }
    }
}
