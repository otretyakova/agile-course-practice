package ru.unn.agile.string_calculator.model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class StringCalculatorTest {
    private StringCalculator calc;

    @Before
    public void setUp() {
        calc = new StringCalculator();
    }

    @Test
    public void stringWithoutNumbers() {
        assertAddReturns(0, "");
    }

    @Test
    public void stringWithOneNumber() {
        assertAddReturns(1, "1");
    }

    @Test
    public void stringWithTwoNumbers() {
        assertAddReturns(3, "1,2");
    }

    @Test
    public void stringWithThreeNumbers() {
        assertAddReturns(6, "1,2,3");
    }

    @Test
    public void stringWithFourNumbers() {
        assertAddReturns(10, "1,2,3,4");
    }

    @Test
    public void stringWithALotOfNumbers() {
        assertAddReturns(210, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20");
    }

    @Test
    public void stringWithNewLineSymbol() {
        assertAddReturns(6, "1\n2,3");
    }

    @Test
    public void stringWithNewDelimiter() {
        assertAddReturns(6, ";\n1;2;3");
    }

    @Test
    public void stringWithDotDelimiter() {
        assertAddReturns(6, ".\n1.2.3");
    }

    @Test
    public void stringWithDotDelimiterAndNewLineSymbol() {
        assertAddReturns(6, ".\n1\n2.3");
    }

    @Test
    public void stringWithNegativeNumbers() {
        try {
            assertAddReturns(6, "-1,2,3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 not allowed", e.getMessage());
        }
    }

    @Test
    public void stringWithTwoNegativeNumbers() {
        try {
            assertAddReturns(6, "-1,2,-3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -3 not allowed", e.getMessage());
        }
    }

    @Test
    public void stringWithAllNegativeNumbers() {
        try {
            assertAddReturns(6, "-1,-2,-3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -2 -3 not allowed", e.getMessage());
        }
    }

    @Test
    public void stringWithIncorrectData() {
        try {
            assertAddReturns(6, "sadfasdf");
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void stringWithMistake() {
        try {
            assertAddReturns(6, "1,2,3s");
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    private void assertAddReturns(final int expected, final String input) {
        assertEquals(expected, calc.add(input));
    }
}
