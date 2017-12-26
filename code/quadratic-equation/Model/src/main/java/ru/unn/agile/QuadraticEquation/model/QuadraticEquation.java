package ru.unn.agile.QuadraticEquation.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class QuadraticEquation {

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public void setA(final double a) {
        this.a = a;
    }

    public void setB(final double b) {
        this.b = b;
    }

    public void setC(final double c) {
        this.c = c;
    }

    public void setABC(final double a, final double b, final double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation(final double a, final double b, final double c) {
        if ((a == 0) && (b == 0)) {
            throw new IllegalArgumentException("QuadraticEquation is degenerated.");
        } else {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public QuadraticEquation(final String a, final String b, final String c) {
        double newA = Double.parseDouble(a);
        double newB = Double.parseDouble(b);
        double newC = Double.parseDouble(c);
        if ((newA == 0) && (newB == 0)) {
            throw new IllegalArgumentException("QuadraticEquation is degenerated.");
        } else {
            this.a = newA;
            this.b = newB;
            this.c = newC;
        }
    }

    public double getDiscriminant() {
        if (this.isLinear()) {
            throw new IllegalArgumentException("Discriminant is not exist.");
        } else {
            return b * b - DISCRIMINANT_MULTIPLIER * a * c;
        }
    }

    public int getNumberofRealRoots() {
        if (!this.isLinear()) {
            return numberOfRootsIfIsNotLinear();
        } else {
            return 1;
        }
    }

    public int getNumberofImaginaryRoots() {
        if (this.getNumberofRealRoots() > 0) {
            return 0;
        } else {
            return 2;
        }
    }

    public String toString(final Complex number) {
        return Formatter.getFormatted(number);
    }

    public Pair<Double, Double> solveQuadraticEquationReal() {
        if (this.isLinear()) {
            return new Pair<Double, Double>((-1) * c / b, null);
        }
        if (this.getDiscriminant() < 0) {
            return new Pair<Double, Double>(null, null);
        }
        if ((this.getDiscriminant() > -DELTA) && (this.getDiscriminant() < DELTA)) {
            return new Pair<Double, Double>(-b / 2 / a, null);
        }
        return new Pair<Double, Double>(this.realFirst(), this.realSecond());
    }

    public Pair<Complex, Complex> solveQuadraticEquationImaginary() {
        if (this.getNumberofImaginaryRoots() == 0) {
            return new Pair<Complex, Complex>(null, null);
        }
        return new Pair<Complex, Complex>(this.cmplxFirst(), this.cmplxSecond());
    }

    public List<String> solveQuadraticEquation() {
        List<String> result = new ArrayList<String>();
        if (this.isLinear()) {
            Complex first = new Complex((-1) * this.getC() / this.getB(), 0.);
            result.add(toString(first));
            return result;
        }
        if ((this.getDiscriminant() > -DELTA) && (this.getDiscriminant() < DELTA)) {
            Complex first = new Complex((-1) * this.getB() / 2 / this.getA(), 0.);
            result.add(toString(first));
            return result;
        }
        if (this.getDiscriminant() > 0) {
            Complex first = new Complex(this.realFirst(), 0.);
            Complex second = new Complex(this.realSecond(), 0.);
            result.add(toString(first));
            result.add(toString(second));
            return result;
        }

        result.add(toString(this.cmplxFirst()));
        result.add(toString(this.cmplxSecond()));
        return result;
    }

    private boolean isNumberZero(final double value) {
        return (value > -DELTA) && (value < DELTA);
    }

    private boolean isLinear() {
        return isNumberZero(a);
    }

    private boolean hasZeroDiscriminant() {
        return isNumberZero(this.getDiscriminant());
    }

    private int numberOfRootsIfIsNotLinear() {
        if (this.hasZeroDiscriminant()) {
            return 1;
        } else if (this.getDiscriminant() > 0) {
            return 2;
        } else {
            return 0;
        }
    }

    public static double getBorderTop() {
        return BORDER_TOP;
    }

    public static double getBorderBottom() {
        return BORDER_BOTTOM;
    }

    private Complex cmplxFirst() {
        return new Complex(-b / 2 / a,
                Math.sqrt(-this.getDiscriminant()) / 2);
    }

    private Complex cmplxSecond() {
        return new Complex(-b / 2 / a,
                -Math.sqrt(-this.getDiscriminant()) / 2);
    }

    private double realFirst() {
        return ((-b + Math.sqrt(this.getDiscriminant())) / 2 / a);
    }

    private double realSecond() {
        return ((-b - Math.sqrt(this.getDiscriminant())) / 2 / a);
    }

    private double a;
    private double b;
    private double c;
    private static final int DISCRIMINANT_MULTIPLIER = 4;
    private static final double DELTA = 1e-15;
    private static final double BORDER_TOP = 10e12;
    private static final double BORDER_BOTTOM = -10e12;
}
