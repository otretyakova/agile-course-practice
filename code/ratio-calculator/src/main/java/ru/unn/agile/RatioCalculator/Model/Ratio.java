package ru.unn.agile.RatioCalculator.Model;

public class Ratio {
    public Ratio() {
        init(0, 1);
    }

    public Ratio(final int numerator, final int denominator) {
        init(numerator, denominator);
    }

    public Ratio(final int wholeNumber) {
        init(wholeNumber, 1);
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getWholePart() {
        return numerator / denominator;
    }

    public boolean isEqual(final Ratio other) {
        return (numerator == other.numerator)
                && (denominator == other.denominator);
    }

    public boolean isEqual(final int wholeNumber) {
        Ratio otherRatio = new Ratio(wholeNumber);
        return isEqual(otherRatio);
    }

    public boolean isLess(final Ratio other) {
        int commonDenominator = lcm(denominator, other.denominator);
        return numerator * (commonDenominator / denominator)
                < other.numerator * (commonDenominator / other.denominator);
    }

    public Ratio add(final Ratio other) {
        int commonDenominator = lcm(denominator, other.denominator);
        int newNumerator1 = numerator * (commonDenominator / denominator);
        int newNumerator2 = other.numerator * (commonDenominator / other.denominator);
        return new Ratio(newNumerator1 + newNumerator2, commonDenominator);
    }

    public Ratio add(final int number) {
        return add(new Ratio(number));
    }

    public Ratio negate() {
        return new Ratio(-numerator, denominator);
    }

    public Ratio sub(final Ratio other) {
        return add(other.negate());
    }

    public Ratio sub(final int number) {
        return sub(new Ratio(number));
    }

    public Ratio mult(final Ratio other) {
        return new Ratio(numerator * other.numerator,
                denominator * other.denominator);
    }

    public Ratio mult(final int number) {
        return mult(new Ratio(number));
    }

    public Ratio reverse() {
        return new Ratio(denominator, numerator);
    }

    public Ratio div(final Ratio other) {
        return mult(other.reverse());
    }

    public Ratio div(final int number) {
        return div(new Ratio(number));
    }

    private void init(final int numerator, final int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Divide by zero is restricted!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    private void reduce() {
        int gcdValue = gcd(numerator, denominator);
        numerator = numerator / gcdValue;
        denominator = denominator / gcdValue;
    }

    private static int gcd(final int first, final int second) {
        int u = first;
        int v = second;
        while (v != 0) {
            int r = u % v;
            u = v;
            v = r;
        }
        return u;
    }

    private static int lcm(final int first, final int second) {
        return first / gcd(first, second) * second;
    }

    private int numerator;
    private int denominator;
}
