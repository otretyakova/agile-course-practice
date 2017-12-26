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
    public void canCreateCopyBoundaryWithInitValues() {
        Boundary bound = new Boundary(1, true);
        Boundary copyBound = new Boundary(bound);

        assertEquals(1, copyBound.value());
        assertEquals(true, copyBound.isIncluded());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEqualsWithInvalidInput() {
        Boundary bound = new Boundary(1, true);
        Object o = new Object();
        bound.equals(o);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEqualsWithNullInput() {
        Boundary bound = new Boundary(10, false);

        bound.equals(null);
    }

    @Test
    public void isSameBoundaryEquals() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(1, true);

        assertTrue(bound1.equals(bound2));
    }

    @Test
    public void notEqualsDifferentBoundary() {
        Boundary bound1 = new Boundary(1, true);
        Boundary bound2 = new Boundary(2, true);

        assertFalse(bound1.equals(bound2));
    }

    @Test
    public void isLessValue() {
        Boundary bound = new Boundary(1, true);

        assertTrue(bound.less(2));
    }

    @Test
    public void isNoLessValue() {
        Boundary bound = new Boundary(3, true);

        assertFalse(bound.less(2));
    }

    @Test
    public void isMoreValue() {
        Boundary bound = new Boundary(3, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void isNoMoreValue() {
        Boundary bound = new Boundary(1, true);

        assertFalse(bound.more(2));
    }

    @Test
    public void isMoreValueWithIncludedValue() {
        Boundary bound = new Boundary(2, true);

        assertTrue(bound.more(2));
    }

    @Test
    public void isMoreValueWithoutIncludedValue() {
        Boundary bound = new Boundary(2, false);

        assertFalse(bound.more(2));
    }

    @Test
    public void isLessValueWithIncludedValue() {
        Boundary bound = new Boundary(5, true);

        assertTrue(bound.less(5));
    }

    @Test
    public void isNoLessValueWithoutIncludedValue() {
        Boundary bound = new Boundary(3, false);

        assertFalse(bound.less(3));
    }
}
