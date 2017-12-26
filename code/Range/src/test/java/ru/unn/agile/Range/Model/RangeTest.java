package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class RangeTest {
    @Test
    public void canCreateRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCreateWithLeftBoundMoreRight() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(leftBound, rightBound);
    }

    @Test
    public void canCreateWithLeftEqualRightAndBothIncluded() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(0, true);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCreateWithLeftEqualRightOnlyOneIncluded() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, false);

        Range range = new Range(leftBound, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCreateWithNullLeft() {
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(null, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notCreateWithNullRight() {

        Boundary leftBound = new Boundary(1, true);

        Range range = new Range(leftBound, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWithInvalidArgument() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(leftBound);
    }

    @Test
    public void isTrueRangeEqualsWithEqualRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(6, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Range range2 = new Range(leftBound1, rightBound1);

        assertTrue(range1.equals(range2));
    }

    @Test
    public void isFalseRangeEqualsWithRangeEqualValuesNoEqualInclusions() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(20, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(20, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.equals(range2));
    }

    @Test
    public void isTrueRangeIsContainsValuesInRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void isFalseRangeIsContainsValuesWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-11, 9};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isFalseRangeIsContainsValuesOnlyOneOfWhichWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isFalseRangeIsContainsValuesWithEmptyInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isTrueOneIntPointRangeIsContainsRequiredValues() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void correctGettingAllPointsWithInitBoundaries() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-2, -1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void correctGettingAllPointsWithoutLeftIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void correctGettingAllPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void correctGettingEndPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 1};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void correctGettingEndPointsWithOnePointRange() {
        Boundary leftBound = new Boundary(2, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {2, 2};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void correctGettingEndPointsWithOnePointRangeLeftBoundNoIncluded() {
        Boundary leftBound = new Boundary(2, false);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {3, 3};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void isTrueRangeIsContainsEqualRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsEqualRangeWithIncludedLeftBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsEqualRangeWithIncludedRightBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsEqualRangeWithIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isFalseRangeIsContainsExtendedRange() {
        Boundary leftBound1 = new Boundary(0, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(10, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsContainedRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isFalseRangeIsContainsOnlyOverlappingRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-10, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isFalseNoIncludedBoundRangeIsContainsEqualBoundValueAndLeftIncludedRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, true);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRightIncludedBoundRangeIsContainsEqualBoundValueAndNoIncludedRange() {
        Boundary leftBound1 = new Boundary(-5, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-5, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueBothIncludedRangeIsContainEqualBoundValueAndNoIncludedRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsEqualRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(1, false);
        Boundary rightBound = new Boundary(2, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsContainsContainedRangeWithoutIntPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(16, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isFalseRangeIsContainsNoContainedRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(6, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isTrueRangeIsOverlapsEqualRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(4, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isFalseRangeIsOverlapsNoOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(5, true);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void isTrueRangeIsOverlapsOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(3, false);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isTrueRangeIsOverlapsEqualBoundValueAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isTrueRangeIsOverlapsRangeContainBaseRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(2, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(9, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isFalseRangeIsOverlapsRangeWithLeftValueEqualRightBaseAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void isTrueRangeIsOverlapsRangeWithLeftValueEqualRightBaseAndIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isFalseRangeRightIncludedIsOverlapsRangeWithLeftEqualRightBaseButNoIncluded() {
        Boundary leftBound1 = new Boundary(-10, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(6, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWithNullInput() {
        Boundary leftBound = new Boundary(-19, true);
        Boundary rightBound = new Boundary(20, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainsValuesWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsValues(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainsRangeWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsRange(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsOverlapsRangeWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isOverlapsRange(null);
    }

    @Test
    public void returnedNullInGetAllPointsForRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(10, false);
        Boundary rightBound = new Boundary(11, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getAllPoints());
    }

    @Test
    public void returnedNullInGetEndPointsForRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(-3, false);
        Boundary rightBound = new Boundary(-2, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getEndPoints());
    }
}
