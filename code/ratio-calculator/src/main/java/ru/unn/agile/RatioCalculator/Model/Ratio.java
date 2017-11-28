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

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(numerator);
        int result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(denominator);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    @Override
    public boolean equals(final Object otherRatio) {
        if (otherRatio.getClass() == Ratio.class) {
            Ratio other = (Ratio) otherRatio;
            return (numerator == other.numerator)
                    && (denominator == other.denominator);
        }
        if (otherRatio.getClass() == Integer.class) {
            return (numerator == (int) (otherRatio))
                    && (denominator == 1);
        }
        return false;
    }

    public int compareTo(final Ratio other) {
        if ((long) (numerator) * other.denominator < (long) (denominator) * other.numerator) {
            return -1;
        }
        if ((long) (numerator) * other.denominator > (long) (denominator) * other.numerator) {
            return 1;
        }
        return 0;
    }

    public Ratio add(final Ratio other) {
        int commonDenominator = lcm(denominator, other.denominator);
        long newNumerator = (long) (numerator) * (commonDenominator / denominator)
                + (long) (other.numerator) * (commonDenominator / other.denominator);
        intOverflowCheck(newNumerator, "The result numerator is too big");
        return new Ratio((int) newNumerator, commonDenominator);
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
        long newNumerator = (long) (numerator) * other.numerator;
        intOverflowCheck(newNumerator, "The result numerator is too big");
        long newDenominator = (long) (denominator) * other.denominator;
        intOverflowCheck(newDenominator, "The result denominator is too big");
        return new Ratio((int) newNumerator, (int) newDenominator);
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

    public class IntegerOverflowException extends RuntimeException {
        IntegerOverflowException(final String msg) {
            super(msg);
        }
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
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
    }

    private void intOverflowCheck(final long number, final String errorMsg) {
        if ((number < Integer.MIN_VALUE) || (number > Integer.MAX_VALUE)) {
            throw new IntegerOverflowException(errorMsg);
        }
    }

    private int gcd(final int first, final int second) {
        int u = first;
        int v = second;
        while (v != 0) {
            int r = u % v;
            u = v;
            v = r;
        }
        return u;
    }

    private int lcm(final int first, final int second) {
        long lcmValue = (long) (first) / gcd(first, second) * second;
        intOverflowCheck(lcmValue, "The common denominator is too big");
        return (int) lcmValue;
    }

    private int numerator;
    private int denominator;
}
