package ru.unn.agile.quadraticequation.model;

import javafx.util.Pair;

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

    public QuadraticEquation(final double a, final double b, final double c) {
        if ((a == 0) && (b == 0)) {
            throw new IllegalArgumentException("QuadraticEquation is degenerated.");
        } else {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public double getDiscriminant() {
        final int d = 4;
        if (this.a == 0) {
            throw new IllegalArgumentException("Discriminant is not exist.");
        } else {
            return this.b * this.b - d * this.a * this.c;
        }
    }

    public int getNumberofRealRoots() {
        if (this.a > 0) {
            if (this.getDiscriminant() == 0) {
                return 1;
            } else {
                if (this.getDiscriminant() > 0) {
                    return 2;
                } else {
                    return 0;
                }
            }
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

    public Pair<Double, Double> solveQuadraticEquationReal() {
        if (this.getA() == 0) {
            return new Pair<Double, Double>((-1) * this.getC() / this.getB(), null);
        }
        if (this.getDiscriminant() < 0) {
            return new Pair<Double, Double>(null, null);
        }
        if (this.getDiscriminant() == 0) {
            return new Pair<Double, Double>((-1) * this.getB() / 2 / this.getA(), null);
        }
        return new Pair<Double, Double>(
                ((-1) * this.getB() + Math.sqrt(this.getDiscriminant())) / 2 / this.getA(),
                ((-1) * this.getB() - Math.sqrt(this.getDiscriminant())) / 2 / this.getA());
    }

    public Pair<Complex, Complex> solveQuadraticEquationImaginary() {
        if (getNumberofImaginaryRoots() == 0) {
            return new Pair<Complex, Complex>(null, null);
        }
        Complex first = new Complex((-1) * this.getB() / 2 / this.getA(),
                Math.sqrt((-1) * this.getDiscriminant()) / 2);
        Complex second = new Complex((-1) * this.getB() / 2 / this.getA(),
                (-1) * Math.sqrt((-1) * this.getDiscriminant()) / 2);
       return new Pair<Complex, Complex>(first, second);
    }

    private double a;
    private double b;
    private double c;
}
