package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class RangeTest {
    @Test
    public void rangeCanCreated() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeWithLeftBoundMoreRightCanNotCreated() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(leftBound, rightBound);
    }

    @Test
    public void rangeWithLeftEqualRightAndBothIncludedCanCreated() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(0, true);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeWithLeftEqualRightAndOnlyOneIncludedCanNotCreated() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, false);

        Range range = new Range(leftBound, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeWithNullLeftCanNotCreated() {
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(null, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeWithNullRightCanNotCreated() {

        Boundary leftBound = new Boundary(1, true);

        Range range = new Range(leftBound, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeIsNotEqualsToIncorrectInput() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(leftBound);
    }

    @Test
    public void sameRangesAreEquals() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(6, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Range range2 = new Range(leftBound1, rightBound1);

        assertTrue(range1.equals(range2));
    }

    @Test
    public void rangesWithSameBoundValuesAndDifferentInclusionsAreNotEquals() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(20, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(20, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.equals(range2));
    }

    @Test
    public void rangeIsContainsValuesInsideRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void rangeIsNotContainsValuesWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-11, 9};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void rangeIsContainsValuesOnlyOneOfWhichWithoutRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void rangeIsNotContainsValuesWithEmptyInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void singlePointRangeIsContainsRequiredValues() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void gettingAllPointsOfRangeWithInitBoundaries() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-2, -1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingAllPointsOfRangeWithoutLeftIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingAllPointsOfRangeWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void gettingEndPointsOfRangeWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 1};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void gettingEndPointsOfSinglePointRange() {
        Boundary leftBound = new Boundary(2, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {2, 2};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void gettingEndPointsOfSinglePointRangeWithoutIncludingLeftBound() {
        Boundary leftBound = new Boundary(2, false);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {3, 3};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void rangeWithoutInclusionsIsContainsSameRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithIncludedLeftBoundaryIsContainsSameRange() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithIncludedRightBoundaryIsContainsSameRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithBothIncludedBoundaryIsContainsSameRange() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsNotContainsExtendedRange() {
        Boundary leftBound1 = new Boundary(0, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(10, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsContainsContainedRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsNotContainsOnlyOverlappingRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-10, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithoutInclusionsIsNotContainsRangeWithLeftInclusion() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, true);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithRightInclusionIsContainsRangeWithoutInclusions() {
        Boundary leftBound1 = new Boundary(-5, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-5, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithBothInclusionsIsContainsRangeWithoutInclusions() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithoutIntPointsIsContainsSameRange() {
        Boundary leftBound = new Boundary(1, false);
        Boundary rightBound = new Boundary(2, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsContainsContainedRangeWithoutIntPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(16, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsContainsNotContainedRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(6, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsOverlapsSameRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(4, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsNotOverlapsAnotherNoOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(5, true);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsOverlapsAnotherOverlappedRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(3, false);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsOverlapsSameRangeButWithoutInclusions() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsOverlapsRangeContainOriginRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(2, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(9, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsNotOverlapsRangeWithLeftValueEqualRightOriginAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeIsOverlapsRangeWithLeftValueEqualRightOriginAndIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeWithRInclusionsIsNotOverlapsRangeWithLeftEqualRightOriginButNoIncluded() {
        Boundary leftBound1 = new Boundary(-10, true);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(6, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsEqualsWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(-19, true);
        Boundary rightBound = new Boundary(20, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainsValuesWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsValues(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainsRangeWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsRange(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsOverlapsRangeWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isOverlapsRange(null);
    }

    @Test
    public void gettingAllPointsOfRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(10, false);
        Boundary rightBound = new Boundary(11, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getAllPoints());
    }

    @Test
    public void gettingEndPointsOfRangeWithoutIntPoints() {
        Boundary leftBound = new Boundary(-3, false);
        Boundary rightBound = new Boundary(-2, false);
        Range range = new Range(leftBound, rightBound);

        assertNull(range.getEndPoints());
    }
}
