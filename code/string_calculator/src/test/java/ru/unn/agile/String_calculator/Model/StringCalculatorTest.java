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
    public void canAddStringWithoutNumbers() {
        assertEquals(0, calc.add(""));
    }

    @Test
    public void canAddStringWithOneNumber() {
        assertEquals(1, calc.add("1"));
    }

    @Test
    public void canAddStringWithTwoNumbers() {
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    public void canAddStringWithThreeNumbers() {
        assertEquals(6, calc.add("1,2,3"));
    }

    @Test
    public void canAddStringWithFourNumbers() {
        assertEquals(10, calc.add("1,2,3,4"));
    }

    @Test
    public void canAddStringWithALotOfNumbers() {
        assertEquals(210, calc.add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20"));
    }

    @Test
    public void canAddStringWithNewLineSymbol() {
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    public void canAddStringWithNewDelimiter() {
        assertEquals(6, calc.add(";\n1;2;3"));
    }

    @Test
    public void canAddStringWithDotDelimiter() {
        assertEquals(6, calc.add(".\n1.2.3"));
    }

    @Test
    public void canAddStringWithDotDelimiterAndNewLineSymbol() {
        assertEquals(6, calc.add(".\n1\n2.3"));
    }

    @Test
    public void canAddStringWithNegativeNumbers() {
        try {
            assertEquals(6, calc.add("-1,2,3"));
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 not allowed", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithTwoNegativeNumbers() {
        try {
            assertEquals(6, calc.add("-1,2,-3"));
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -3 not allowed", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithAllNegativeNumbers() {
        try {
            assertEquals(6, calc.add("-1,-2,-3"));
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -2 -3 not allowed", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithIncorrectData() {
        try {
            assertEquals(6, calc.add("sadfasdf"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithMistake() {
        try {
            assertEquals(6, calc.add("1,2,3s"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithNull() {
        try {
            assertEquals(6, calc.add(null));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithDifferentDelimiters() {
        try {
            assertEquals(15, calc.add("1,2;3.4:5"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithoutDelimiterAtBeginning() {
        try {
            assertEquals(15, calc.add("1.2.3.4.5"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithBigNumbers() {
        try {
            assertEquals(15, calc.add("1e+40,1e+40"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithOneBigNumber() {
        try {
            assertEquals(15, calc.add("1e+80,1"));
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect data", e.getMessage());
        }
    }
}
