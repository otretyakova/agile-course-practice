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

    @Test(expected = ArithmeticException.class)
    public void cantCreateRatioWithZeroDenominator() {
        Ratio ratio = new Ratio(1, 0);
    }

    @Test
    public void equalRatiosAreEqual() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(2, 4);

        assertEquals(ratio1, ratio2);
    }

    @Test
    public void notEqualRatiosAreNotEqual() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(2, 5);

        assertNotEquals(ratio1, ratio2);
    }

    @Test
    public void ratiosWithDefferentSignsAreNotEqual() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(-1, 2);

        assertNotEquals(ratio1, ratio2);
    }

    @Test
    public void equalNegativeRatiosAreEqual() {
        Ratio ratio1 = new Ratio(1, -2);
        Ratio ratio2 = new Ratio(-1, 2);

        assertEquals(ratio1, ratio2);
    }

    @Test
    public void whenZeroRatiosAreCreatedInDifferentWaysTheyAreStillEqual() {
        Ratio ratio1 = new Ratio(0, -2);
        Ratio ratio2 = new Ratio(0);

        assertEquals(ratio1, ratio2);
    }

    @Test
    public void zeroRatioEqualsZeroNumber() {
        Ratio ratio = new Ratio(0);

        assertEquals(ratio, 0);
    }

    @Test
    public void canCreateRatioFromWholeNumber() {
        Ratio ratio = new Ratio(5);
        Ratio expected = new Ratio(5, 1);

        assertEquals(ratio, expected);
    }

    @Test
    public void ratioWhichPresentsWholeNumberEqualsToThisNumber() {
        Ratio ratio = new Ratio(5);

        assertEquals(ratio, 5);
    }

    @Test
    public void whenRatioCreatedItMustBeIrreducable() {
        Ratio ratio = new Ratio(2, 4);
        Ratio expected = new Ratio(1, 2);

        assertEquals(ratio, expected);
    }

    @Test
    public void whenRatioDenominatorsAreEqualTheLessRatioHasTheLessNumerator() {
        Ratio ratio1 = new Ratio(2, 5);
        Ratio ratio2 = new Ratio(3, 5);

        assertTrue(ratio1.compareTo(ratio2) < 0);
    }

    @Test
    public void whenRatioNumeratorsAreEqualTheLessRatioHasTheBiggerDenominator() {
        Ratio ratio1 = new Ratio(1, 2);
        Ratio ratio2 = new Ratio(1, 3);

        assertTrue(ratio1.compareTo(ratio2) > 0);
    }

    @Test
    public void canCompareRatiosWithoutEqualsNumeratorsOrDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 8);

        assertTrue(ratio1.compareTo(ratio2) < 0);
    }

    @Test
    public void positiveRatioIsBiggerThenNegative() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(-5, 8);

        assertTrue(ratio1.compareTo(ratio2) > 0);
    }

    @Test
    public void canCompareNegativeRatios() {
        Ratio ratio1 = new Ratio(-1, -6);
        Ratio ratio2 = new Ratio(-5, 8);

        assertTrue(ratio1.compareTo(ratio2) > 0);
    }

    @Test
    public void negativeRatiosHaveOneRepresentation() {
        Ratio ratio1 = new Ratio(-1, 2);
        Ratio ratio2 = new Ratio(1, -2);

        assertEquals(ratio1, ratio2);
    }

    @Test
    public void negativeRatioMustHaveNegativeNumeratorNotDenominator() {
        Ratio ratio = new Ratio(1, -2);

        assertTrue((ratio.getDenominator() > 0) && (ratio.getNumerator() < 0));
    }

    @Test
    public void ratioWithNegativeNumeratorAndDenominatorIsPositive() {
        Ratio ratio1 = new Ratio(-1, -2);
        Ratio ratio2 = new Ratio(1, 2);

        assertEquals(ratio1, ratio2);
    }

    @Test
    public void ratioWithZeroNumeratorMustEqualsOne() {
        Ratio ratio = new Ratio(0, 2);

        assertEquals(ratio.getDenominator(), 1);
    }

    @Test
    public void negativeRatioMustBeIrreducableAfterCreating() {
        Ratio ratio1 = new Ratio(6, -9);
        Ratio ratio2 = new Ratio(-2, 3);

        assertEquals(ratio1, ratio2);
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
    public void whenDenominatorDividesNumeratorResultOfDivisionIsTheWholePartOfRatio() {
        Ratio ratio = new Ratio(36, 6);

        assertEquals(ratio.getWholePart(), 6);
    }

    @Test
    public void theWholePartOfZeroIsZero() {
        Ratio ratio = new Ratio(0);

        assertEquals(ratio.getWholePart(), 0);
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

        assertEquals(got, ratio1);
    }

    @Test
    public void canCalcSumOfRatiosWithCommonDenominators() {
        Ratio ratio1 = new Ratio(2, 3);
        Ratio ratio2 = new Ratio(4, 3);
        Ratio expected = new Ratio(2);

        Ratio got = ratio1.add(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSumOfRatiosWithoutCommonDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(11, 12);

        Ratio got = ratio1.add(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSumOfPositiveAndNegativeRatio() {
        Ratio ratio1 = new Ratio(1, 8);
        Ratio ratio2 = new Ratio(-1, 4);
        Ratio expected = new Ratio(-1, 8);

        Ratio got = ratio1.add(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSumOfNegativesRatios() {
        Ratio ratio1 = new Ratio(-1, 8);
        Ratio ratio2 = new Ratio(-1, 4);
        Ratio expected = new Ratio(-3, 8);

        Ratio got = ratio1.add(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canNegateRatio() {
        Ratio ratio = new Ratio(1, 6);
        Ratio expected = new Ratio(-1, 6);

        Ratio got = ratio.negate();

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSubtractOfRatioAndZeroRatio() {
        Ratio ratio1 = new Ratio(37, 6);
        Ratio ratio2 = new Ratio(0);

        Ratio got = ratio1.sub(ratio2);

        assertEquals(got, ratio1);
    }

    @Test
    public void canCalcSubOfRatiosWithCommonDenominators() {
        Ratio ratio1 = new Ratio(2, 3);
        Ratio ratio2 = new Ratio(4, 3);
        Ratio expected = new Ratio(-2, 3);

        Ratio got = ratio1.sub(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSubOfRatiosWithoutCommonDenominators() {
        Ratio ratio1 = new Ratio(1, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(-7, 12);

        Ratio got = ratio1.sub(ratio2);

        assertEquals(got, expected);
    }

   @Test
   public void canCalcMultiplyOfRatioAndZeroRatio() {
       Ratio ratio1 = new Ratio(5, 6);
       Ratio ratio2 = new Ratio(0);
       Ratio expected = new Ratio(0);

       Ratio got = ratio1.mult(ratio2);

       assertEquals(got, expected);
   }

    @Test
    public void canCalcMultiplyOfRatioAndWholeNumberRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3);
        Ratio expected = new Ratio(15, 6);

        Ratio got = ratio1.mult(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcMultiplyOfNormalRatios() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(5, 8);

        Ratio got = ratio1.mult(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcMultOfPositiveAndNegativeRatio() {
        Ratio ratio1 = new Ratio(1, 8);
        Ratio ratio2 = new Ratio(-5, 4);
        Ratio expected = new Ratio(-5, 32);

        Ratio got = ratio1.mult(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcMultOfNegativesRatios() {
        Ratio ratio1 = new Ratio(-1, 8);
        Ratio ratio2 = new Ratio(-5, 4);
        Ratio expected = new Ratio(5, 32);

        Ratio got = ratio1.mult(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canReverseRatio() {
        Ratio ratio = new Ratio(5, 6);
        Ratio expected = new Ratio(6, 5);

        Ratio got = ratio.reverse();

        assertEquals(got, expected);
    }

    @Test(expected = ArithmeticException.class)
    public void cantReverseZeroRatio() {
        Ratio ratio = new Ratio(0);

        Ratio got = ratio.reverse();
    }

    @Test(expected = ArithmeticException.class)
    public void cantCalcDivisionOfRatioAndZeroRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(0);

        Ratio got = ratio1.div(ratio2);
    }

    @Test
    public void canCalcDivisionOfRatioAndWholeNumberRatio() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3);
        Ratio expected = new Ratio(5, 18);

        Ratio got = ratio1.div(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcDivisionOfNormalRatios() {
        Ratio ratio1 = new Ratio(5, 6);
        Ratio ratio2 = new Ratio(3, 4);
        Ratio expected = new Ratio(10, 9);

        Ratio got = ratio1.div(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcDivisionOfPositiveAndNegativeRatio() {
        Ratio ratio1 = new Ratio(1, 8);
        Ratio ratio2 = new Ratio(-5, 4);
        Ratio expected = new Ratio(-1, 10);

        Ratio got = ratio1.div(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcDivisionOfNegativesRatios() {
        Ratio ratio1 = new Ratio(-1, 8);
        Ratio ratio2 = new Ratio(-5, 4);
        Ratio expected = new Ratio(1, 10);

        Ratio got = ratio1.div(ratio2);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSumOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(17, 3);

        Ratio got = ratio.add(number);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcSubOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(-13, 3);

        Ratio got = ratio.sub(number);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcMultiplyOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(10, 3);

        Ratio got = ratio.mult(number);

        assertEquals(got, expected);
    }

    @Test
    public void canCalcDivisionOfRatioAndInteger() {
        Ratio ratio = new Ratio(2, 3);
        int number = 5;
        Ratio expected = new Ratio(2, 15);

        Ratio got = ratio.div(number);

        assertEquals(got, expected);
    }

    @Test
    public void ratiosWithBigDenominatorsAndNumeratorsComparesCorrectly() {
        Ratio ratio1 = new Ratio(Integer.MAX_VALUE, 2);
        Ratio ratio2 = new Ratio(Integer.MAX_VALUE, 1);

        assertTrue(ratio1.compareTo(ratio2) < 0);
    }

    @Test(expected = Ratio.IntegerOverflowException.class)
    public void whenSumOfRatiosHasTooBigNumeratorExceptionThrows() {
        Ratio ratio1 = new Ratio(Integer.MAX_VALUE, 2);
        Ratio ratio2 = new Ratio(Integer.MAX_VALUE / 2 + 1, 1);

        Ratio got = ratio1.add(ratio2);
    }

    @Test(expected = Ratio.IntegerOverflowException.class)
    public void whenMultOfRatiosHasTooBigNumeratorExceptionThrows() {
        Ratio ratio1 = new Ratio(Integer.MAX_VALUE, 2);
        Ratio ratio2 = new Ratio(2, 5);

        Ratio got = ratio1.mult(ratio2);
    }

    @Test(expected = Ratio.IntegerOverflowException.class)
    public void whenMultOfRatiosHasTooBigDenominatorExceptionThrows() {
        Ratio ratio1 = new Ratio(1, Integer.MAX_VALUE);
        Ratio ratio2 = new Ratio(2, 5);

        Ratio got = ratio1.mult(ratio2);
    }

    @Test(expected = Ratio.IntegerOverflowException.class)
    public void cantFindSumOfRatiosWithTooBigCommonDenominator() {
        Ratio ratio1 = new Ratio(1, Integer.MAX_VALUE);
        Ratio ratio2 = new Ratio(2, 3);

        Ratio got = ratio1.add(ratio2);
    }

}
