package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTest {
    @Test
    public void canCreateRangeWithInitialValues() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);

        Range range = new Range(leftBound, rightBound);

        assertNotNull(range);
    }

    @Test
    public void areTrueEqualsRangeWithEqualRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(6, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Range range2 = new Range(leftBound1, rightBound1);

        assertTrue(range1.equals(range2));
    }

    @Test
    public void areFalseEqualsRangeWithNoEqualRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(20, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(20, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.equals(range2));
    }

    @Test
    public void areTrueRangeIsContainsValuesWithValuesInRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void areFalseRangeIsContainsValuesWithoutValuesInRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 9};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void areFalseRangeIsContainsValuesWithoutOneOfValuesInRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1, 2, 3};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void areFalseRangeIsContainsValuesWithEmptyValues() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(3, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void areFalseRangeIsContainsValuesWithEmptyRange() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(-1, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1};

        assertFalse(range.isContainsValues(values));
    }

    @Test
    public void areTrueRangeIsContainsValuesWithOnePointRangeEqualsValues() {
        Boundary leftBound = new Boundary(1, true);
        Boundary rightBound = new Boundary(1, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {1};

        assertTrue(range.isContainsValues(values));
    }

    @Test
    public void areEqualGetAllPointsWithInitBoundaries() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-2, -1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void areEqualGetAllPointsWithoutLeftIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3, 4};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void areEqualGetAllPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(4, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 0, 1, 2, 3};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void areEqualGetAllPointsWithEmptyRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(-2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertArrayEquals(values, range.getAllPoints());
    }

    @Test
    public void areEqualGetEndPointsWithoutIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {-1, 1};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void areEqualGetEndPointsWithEmptyRange() {
        Boundary leftBound = new Boundary(2, false);
        Boundary rightBound = new Boundary(-2, false);
        Range range = new Range(leftBound, rightBound);
        int[] values = {};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void areEqualGetEndPointsWithOnePointRange() {
        Boundary leftBound = new Boundary(2, true);
        Boundary rightBound = new Boundary(2, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {2, 2};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void areEqualGetEndPointsWithOnePointRangeLeftBoundNoIncluded() {
        Boundary leftBound = new Boundary(2, false);
        Boundary rightBound = new Boundary(3, true);
        Range range = new Range(leftBound, rightBound);
        int[] values = {3, 3};

        assertArrayEquals(values, range.getEndPoints());
    }

    @Test
    public void areTrueIsContainsRangeEqualRange() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeEqualRangeWithIncludedLeftBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeEqualRangeWithIncludedRightBoundary() {
        Boundary leftBound = new Boundary(-2, false);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeEqualRangeWithIncludedBoundary() {
        Boundary leftBound = new Boundary(-2, true);
        Boundary rightBound = new Boundary(5, true);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeWithExtendedInputRange() {
        Boundary leftBound1 = new Boundary(0, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(10, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeWithContainedInputRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeWithOverlapsInputRange() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-10, false);
        Boundary rightBound2 = new Boundary(2, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeNoIncludedBoundWithEqualBoundValueAndLeftIncluded() {
        Boundary leftBound1 = new Boundary(-1, false);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, true);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeLeftIncludedBoundWithEqualBoundValueAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-5, false);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-5, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeBothIncludedBoundWithEqualBoundValueAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeWithEqualRangeWithoutPoints() {
        Boundary leftBound = new Boundary(1, false);
        Boundary rightBound = new Boundary(2, false);
        Range range1 = new Range(leftBound, rightBound);
        Range range2 = new Range(leftBound, rightBound);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsContainsRangeWithContainedRangeWithoutPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(16, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeWithNoContainedRangeWithoutPoints() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(6, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(10, false);
        Boundary rightBound2 = new Boundary(11, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeEmptyRangeWithNoEmptyRange() {
        Boundary leftBound1 = new Boundary(1, true);
        Boundary rightBound1 = new Boundary(1, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(1, true);
        Boundary rightBound2 = new Boundary(1, true);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeWithEmptyRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(1, false);
        Boundary rightBound2 = new Boundary(1, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areFalseIsContainsRangeEmptyRangeWithEmptyRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(0, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(0, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isContainsRange(range2));
    }

    @Test
    public void areTrueIsOverlapsRangeWithEqualNoEmptyRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, true);
        Boundary rightBound2 = new Boundary(4, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void areFalseIsOverlapsRangeWithNoOverlappedNoEmptyRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(5, true);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void areTrueIsOverlapsRangeWithOverlappedNoEmptyRange() {
        Boundary leftBound1 = new Boundary(0, true);
        Boundary rightBound1 = new Boundary(4, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(3, false);
        Boundary rightBound2 = new Boundary(6, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void areTrueIsOverlapsRangeLeftIncludedBoundWithEqualBoundValueAndNoIncluded() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-1, false);
        Boundary rightBound2 = new Boundary(3, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }

    @Test
    public void areFalseIsOverlapsRangeWithEmptyRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(3, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(0, false);
        Boundary rightBound2 = new Boundary(0, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void areFalseIsOverlapsRangeEmptyRangeWithNoEmptyRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(-1, false);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(9, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertFalse(range1.isOverlapsRange(range2));
    }

    @Test
    public void areTrueIsOverlapsRangeContainedInNoEmptyRangeWithNoEmptyRange() {
        Boundary leftBound1 = new Boundary(-1, true);
        Boundary rightBound1 = new Boundary(2, true);
        Range range1 = new Range(leftBound1, rightBound1);
        Boundary leftBound2 = new Boundary(-2, false);
        Boundary rightBound2 = new Boundary(9, false);
        Range range2 = new Range(leftBound2, rightBound2);

        assertTrue(range1.isOverlapsRange(range2));
    }
}
