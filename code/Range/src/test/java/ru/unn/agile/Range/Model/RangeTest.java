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
    public void notCreateWithLeftEqualRightAndOnlyOneIncluded() {
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
    public void isEqualsWithInvalidArgument() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(leftBound);
    }

    @Test
    public void isSameRangeEquals() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(6, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Range range2 = new Range(leftBound1, rightBound1);

        assertTrue(range1.equals(range2));
    }

    @Test
    public void isEqualsWithRangeEqualValuesNoEqualInclusions() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(20, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(20, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.equals(range2));
    }

    @Test
    public void isContainsValuesInsideRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void isContainsValuesWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-11, 9};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isContainsValuesOnlyOneOfWhichWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isContainsValuesWithEmptyInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void isIsContainsRequiredValuesWhenOriginIsOneIntPointRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void gettingAllPointsWithInitBoundaries() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-2, -1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingAllPointsWithoutLeftIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingAllPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingEndPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 1};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void gettingEndPointsOfOnePointRange() {
        Boundary leftBound = new Boundary(2, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {2, 2};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void gettingEndPointsOfOnePointRangeWithoutIncludingLeftBound() {
        Boundary leftBound = new Boundary(2, false);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {3, 3};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void isSameRangeContains() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsEqualRangeWithIncludedLeftBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsEqualRangeWithIncludedRightBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsEqualRangeWithIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsExtendedRange() {
        Boundary leftBound1 = new Boundary(0, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(10, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsContainedRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsOnlyOverlappingRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-10, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isNoIncludedBoundRangeIsContainsEqualBoundValueAndLeftIncludedRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, true);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isRightIncludedBoundRangeIsContainsEqualBoundValueAndNoIncludedRange() {
        Boundary leftBound1 = new Boundary(-5, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-5, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isBothIncludedRangeIsContainEqualBoundValueAndNoIncludedRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsEqualRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(1, false);
        Boundary rightBound = new Boundary(2, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsContainedRangeWithoutIntPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(16, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void isContainsNoContainedRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(6, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void isOverlapsEqualRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(4, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsNoOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(5, true);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(3, false);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsEqualBoundValueAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsRangeContainOriginRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(2, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(9, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsRangeWithLeftValueEqualRightOriginAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void isOverlapsRangeWithLeftValueEqualRightOriginAndIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void isRightIncludedIsNoOverlapsRangeWithLeftEqualRightBaseButNoIncluded() {
        Boundary leftBound1 = new Boundary(-10, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(6, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEqualsWithNullInput() {
        Boundary leftBound = new Boundary(-19, true);
        Boundary rightBound = new Boundary(20, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isContainsValuesWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsValues(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isContainsRangeWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsRange(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isOverlapsRangeWithNullInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isOverlapsRange(null);
    }

    @Test
    public void gettingAllPointsForRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(10, false);
        Boundary rightBound = new Boundary(11, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getAllPoints());
    }

    @Test
    public void gettingEndPointsForRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(-3, false);
        Boundary rightBound = new Boundary(-2, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getEndPoints());
    }
}
