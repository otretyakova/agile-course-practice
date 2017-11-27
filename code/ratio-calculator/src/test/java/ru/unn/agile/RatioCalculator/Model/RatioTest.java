package ru.unn.agile.RatioCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RatioTest {
    @Test
    public void canCreateRatioWithoutInitialValues() {
        Ratio ratio = new Ratio();

        assertEquals(ratio.getNumerator(), 0);
    }

    @Test
    public void canCreateRatioWithNumeratorAndDenominator() {
        Ratio ratio = new Ratio(1, 2);

        assertEquals(ratio.getDenominator(), 2);
    }

    @Test
    public void cantCreateRatioWithZeroDenominator() {
        boolean divisionByZeroCatched = false;
        try {
            Ratio ratio = new Ratio(1, 0);
        } catch (ArithmeticException e) {
            divisionByZeroCatched = true;
        }

        assertTrue(divisionByZeroCatched);
    }


    @Test
    public void equalRatiosAreEqual() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(2, 4);

        assertTrue(ratio1.isEqual(ratio2));
    }

    @Test
    public void notEqualRatiosAreNotEqual() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(2, 5);

        assertFalse(ratio1.isEqual(ratio2));
    }

    @Test
    public void canCreateRatioFromWholeNumber() {
        Ratio ratio = new Ratio(5);
        Ratio expected = new Ratio(5, 1);

        assertTrue(ratio.isEqual(expected));
    }

    @Test
    public void ratioWhichPresentsWholeNumberEqualsToThisNumber() {
        Ratio ratio = new Ratio(5);

        assertTrue(ratio.isEqual(5));
    }

    @Test
    public void whenRatioCreatedItMustBeIrreducable() {
        Ratio ratio = new Ratio(2, 4);
        Ratio expected = new Ratio(1, 2);

        assertTrue(ratio.isEqual(expected));
    }

    @Test
    public void whenRatioDenominatorsAreEqualTheLessRatioHasTheLessNumerator() {
        Ratio ratio1 = new Ratio(2, 5);
        Ratio ratio2 = new Ratio(3, 5);

        assertTrue(ratio1.isLess(ratio2));
    }

    @Test
    public void whenRatioNumeratorsAreEqualTheLessRatioHasTheBiggerDenominator() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(1, 3);

        assertTrue(ratio2.isLess(ratio1));
    }

    @Test
    public void canCompareRatiosWithoutEqualsNumeratorsOrDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 8);

        assertTrue(ratio1.isLess(ratio2));
    }

    @Test
    public void negativeRatioMustHaveNegativeNumeratorNotDenominator() {
        Ratio ratio1 = new Ratio(-1, 2);
        Ratio ratio2 = new Ratio(1, -2);

        assertTrue(ratio1.isEqual(ratio2));
    }

    @Test
    public void negativeRatioMustBeIrreducableAfterCreating() {
        Ratio ratio1 = new Ratio(6, -9);
        Ratio ratio2 = new Ratio(-2, 3);

        assertTrue(ratio1.isEqual(ratio2));
    }

    @Test
    public void theWholeRatioHasEqualWholePart() {
        Ratio ratio = new Ratio(10);

        assertEquals(ratio.getWholePart(), 10);
    }

    @Test
    public void whenRatioNumeratorIsLessThenDenominatorItHasZeroWholePart() {
        Ratio ratio = new Ratio(1, 6);

        assertEquals(ratio.getWholePart(), 0);
    }

    @Test
    public void canCalcWholePartOfRatioWhichHasBiggerNumerator() {
        Ratio ratio = new Ratio(37, 6);

        assertEquals(ratio.getWholePart(), 6);
    }

    @Test
    public void canCalcWholePartOfNegativeRatio() {
        Ratio ratio = new Ratio(-37, 6);

        assertEquals(ratio.getWholePart(), -6);
    }

    @Test
    public void canCalcSumOfRatioAndZeroRatio() {
        Ratio ratio1 = new Ratio(37, 6);
        Ratio ratio2 = new Ratio(0);

        Ratio got = ratio1.add(ratio2);

        assertTrue(got.isEqual(ratio1));
    }

    @Test
    public void canCalcSumOfRatiosWithCommonDenominators() {
        Ratio ratio1 = new Ratio(2, 3);
        Ratio ratio2 = new Ratio(4, 3);
        Ratio expected = new Ratio(2);

        Ratio got = ratio1.add(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcSumOfRatiosWithoutCommonDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(11, 12);

        Ratio got = ratio1.add(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canNegateRatio() {
        Ratio ratio = new Ratio(1, 6);
        Ratio expected = new Ratio(-1, 6);

        Ratio got = ratio.negate();

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcSubtractOfRatioAndZeroRatio() {
        Ratio ratio1 = new Ratio(37, 6);
        Ratio ratio2 = new Ratio(0);

        Ratio got = ratio1.sub(ratio2);

        assertTrue(got.isEqual(ratio1));
    }

    @Test
    public void canCalcSubOfRatiosWithCommonDenominators() {
        Ratio ratio1 = new Ratio(2, 3);
        Ratio ratio2 = new Ratio(4, 3);
        Ratio expected = new Ratio(-2, 3);

        Ratio got = ratio1.sub(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcSubOfRatiosWithoutCommonDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(-7, 12);

        Ratio got = ratio1.sub(ratio2);

        assertTrue(got.isEqual(expected));
    }

   @Test
   public void canCalcMultiplyOfRatioAndZeroRatio() {
       Ratio ratio1 = new Ratio(5, 6);
       Ratio ratio2 = new Ratio(0);
       Ratio expected = new Ratio(0);

       Ratio got = ratio1.mult(ratio2);

       assertTrue(got.isEqual(expected));
   }

    @Test
    public void canCalcMultiplyOfRatioAndWholeNumberRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3);
        Ratio expected = new Ratio(15, 6);

        Ratio got = ratio1.mult(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcMultiplyOfNormalRatios() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(5, 8);

        Ratio got = ratio1.mult(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canReverseRatio() {
        Ratio ratio = new Ratio(5, 6);
        Ratio expected = new Ratio(6, 5);

        Ratio got = ratio.reverse();

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void cantReverseZeroRatio() {
        Ratio ratio = new Ratio(0);

        boolean divisionByZeroCatched = false;
        try {
            Ratio got = ratio.reverse();
        } catch (ArithmeticException e) {
            divisionByZeroCatched = true;
        }

        assertTrue(divisionByZeroCatched);
    }

    @Test
    public void cantCalcDivisionOfRatioAndZeroRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(0);

        boolean divisionByZeroCatched = false;
        try {
            Ratio got = ratio1.div(ratio2);
        } catch (ArithmeticException e) {
            divisionByZeroCatched = true;
        }

        assertTrue(divisionByZeroCatched);
    }

    @Test
    public void canCalcDivisionOfRatioAndWholeNumberRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3);
        Ratio expected = new Ratio(5, 18);

        Ratio got = ratio1.div(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcDivisionOfNormalRatios() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(10, 9);

        Ratio got = ratio1.div(ratio2);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcSumOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(17, 3);

        Ratio got = ratio.add(number);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcSubOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(-13, 3);

        Ratio got = ratio.sub(number);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcMultiplyOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(10, 3);

        Ratio got = ratio.mult(number);

        assertTrue(got.isEqual(expected));
    }

    @Test
    public void canCalcDivisionOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(2, 15);

        Ratio got = ratio.div(number);

        assertTrue(got.isEqual(expected));
    }

}
