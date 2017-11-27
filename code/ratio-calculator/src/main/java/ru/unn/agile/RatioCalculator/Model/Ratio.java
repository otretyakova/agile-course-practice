package ru.unn.agile.RatioCalculator.Model;

public class Ratio {
    public Ratio() {
        init(0, 1);
    }

    public Ratio(final int numerator, final int denominator) {
        init(numerator, denominator);
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }


    private void init(final int numerator, final int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private int numerator;
    private int denominator;
}
