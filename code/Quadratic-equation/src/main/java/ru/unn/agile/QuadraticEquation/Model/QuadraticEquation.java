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
        if (this.isLinear()) {
            throw new IllegalArgumentException("Discriminant is not exist.");
        } else {
            return this.getB() * this.getB() - d * this.getA() * this.getC();
        }
    }

    public int getNumberofRealRoots() {
        final double delta = 1e-15;
        if (!this.isLinear()) {
            if ((this.getDiscriminant() > -delta) && (this.getDiscriminant() < delta)) {
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
        final double delta = 1e-15;
        if (this.isLinear()) {
            return new Pair<Double, Double>((-1) * this.getC() / this.getB(), null);
        }
        if (this.getDiscriminant() < 0) {
            return new Pair<Double, Double>(null, null);
        }
        if ((this.getDiscriminant() > -delta) && (this.getDiscriminant() < delta)) {
            return new Pair<Double, Double>(-this.getB() / 2 / this.getA(), null);
        }
        return new Pair<Double, Double>(
                (-this.getB() + Math.sqrt(this.getDiscriminant())) / 2 / this.getA(),
                (-this.getB() - Math.sqrt(this.getDiscriminant())) / 2 / this.getA());
    }

    public Pair<Complex, Complex> solveQuadraticEquationImaginary() {
        if (getNumberofImaginaryRoots() == 0) {
            return new Pair<Complex, Complex>(null, null);
        }
        Complex first = new Complex(-this.getB() / 2 / this.getA(),
                Math.sqrt(-this.getDiscriminant()) / 2);
        Complex second = new Complex(-this.getB() / 2 / this.getA(),
                -Math.sqrt(-this.getDiscriminant()) / 2);
       return new Pair<Complex, Complex>(first, second);
    }

    private boolean isLinear() {
        final double delta = 1e-15;
        return (this.getA() > -delta) && (this.getA() < delta);

    }

    private double a;
    private double b;
    private double c;
}
