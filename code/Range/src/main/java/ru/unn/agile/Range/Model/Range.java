package ru.unn.agile.Range.Model;

import java.util.Objects;

public class Range {
    private int numberOfIntPoints;
    private boolean isEmpty = false;
    private Boundary leftBound;
    private Boundary rightBound;

    public Range(final Boundary left, final Boundary right) {
        this.leftBound = new Boundary(left);
        this.rightBound = new Boundary(right);
        this.setIsEmpty();
        this.setNumberOfIntPoints();
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfIntPoints, isEmpty, leftBound, rightBound);
    }

    @Override
    public boolean equals(final Object object) {
        Range range = (Range) object;
        return leftBound.equals(range.leftBound) && rightBound.equals(range.rightBound);
    }

    public int[] getAllPoints() {
        int[] result = new int[numberOfIntPoints];
        int firstValue = (leftBound.isIncluded()) ? leftBound.value() : leftBound.value() + 1;
        for (int index = 0; index < numberOfIntPoints; index++) {
            result[index] = firstValue + index;
        }
        return result;
    }

    public int[] getEndPoints() {
        int[] result;
        if (numberOfIntPoints == 0) {
            return new int[0];
        } else {
            result = new int[2];
        }

        result[0] = leftBound.isIncluded() ? leftBound.value() : (leftBound.value() + 1);
        result[1] = rightBound.isIncluded() ? rightBound.value() : (rightBound.value() - 1);

        return result;
    }

    public boolean isContainsValues(final int[] values) {
        boolean result = (values.length > 0);
        for (int value : values) {
            if (!(leftBound.less(value) && rightBound.more(value))) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isContainsRange(final Range range) {
        if (range.isEmpty) {
            return false;
        }
        if (equals(range)) {
            return true;
        }
        int[] endPoints = range.getEndPoints();
        if (endPoints.length == 0) {
            return leftBound.less(range.leftBound.value())
                    && rightBound.more(range.rightBound.value());
        } else {
            return leftBound.less(endPoints[0]) && rightBound.more(endPoints[1]);
        }
    }

    public boolean isOverlapsRange(final Range range) {
        if (range.isEmpty || isEmpty) {
            return false;
        }
        boolean isInputLeftBoundInThisRange = leftBound.less(range.leftBound.value())
                && rightBound.more(range.leftBound.value());
        boolean isInputRightBoundInThisRange = leftBound.less(range.rightBound.value())
                && rightBound.more(range.rightBound.value());
        boolean isThisLeftBoundInInputRange = range.leftBound.less(leftBound.value())
                && range.rightBound.more(leftBound.value());
        boolean isThisRightBoundInThisRange = range.leftBound.less(rightBound.value())
                && range.rightBound.more(rightBound.value());

        return isInputLeftBoundInThisRange
                || isInputRightBoundInThisRange
                || isThisLeftBoundInInputRange
                || isThisRightBoundInThisRange;

    }

    private void setIsEmpty() {
        boolean isRightLessLeft = (rightBound.value() < leftBound.value());
        boolean isRightEqualLeft = rightBound.value() == leftBound.value();
        boolean isSomeBoundNoIncluded = (!leftBound.isIncluded() || !rightBound.isIncluded());
        if (isRightLessLeft || isRightEqualLeft && isSomeBoundNoIncluded) {
            this.isEmpty = true;
        }
    }

    private void setNumberOfIntPoints() {
        this.numberOfIntPoints = rightBound.value() - leftBound.value() + 1;
        if (!leftBound.isIncluded()) {
            this.numberOfIntPoints--;
        }
        if (!rightBound.isIncluded()) {
            this.numberOfIntPoints--;
        }
        this.numberOfIntPoints = numberOfIntPoints < 0 ? 0 : this.numberOfIntPoints;
    }
}
