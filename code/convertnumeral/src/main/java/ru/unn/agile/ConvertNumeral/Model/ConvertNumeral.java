package ru.unn.agile.ConvertNumeral.Model;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class ConvertNumeral {

    public Integer convert(final String romanNumber) {
        return getArabicNumber(romanNumber);
    }

    public String convert(final int arabicNumber) {
        return getRomanNumber(arabicNumber);
    }

    private Integer getArabicNumber(final String romanNumber) {
        Integer returnArabicNumber = 0;
        String cutRomanNumber = romanNumber;
        String previousRoman = "";
        String currentRoman = "";
        int countOfRepeatedSymbols = 0;
        int index = 0;

        while (cutRomanNumber.length() > 0) {
            countOfRepeatedSymbols = 0;
            currentRoman = ROMAN_NUMBERS[index];

            while (isCurrentRomanEqualFirstOfString(currentRoman, cutRomanNumber)) {
                countOfRepeatedSymbols++;
                if (isCurrentValueNotAcceptable(previousRoman,
                        currentRoman,
                        countOfRepeatedSymbols)) {
                    throw new InvalidParameterException("Incorrectly entered number!");
                }

                previousRoman = currentRoman;
                cutRomanNumber = cutRomanNumber.substring(currentRoman.length());
                returnArabicNumber += ARABIC_NUMBERS[index];
            }

            index++;

            if (index >= ROMAN_NUMBERS.length && cutRomanNumber.length() != 0) {
                throw new InvalidParameterException("Incorrectly entered number!");
            }
        }
        return returnArabicNumber;
    }

    private boolean isCurrentRomanEqualFirstOfString(final String currentNumber,
                                                     final String cutRomanNumber) {
        return cutRomanNumber.length() >= currentNumber.length()
                && currentNumber.equals(cutRomanNumber.substring(0, currentNumber.length()));
    }

    private boolean isCurrentValueNotAcceptable(final String previous,
                                                final String current,
                                                final int countOfRepeatedSymbols) {
        if (countOfRepeatedSymbols > MAX_COUNT_OF_REPEATED_SYMBOLS) {
            return true;
        }
        if (ROMAN_NUMBERS_CONTAINS_ONE.contains(previous) || previous.length() == 0) {
            return false;
        }
        if (ROMAN_NUMBERS_CONTAINS_FIVE.contains(previous)) {
            return (getDigitNumber(previous) == getDigitNumber(current)
                    && !(ROMAN_NUMBERS_CONTAINS_ONE.contains(current)));
        }
        return getDigitNumber(previous) == getDigitNumber(current);
    }

    private int getDigitNumber(final String roman) {
        int digitNumber = 0;
        for (int j = 0; j < ROMAN_NUMBERS.length; j++) {
            if (ROMAN_NUMBERS[j].equals(roman)) {
                digitNumber++;
                if (j > MAX_INDEX_TEN) {
                    return digitNumber;
                }
                digitNumber++;
                if (j > MAX_INDEX_HUNDRED && j <= MAX_INDEX_TEN) {
                    return digitNumber;
                }
                digitNumber++;
                if (j > MAX_INDEX_THOUSAND && j <= MAX_INDEX_HUNDRED) {
                    return digitNumber;
                }
                digitNumber++;
                return digitNumber;
            }
        }
        return -1;
    }

    private String getRomanNumber(final int arabicNumber) {
        if (arabicNumber > MAX_VALUE || arabicNumber < 0) {
            throw new InvalidParameterException("Incorrectly entered number."
                    + " Exceeding the allowed values!");
        }
        int index = 0;
        int arabicNumberUse = arabicNumber;
        String returnRomanNumber = new String();
        while (arabicNumberUse > 0) {
            while (ARABIC_NUMBERS[index] <= arabicNumberUse) {
                returnRomanNumber += ROMAN_NUMBERS[index];
                arabicNumberUse -= ARABIC_NUMBERS[index];
            }
            index++;
        }
        return returnRomanNumber;
    }

    private static final Integer[] ARABIC_NUMBERS
            = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_NUMBERS
            = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final List<String> ROMAN_NUMBERS_CONTAINS_FIVE
            = Arrays.asList("V", "L", "D");
    private static final List<String> ROMAN_NUMBERS_CONTAINS_ONE
            = Arrays.asList("M", "C", "X", "I");
    private static final Integer MAX_VALUE = 3999;
    private static final Integer MAX_COUNT_OF_REPEATED_SYMBOLS = 3;
    private static final int MAX_INDEX_THOUSAND = 0;
    private static final int MAX_INDEX_HUNDRED = 4;
    private static final int MAX_INDEX_TEN = 8;
}
