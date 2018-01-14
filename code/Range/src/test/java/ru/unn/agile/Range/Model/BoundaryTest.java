package ru.unn.agile.Range.Model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoundaryTest {
    @Test
    public void boundaryCanCreated() {
        Boundary bound = new Boundary(1, true);

        assertNotNull(bound);
    }

    @Test
    public void boundaryCopyCanCreated() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(1, copyBound.value());
        assertEquals(true, copyBound.isIncluded());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWhenArgumentIsIncorrect() {
        Boundary bound = new Boundary(1, true);
        Object o = new Object();
        bound.equals(o);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionInEqualsWhenArgumentIsNull() {
        Boundary bound = new Boundary(10, false);

        bound.equals(null);
    }

    @Test
    public void sameBoundariesAreEqual() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(1, true);

        assertTrue(bound1.equals(bound2));
    }

    @Test
    public void differentBoundariesAreNotEqual() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(2, true);

        assertFalse(bound1.equals(bound2));
    }

    @Test
    public void boundaryIsLessValue() {
        Boundary bound = new Boundary(1, true);

        assertTrue(bound.less(2));
    }

    @Test
    public void boundaryIsNotLessValue() {
        Boundary bound = new Boundary(3, true);

        assertFalse(bound.less(2));
    }

    @Test
    public void boundaryIsMoreValue() {
        Boundary bound = new Boundary(3, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void boundaryIsNotMoreValue() {
        Boundary bound = new Boundary(1, true);

        assertFalse(bound.more(2));
    }

    @Test
    public void boundaryWithIncludedValueIsMoreValue() {
        Boundary bound = new Boundary(2, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void boundaryWithoutIncludedValueIsNotMoreValue() {
        Boundary bound = new Boundary(2, false);

        assertFalse(bound.more(2));
    }

    @Test
    public void boundaryWithIncludedValueIsLessValue() {
        Boundary bound = new Boundary(5, true);

        assertTrue(bound.less(5));
    }

    @Test
    public void boundaryWithoutIncludedValueIsNotLessValue() {
        Boundary bound = new Boundary(3, false);

        assertFalse(bound.less(3));
    }
}
