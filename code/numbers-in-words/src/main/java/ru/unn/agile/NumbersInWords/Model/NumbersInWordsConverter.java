package ru.unn.agile.NumbersInWords.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NumbersInWordsConverter {

    private Map<String, String> digits;
    private Map<String, String> twoDigitNumbersBeforeTwenty;
    private Map<String, String> twoDigitNumbersAfterTwenty;
    private Map<Integer, String> digitPosition;

    public NumbersInWordsConverter() {
        digits = new HashMap<String, String>();
        digits.put("0", "zero");
        digits.put("1", "one");
        digits.put("2", "two");
        digits.put("3", "three");
        digits.put("4", "four");
        digits.put("5", "five");
        digits.put("6", "six");
        digits.put("7", "seven");
        digits.put("8", "eight");
        digits.put("9", "nine");

        twoDigitNumbersBeforeTwenty = new HashMap<String, String>();
        twoDigitNumbersBeforeTwenty.put("0", "ten");
        twoDigitNumbersBeforeTwenty.put("1", "eleven");
        twoDigitNumbersBeforeTwenty.put("2", "twelve");
        twoDigitNumbersBeforeTwenty.put("3", "thirteen");
        twoDigitNumbersBeforeTwenty.put("4", "fourteen");
        twoDigitNumbersBeforeTwenty.put("5", "fifteen");
        twoDigitNumbersBeforeTwenty.put("6", "sixteen");
        twoDigitNumbersBeforeTwenty.put("7", "seventeen");
        twoDigitNumbersBeforeTwenty.put("8", "eighteen");
        twoDigitNumbersBeforeTwenty.put("9", "nineteen");

        twoDigitNumbersAfterTwenty = new HashMap<String, String>();
        twoDigitNumbersAfterTwenty.put("2", "twenty");
        twoDigitNumbersAfterTwenty.put("3", "thirty");
        twoDigitNumbersAfterTwenty.put("4", "forty");
        twoDigitNumbersAfterTwenty.put("5", "fifty");
        twoDigitNumbersAfterTwenty.put("6", "sixty");
        twoDigitNumbersAfterTwenty.put("7", "seventy");
        twoDigitNumbersAfterTwenty.put("8", "eighty");
        twoDigitNumbersAfterTwenty.put("9", "ninety");

        final int milliardPosition = 3;
        final int millionPosition = 2;
        final int thousandPosition = 1;
        digitPosition = new HashMap<Integer, String>();
        digitPosition.put(thousandPosition, "thousand");
        digitPosition.put(millionPosition, "million");
        digitPosition.put(milliardPosition, "milliard");
    }

    public String convert(final String number) throws IllegalArgumentException {
        String integerConvertedNumber = "", decimalConvertedNumber = "";
        String positiveNumber = "";

        if (number == null) {
            throw new IllegalArgumentException("Input number is null");
        } else if (number.isEmpty()) {
            throw new IllegalArgumentException("Input number is empty");
        }

        if (number.charAt(0) == '-') {
            integerConvertedNumber += "negative ";
            positiveNumber = number.substring(1);
        } else {
            positiveNumber = number;
        }

        String[] numberParts = positiveNumber.split("\\.");
        if (numberParts.length == 1) {
            integerConvertedNumber += convertIntegerPart(numberParts[0]);
        } else if (numberParts.length == 2) {
            integerConvertedNumber += convertIntegerPart(numberParts[0]);
            decimalConvertedNumber += convertDecimalPart(numberParts[1]);
        } else {
            throw new IllegalArgumentException("Input number must have no more than one point");
        }

        return integerConvertedNumber + decimalConvertedNumber;
    }

    private String convertIntegerPart(final String number) throws IllegalArgumentException {
        String convertedNumber = "";
        final int subNumberLength = 3;

        if (number.length() == 1) {
            return convertOneDigitNumber(number);
        } else if (number.length() == 2) {
            return convertTwoDigitNumber(number);
        }

        String processedNumber = addZerosToBeginningString(number, subNumberLength);

        int i = processedNumber.length() - subNumberLength;
        String subNumber = processedNumber.substring(i, i + subNumberLength);
        convertedNumber = convertThreeDigitNumber(subNumber);
        boolean isOneOrTwoDigitSubNumber = !isStringsEqual(convertedNumber, "")
                && (subNumber.charAt(0) == '0');
        if (isOneOrTwoDigitSubNumber) {
            convertedNumber = "and " + convertedNumber;
        }

        int position = 1;
        for (i = processedNumber.length() - 2 * subNumberLength; i >= 0; i -= subNumberLength) {
            subNumber = processedNumber.substring(i, i + subNumberLength);
            String convertedThreeDigitNumber = convertThreeDigitNumber(subNumber);
            if (!isStringsEqual(convertedThreeDigitNumber, "")) {
                if (!isStringsEqual(convertedNumber, "")) {
                    convertedNumber = convertedThreeDigitNumber + " "
                            + convertDigitPosition(position) + " " + convertedNumber;
                } else {
                    convertedNumber = convertedThreeDigitNumber + " "
                            + convertDigitPosition(position);
                }
            }
            position++;
        }
        return convertedNumber;
    }

    private String convertDecimalPart(final String number) throws IllegalArgumentException {
        String convertedNumber = " point";
        for (int i = 0; i < number.length(); i++) {
            convertedNumber += " " + convertOneDigitNumber(number.substring(i, i + 1));
        }
        return convertedNumber;
    }

    private String addZerosToBeginningString(final String string, final int subStringLength) {
        String newString = "";
        int remainder = subStringLength - string.length() % subStringLength;
        if (remainder != subStringLength) {
            newString = String.join("", Collections.nCopies(remainder, "0")) + string;
        } else {
            newString = string;
        }
        return newString;
    }

    private String convertOneDigitNumber(final String digit) {
        String convertedDigit = digits.get(digit);
        if (convertedDigit == null) {
            throw new IllegalArgumentException("Input number contains incorrect symbols");
        }
        return convertedDigit;
    }

    private String convertTwoDigitNumber(final String number) throws IllegalArgumentException {
        String convertedNumber = "";
        String firstDigit = number.substring(0, 1);
        String secondDigit = number.substring(1, 2);

        if (isStringsEqual(firstDigit, "1")) {
            convertedNumber = convertTwoDigitNumberBeforeTwenty(secondDigit);
        } else if (!isStringsEqual(firstDigit, "0")) {
            String twoDigitNumber = twoDigitNumbersAfterTwenty.get(firstDigit);
            if (twoDigitNumber == null) {
                throw new IllegalArgumentException("Input number contains incorrect symbols");
            }
            convertedNumber = twoDigitNumber + " " + convertOneDigitNumber(secondDigit);
        } else if (isStringsEqual(firstDigit, "0") && !isStringsEqual(secondDigit, "0")) {
            convertedNumber = convertOneDigitNumber(secondDigit);
        }

        return convertedNumber;
    }

    private String convertTwoDigitNumberBeforeTwenty(final String secondDigit)
            throws IllegalArgumentException {
        String convertedNumber = twoDigitNumbersBeforeTwenty.get(secondDigit);
        if (convertedNumber == null) {
            throw new IllegalArgumentException("Input number contains incorrect symbols");
        }
        return convertedNumber;
    }

    private String convertThreeDigitNumber(final String number) throws IllegalArgumentException {
        String convertedNumber = "";

        final int startOfFirstDigit = 0;
        final int startOfSecondDigit = 1;
        final int startOfThirdDigit = 2;
        final int endOfFirstDigit = 1;
        final int endOfSecondDigit = 2;
        final int endOfThirdDigit = 3;
        String firstDigit = number.substring(startOfFirstDigit, endOfFirstDigit);
        String secondDigit = number.substring(startOfSecondDigit, endOfSecondDigit);
        String thirdDigit = number.substring(startOfThirdDigit, endOfThirdDigit);

        String convertedTwoDigitNumber = convertTwoDigitNumber(secondDigit + thirdDigit);
        if (!isStringsEqual(firstDigit, "0")) {
            if (isStringsEqual(convertedTwoDigitNumber, "")) {
                convertedNumber = convertOneDigitNumber(firstDigit) + " hundred";
            } else {
                convertedNumber = convertOneDigitNumber(firstDigit)
                        + " hundred and " + convertedTwoDigitNumber;
            }
        } else {
            convertedNumber = convertedTwoDigitNumber;
        }
        return convertedNumber;
    }

    private String convertDigitPosition(final int position) throws IllegalArgumentException {
        String convertedPosition = digitPosition.get(position);
        if (convertedPosition == null) {
            throw new IllegalArgumentException("Input number is too big or too small");
        }
        return convertedPosition;
    }

    boolean isStringsEqual(final String str, final String expected) {
        return str != null && str.equals(expected);
    }

}
