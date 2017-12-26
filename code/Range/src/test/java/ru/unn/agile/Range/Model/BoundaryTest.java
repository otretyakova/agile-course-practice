package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoundaryTest {
    @Test
    public void canCreateBoundWithInitValues() {
        Boundary bound = new Boundary(1, true);

        assertNotNull(bound);
    }

    @Test
    public void canCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertNotNull(copyBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWithInvalidInput() {
        Boundary bound = new Boundary(1, true);
        Object o = new Object();
        bound.equals(o);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWithNullInput() {
        Boundary bound = new Boundary(10, false);

        bound.equals(null);
    }

    @Test
    public void correctValueAtCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(1, copyBound.value());
    }

    @Test
    public void correctIsInclludedAtCreateCopyBoundary() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(true, copyBound.isIncluded());
    }

    @Test
    public void isTrueBoundaryEqualsEqualBoundary() {
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
    public void isTrueBoundaryLessValue() {
        Boundary bound = new Boundary(1, true);

        assertTrue(bound.less(2));
    }

    @Test
    public void isFalseBoundaryNoLessValue() {
        Boundary bound = new Boundary(3, true);

        assertFalse(bound.less(2));
    }

    @Test
    public void isTrueBoundaryMoreValue() {
        Boundary bound = new Boundary(3, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void isFalseBoundaryNoMoreValue() {
        Boundary bound = new Boundary(1, true);

        assertFalse(bound.more(2));
    }

    @Test
    public void isTrueBoundaryMoreValueWithIncludedValue() {
        Boundary bound = new Boundary(2, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void isFalseBoundaryMoreValueWithoutIncludedValue() {
        Boundary bound = new Boundary(2, false);

        assertFalse(bound.more(2));
    }

    @Test
    public void isTrueBoundaryLessValueWithIncludedValue() {
        Boundary bound = new Boundary(5, true);

        assertTrue(bound.less(5));
    }

    @Test
    public void isFalseBoundaryLessValueWithoutIncludedValue() {
        Boundary bound = new Boundary(3, false);

        assertFalse(bound.less(3));
    }
}
