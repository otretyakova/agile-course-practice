package ru.unn.agile.string_calculator.model;

public class StringCalculator {
    public int add(final String input) throws IllegalArgumentException {
        if ("".equals(input)) {
            return 0;
        }
        String str = input;
        String delimiter = ",\n";
        String firstSymbol = input.substring(0, 1);
        if (firstSymbol.matches("[,.:;]")) {
            delimiter += firstSymbol;
            str = input.substring(2);
        }

        String[] numbers = str.split("[" + delimiter + "]");

        checkIncorrectData(numbers);
        checkNegatives(numbers);

        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private void checkNegatives(final String[] numbers) {
        String error = "Negatives ";
        for (String number: numbers) {
            if (Integer.parseInt(number) < 0) {
                error += number + " ";
            }
        }
        if (!"Negatives ".equals(error)) {
            error += "not allowed";
            throw new IllegalArgumentException(error);
        }
    }

    private void checkIncorrectData(final String[] numbers) {
        for (String number: numbers) {
            if ("".equals(number) || !isNumeric(number)) {
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
}
