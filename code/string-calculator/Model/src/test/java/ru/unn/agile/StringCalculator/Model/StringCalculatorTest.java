package ru.unn.agile.StringCalculator.model;

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
            calc.add("-1,2,3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 not allowed", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithTwoNegativeNumbers() {
        try {
            calc.add("-1,2,-3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -3 not allowed", e.getMessage());
        }
    }

    @Test
    public void canAddStringWithAllNegativeNumbers() {
        try {
            calc.add("-1,-2,-3");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives -1 -2 -3 not allowed", e.getMessage());
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithIncorrectData() {
        calc.add("sadfasdf");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithMistake() {
        calc.add("1,2,3s");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithNull() {
        calc.add(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithDifferentDelimiters() {
        calc.add("1,2;3.4:5");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithoutDelimiterAtBeginning() {
        calc.add("1.2.3.4.5");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithBigNumbers() {
        calc.add("2147483647, 2147483647");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canAddStringWithOneBigNumber() {
        calc.add("2147483648, 1");
    }
}
