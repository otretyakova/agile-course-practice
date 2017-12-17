package ru.unn.agile.StringCalculator.Model;

public final class StringCalculator {
    public static int add(final String input) throws IllegalArgumentException {
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

    public static Boolean isIncorrectData(final String input) {
        String delimiter = extractDelimiter(input);
        String[] numbers = split(input, delimiter);

        try {
            checkIncorrectData(numbers);
            checkNegatives(numbers);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private static String extractDelimiter(final String input) {
        String delimiter = ",\n";
        String firstSymbol = input.substring(0, 1);
        if (firstSymbol.matches("[,.:;]")) {
            delimiter += firstSymbol;
        }
        return delimiter;
    }

    private static String[] split(final String input, final String delimiter) {
        String str = input;
        String firstSymbol = str.substring(0, 1);
        if (firstSymbol.matches("[,.:;]")) {
            str = str.substring(2);
        }
        return str.split("[" + delimiter + "]");
    }

    private static void checkNegatives(final String[] numbers) {
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

    private static void checkIncorrectData(final String[] numbers) {
        for (String number: numbers) {
            if (number.isEmpty() || !isNumeric(number)) {
                throw new IllegalArgumentException("Incorrect data");
            }
        }
    }

    private static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private StringCalculator() {
    }

    private static final int ERROR_BUFFER_SIZE = 50;
}
