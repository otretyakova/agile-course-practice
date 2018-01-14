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
    public void rangeCanNotCreatedWhenLeftBoundMoreRight() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(leftBound, rightBound);
    }

    @Test
    public void rangeCanCreatedWhenLeftEqualRightAndBothIncluded() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(0, true);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeCanNotCreatedWhenLeftEqualRightAndOnlyOneIncluded() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, false);

        Range range = new Range(leftBound, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeCanNotCreatedWhenLeftBoundIsNull() {
        Boundary rightBound = new Boundary(-1, true);

        Range range = new Range(null, rightBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeCanNotCreatedWhenRightBoundIsNull() {

        Boundary leftBound = new Boundary(1, true);

        Range range = new Range(leftBound, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWhenArgumentIsIncorrect() {
        Boundary leftBound = new Boundary(0, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(leftBound);
    }

    @Test
    public void sameRangesAreEqual() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(6, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Range range2 = new Range(leftBound1, rightBound1);

        assertTrue(range1.equals(range2));
    }

    @Test
    public void rangesWithSameBoundValuesAndDifferentInclusionsAreNotEqual() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(20, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(20, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.equals(range2));
    }

    @Test
    public void rangeIsContainValuesInsideRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void rangeIsNotContainValuesOutsideRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-11, 9};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void rangeIsContainValuesOnlyOneOfWhichOutsideRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void rangeIsNotContainValuesWithEmptyInput() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void singlePointRangeIsContainRequiredValues() {
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
    public void rangeWithoutInclusionsIsContainSameRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithIncludedLeftBoundaryIsContainSameRange() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithIncludedRightBoundaryIsContainSameRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithBothIncludedBoundaryIsContainSameRange() {
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
    public void rangeIsContainContainedRange() {
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
    public void rangeWithRightInclusionIsContainRangeWithoutInclusions() {
        Boundary leftBound1 = new Boundary(-5, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-5, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithBothInclusionsIsContainRangeWithoutInclusions() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeWithoutIntPointsIsContainSameRange() {
        Boundary leftBound = new Boundary(1, false);
        Boundary rightBound = new Boundary(2, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsContainContainedRangeWithoutIntPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(16, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void rangeIsNotContainNotContainedRange() {
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
    public void rangeIsOverlapsRangeWithLeftValueEqualRightOriginAndIsIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(5, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void rangeWithInclusionsIsNotOverlapsRangeWithLeftEqualRightOriginAndIsNotIncluded() {
        Boundary leftBound1 = new Boundary(-10, true);
        Boundary rightBound1 = new Boundary(0, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(6, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(-19, true);
        Boundary rightBound = new Boundary(20, true);
        Range range = new Range(leftBound, rightBound);

        range.equals(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainValuesWhenArgumentIsNull() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);

        range.isContainsValues(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInIsContainRangeWhenArgumentIsNull() {
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
