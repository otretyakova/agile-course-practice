package ru.unn.agile.string_calculator.model;

public final class StringCalculator {
    public int add(final String input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Incorrect data");
        }
        if (input.isEmpty()) {
            return 0;
        }

        String delimiter = extractDelimiter(input);
        String[] numbers = split(input, delimiter);

        checkIncorrectData(numbers);
        checkNegatives(numbers);

        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private String extractDelimiter(final String input) {
        String delimiter = ",\n";
        String firstSymbol = input.substring(0, 1);
        if (firstSymbol.matches("[,.:;]")) {
            delimiter += firstSymbol;
        }
        return delimiter;
    }

    private String[] split(final String input, final String delimiter) {
        String str = input;
        String firstSymbol = str.substring(0, 1);
        if (firstSymbol.matches("[,.:;]")) {
            str = str.substring(2);
        }
        return str.split("[" + delimiter + "]");
    }

    private void checkNegatives(final String[] numbers) {
        StringBuilder error = new StringBuilder(ERROR_BUFFER_SIZE);
        error.append("Negatives ");
        for (String number: numbers) {
            if (Integer.parseInt(number) < 0) {
                error.append(number).append(" ");
            }
        }
        if (!"Negatives ".equals(error.toString())) {
            error.append("not allowed");
            throw new IllegalArgumentException(error.toString());
        }
    }

    private void checkIncorrectData(final String[] numbers) {
        for (String number: numbers) {
            if (number.isEmpty() || !isNumeric(number)) {
                throw new IllegalArgumentException("Incorrect data");
            }
        }
    }

    private boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    private static final int ERROR_BUFFER_SIZE = 50;
}
