package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundaryTest {
    @Test
    public void canCreateBoundWithInitValues() {
        Boundary bound = new Boundary(1, true);

        assertNotNull(bound);
    }

    @Test
    public void canGetValueOfBoundary() {
        Boundary bound = new Boundary(1, true);

        assertEquals(1, bound.value());
    }

    @Test
    public void canGetIsIncludedOfBoundary() {
        Boundary bound = new Boundary(1, true);

        assertEquals(true, bound.isIncluded());
    }

    @Test
    public void canCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertNotNull(copyBound);
    }

    @Test
    public void areEqualValuesAtCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(1, copyBound.value());
    }

    @Test
    public void areEqualIncludedAtCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(true, copyBound.isIncluded());
    }

    @Test
    public void areEqualBoundaryEqual() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(1, true);

        assertTrue(bound1.equals(bound2));
    }

    @Test
    public void notEqualDifferentBoundary() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(2, true);

        assertFalse(bound1.equals(bound2));
    }

    @Test
    public void areTrueBoundaryLessValue() {
        Boundary bound = new Boundary(1, true);

        assertTrue(bound.less(2));
    }

    @Test
    public void areFalseBoundaryNoLessValue() {
        Boundary bound = new Boundary(3, true);

        assertFalse(bound.less(2));
    }

    @Test
    public void areTrueBoundaryMoreValue() {
        Boundary bound = new Boundary(3, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void areFalseBoundaryNoMoreValue() {
        Boundary bound = new Boundary(1, true);

        assertFalse(bound.more(2));
    }

    @Test
    public void areTrueBoundaryMoreValueWithIncludedValue() {
        Boundary bound = new Boundary(2, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void areFalseBoundaryMoreValueWithoutIncludedValue() {
        Boundary bound = new Boundary(2, false);

        assertFalse(bound.more(2));
    }

    @Test
    public void areTrueBoundaryLessValueWithIncludedValue() {
        Boundary bound = new Boundary(5, true);

        assertTrue(bound.less(5));
    }

    @Test
    public void areFalseBoundaryLessValueWithoutIncludedValue() {
        Boundary bound = new Boundary(3, false);

        assertFalse(bound.less(3));
    }
}
