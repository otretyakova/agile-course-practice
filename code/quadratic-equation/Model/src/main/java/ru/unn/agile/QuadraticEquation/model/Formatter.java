package ru.unn.agile.QuadraticEquation.model;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public final class Formatter {

    public static String formatPositiveDouble(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(value);
    }

    public static String getFormatted(final Complex z) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(z.getReal() < 0 ? "-" : "");
        String re = formatPositiveDouble(Math.abs(z.getReal()));
        String im = formatPositiveDouble(Math.abs(z.getImaginary()));
        buffer.append(re)
                .append(z.getImaginary() < 0 ? " - " : " + ")
                .append(im)
                .append("i");
        return buffer.toString();
    }

    private Formatter() {
    }
}



