package ru.unn.agile.quadraticequation.model;

import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuadraticEquationTest {

    @Test
    public void canCreateQuadraticEquation() {
        QuadraticEquation equation = new QuadraticEquation(1, 2, 3);
        assertNotNull(equation);
    }

    @Test
    public void willThrowExceptionIfDegenerateEquation() {
        try {
            new QuadraticEquation(0, 0, 2);
            fail("Object can't be created");
        } catch (IllegalArgumentException error) {
        }
    }

    @Test
    public void canGetSimpleDiscriminant() {
        double discriminant = equationDiscriminant(1, 0, 0);
        assertEquals(0, discriminant, 1e-15);
    }

    @Test
    public void canGetPositiveDiscriminant() {
        double discriminant = equationDiscriminant(1, 0, -1);
        assertEquals(4, discriminant, 1e-15);
    }

    @Test
    public void canGetNegativeDiscriminant() {
        double discriminant = equationDiscriminant(1, 2, 3);
        assertEquals(-8, discriminant, 1e-15);
    }

    @Test
    public void willThrowExceptionIfDiscriminantNotExist() {
        try {
            double discriminant = equationDiscriminant(0, 1, 0);
            fail("Object can't be get");
        } catch (IllegalArgumentException error) {
        }
    }

    @Test
    public void canGetNumberofRealRootsSimple() {
        int number = equationRealRootsNumber(1, 0, 0);
        assertEquals(1, number);
    }

    @Test
    public void canGetNumberofRealRootsTwo() {
        int number = equationRealRootsNumber(1, 2, -1);
        assertEquals(2, number);
    }

    @Test
    public void canGetNumberofRealRootsNull() {
        int number = equationRealRootsNumber(1, 2, 2);
        assertEquals(0, number);
    }

    @Test
    public void canGetNumberofRealRootsLinearEquation() {
        int number = equationRealRootsNumber(0, 2, 2);
        assertEquals(1, number);
    }

    @Test
    public void canGetNumberofImaginaryRootsSimple() {
        int number = equationImaginaryRootsNumber(1, 0, 0);
        assertEquals(0, number);
    }

    @Test
    public void canGetNumberofImaginaryRoots() {
        int number = equationImaginaryRootsNumber(1, 4, 5);
        assertEquals(2, number);
    }

    @Test
    public void canSolveQuadraticEquationSimpleFirst() {
        Pair<Double, Double> solve = equationRealSolve(4, 0, 0);
        assertEquals(0.0, solve.getKey(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationSimpleSecond() {
        Pair<Double, Double> solve = equationRealSolve(4, 0, 0);
        assertEquals(null, solve.getValue());
    }

    @Test
    public void canSolveQuadraticEquationOneRealRootFirst() {
        Pair<Double, Double> solve = equationRealSolve(1, 4, 4);
        assertEquals(-2.0, solve.getKey(), 1e-15);
    }

    @Test
    public void canSolveLinearEquationOneRealRootFirst() {
        Pair<Double, Double> solve = equationRealSolve(0, 6, -2);
        assertEquals(1.0 / 3, solve.getKey(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationNoRealRootsFirst() {
        Pair<Double, Double> solve = equationRealSolve(6, -4, 3.3);
        assertEquals(null, solve.getKey());
    }

    @Test
    public void canSolveQuadraticEquationNoRealRootsSecond() {
        Pair<Double, Double> solve = equationRealSolve(6, -4, 3.3);
        assertEquals(null, solve.getKey());
    }

    @Test
    public void canSolveQuadraticEquationTwoRealRootsFirst() {
        Pair<Double, Double> solve = equationRealSolve(-2, 3, 1);
        assertEquals(0.75 - Math.sqrt(17) / 4, solve.getKey(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationTwoRealRootsSecond() {
        Pair<Double, Double> solve = equationRealSolve(-2, 3, 1);
        assertEquals(0.75 + Math.sqrt(17) / 4, solve.getValue(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationNoImaginaryRootsFirst() {
        Pair<Complex, Complex> solve = equationImaginarySolve(-1, -4, 3.3);
        assertEquals(null, solve.getKey());
    }

    @Test
    public void canSolveQuadraticEquationNoImaginaryRootsSecond() {
        Pair<Complex, Complex> solve = equationImaginarySolve(-1, -4, 3.3);
        assertEquals(null, solve.getValue());
    }

    @Test
    public void canSolveQuadraticEquationImaginaryRootsFirstRe() {
        Pair<Complex, Complex> solve = equationImaginarySolve(3, -4, 3);
        assertEquals(2.0 / 3, solve.getKey().getReal(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationImaginaryRootsFirstIm() {
        Pair<Complex, Complex> solve = equationImaginarySolve(3, -4, 3);
        assertEquals(Math.sqrt(20) / 2, solve.getKey().getImaginary(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationImaginaryRootsSecondRe() {
        Pair<Complex, Complex> solve = equationImaginarySolve(3, -4, 3);
        assertEquals(2.0 / 3, solve.getValue().getReal(), 1e-15);
    }

    @Test
    public void canSolveQuadraticEquationImaginaryRootsSecondIm() {
        Pair<Complex, Complex> solve = equationImaginarySolve(3, -4, 3);
        assertEquals((-1) * Math.sqrt(20) / 2, solve.getValue().getImaginary(), 1e-15);
    }

    private double equationDiscriminant(final double a, final double b, final double c) {
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        return equation.getDiscriminant();
    }

    private int equationRealRootsNumber(final double a, final double b, final double c) {
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        return equation.getNumberofRealRoots();
    }

    private int equationImaginaryRootsNumber(final double a, final double b, final double c) {
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        return equation.getNumberofImaginaryRoots();
    }

    private Pair<Double, Double> equationRealSolve(final double a, final double b, final double c) {
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        return equation.solveQuadraticEquationReal();
    }

    private Pair<Complex, Complex> equationImaginarySolve(
            final double a, final double b, final double c) {
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        return equation.solveQuadraticEquationImaginary();
    }

}
