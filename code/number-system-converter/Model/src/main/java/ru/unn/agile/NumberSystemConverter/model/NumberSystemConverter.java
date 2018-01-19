package ru.unn.agile.NumberSystemConverter.model;


import java.math.BigInteger;
import java.util.Locale;

public final class NumberSystemConverter {

    public static String convert(final String input, final NumberSystemBase baseSystem,
                                 final NumberSystemBase targetSystem) throws NumberFormatException,
            IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Input is Null");
        }
        if (input.isEmpty()) {
            return "";
        }
        BigInteger inputInteger = new BigInteger(input, baseSystem.getBaseSystem());
        return inputInteger.toString(targetSystem.getBaseSystem()).toUpperCase(Locale.US);
    }

    private NumberSystemConverter() {
    }
}
