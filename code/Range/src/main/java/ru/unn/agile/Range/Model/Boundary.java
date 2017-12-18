package ru.unn.agile.Range.Model;

import java.util.Objects;

public class Boundary {
    private int value;
    private boolean isIncluded;

    public Boundary(final int value, final boolean isIncluded) {
        this.value = value;
        this.isIncluded = isIncluded;
    }

    public Boundary(final Boundary bound) {
        this.value = bound.value();
        this.isIncluded = bound.isIncluded();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isIncluded);
    }

    @Override
    public boolean equals(final Object object) {
        Boundary bound = (Boundary) object;
        return (value == bound.value() && isIncluded == bound.isIncluded());
    }

    public int value() {
        return this.value;
    }

    public boolean isIncluded() {
        return this.isIncluded;
    }

    public boolean less(final int value) {
        return (this.value <= value && isIncluded)
                || (this.value < value && !isIncluded);
    }

    public boolean more(final int value) {
        return (this.value >= value && isIncluded)
                || (this.value > value && !isIncluded);
    }
}
