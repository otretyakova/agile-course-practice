package ru.unn.agile.ConvertNumeral.Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ConvertNumeral {
    private List<Pair<Integer, String>> number;

    public ConvertNumeral() {

        number = new ArrayList<>();
        for (int i = 0; i < ARABIC_NUMBER.length; i++) {
            number.add(new Pair<>(ARABIC_NUMBER[i], ROMAN_NUMBER[i]));
        }
    }

    private static final Integer[] ARABIC_NUMBER = {1000, 500, 100, 50, 10, 5, 1};
    private static final String[] ROMAN_NUMBER = {"M", "D", "C", "L", "X", "V", "I"};
    private static final Integer BOUNDARY_VALUE = 3999;


    public String convert(final int arabicNumber) {
        return getRomanNumber(arabicNumber);
    }

    public Integer convert(final String romanNumber) {
        return getArabicNumber(romanNumber);
    }

    private Integer getArabicNumber(final String romanNumber) {
        int currentSymbol = 0;
        int sum = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            for (Pair<Integer, String> item : number) {
                if (romanNumber.substring(i, i + 1).equals(item.getValue())) {
                    if (item.getKey() < currentSymbol) {

                        sum += currentSymbol;
                        currentSymbol = item.getKey();

                    } else if (item.getKey() > currentSymbol) {

                        if (currentSymbol == 0) {
                            currentSymbol = item.getKey();
                        } else {
                            sum += item.getKey() - currentSymbol;
                            currentSymbol = 0;
                        }
                    } else if (item.getKey().equals(currentSymbol)) {

                        sum += currentSymbol + item.getKey();
                        currentSymbol = 0;
                    }
                }
            }
        }
        sum += currentSymbol;
        return sum;
    }

    private String getRomanNumber(final int arabicNumber) {

        String romanNumber = "";
        boolean isCorrectSequence = false;
        if (arabicNumber > BOUNDARY_VALUE || arabicNumber < 0) {
            return "Выход за пределы";
        }
        int arabicNumberUse = arabicNumber;
        for (Pair<Integer, String> item : number) {

            while (arabicNumberUse >= item.getKey()) {
                romanNumber += item.getValue();
                arabicNumberUse -= item.getKey();
            }
        }

        while (!isCorrectSequence) {
            String repeatFourSymbol = new String();
            String repeatTwoSymbol = new String();
            String changeRomanNumber = new String();

            repeatFourSymbol = searchRepeatingFourSymbol(romanNumber);
            if (!"".equals(repeatFourSymbol)) {
                romanNumber = changeRepeatFourSymbol(repeatFourSymbol, romanNumber);
            } else {
                repeatTwoSymbol = searchRepeatingTwoSymbols(romanNumber);
                if (!"".equals(repeatTwoSymbol)) {

                    changeRomanNumber = changeRepeatTwoSymbols(repeatTwoSymbol, romanNumber);
                    if (romanNumber.equals(changeRomanNumber)) {
                        isCorrectSequence = true;
                    } else {
                        romanNumber = changeRomanNumber;
                    }

                } else {
                    isCorrectSequence = true;
                }
            }
        }
        return romanNumber;
    }

    private static final Integer START_SEARCH = 3;

    private String searchRepeatingFourSymbol(final String romanNumber) {
        String result = new String();

        for (int i = START_SEARCH; i < romanNumber.length(); i++) {
            if (romanNumber.charAt(i - START_SEARCH) == romanNumber.charAt(i - 2)
                    && romanNumber.charAt(i - 1) == romanNumber.charAt(i - 2)
                    && romanNumber.charAt(i - 1) == romanNumber.charAt(i)) {
                result = romanNumber.substring(i - START_SEARCH, i + 1);
            }
        }
        return result;
    }

    private String searchRepeatingTwoSymbols(final String romanNumber) {
        String result = new String();
        for (int i = 2; i < romanNumber.length(); i++) {
            if (romanNumber.charAt(i - 2) == romanNumber.charAt(i)
                    && romanNumber.charAt(i - 1) != romanNumber.charAt(i - 2)) {
                result = romanNumber.substring(i - 2, i + 1);


            }

        }
        return result;
    }

    private String changeRepeatFourSymbol(final String repeatNumber, final String romanNumber) {

        String newPart = new String();
        String romanNumberReturn = romanNumber;
        int lengthPartRomanNumber = repeatNumber.length();
        for (int j = 0; j < number.size(); j++) {
            if (repeatNumber.substring(lengthPartRomanNumber - 1)
                    .equals(number.get(j).getValue())) {

                newPart = repeatNumber.substring(lengthPartRomanNumber - 1)
                        + number.get(j - 1).getValue();
                romanNumberReturn = romanNumberReturn.replaceAll(repeatNumber, newPart);
            }

        }
        return romanNumberReturn;
    }

    private String changeRepeatTwoSymbols(final String partRomanNumber, final String romanNumber) {

        String returnRomanNumber = romanNumber;
        String newPartRomanNumber = new String();
        int lengthFoundPart = partRomanNumber.length();
        for (int j = 0; j < number.size(); j++) {
            if (partRomanNumber.substring(lengthFoundPart - 1).equals(number.get(j).getValue())
                    && partRomanNumber.substring(1, 2).equals(number.get(j + 1).getValue())) {

                newPartRomanNumber = partRomanNumber.substring(1, lengthFoundPart - 1)
                        + number.get(j - 1).getValue();
                returnRomanNumber = returnRomanNumber
                        .replaceAll(partRomanNumber, newPartRomanNumber);
            }

        }
        return returnRomanNumber;
    }
}

