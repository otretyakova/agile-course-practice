package ru.unn.agile.ClassMatrix.Model;

public class Matrix<T> {

    public Matrix(final float[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("Can't create matrix from null!");
        }
        numberOfRows = array.length;
        numberOfColumns = array[0].length;
        for (int i = 1; i < numberOfRows; i++) {
            if (array[i].length != numberOfColumns) {
                throw new IllegalArgumentException("There are rows with diffrent sizes in array!");
            }
        }

        data = array.clone();
    }

    public Matrix(final int numberOfRows, final int numberOfColumns) {
        if (numberOfRows == 0 || numberOfColumns == 0) {
            throw new IllegalArgumentException("The number of rows or columns is zero!");
        }

        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        data = new float[numberOfRows][numberOfColumns];
    }

    public float get(final int indexOfRow, final int indexOfColumn) {
        if (indexOfRow >= this.numberOfRows || indexOfColumn >= this.numberOfColumns) {
            throw new IllegalArgumentException("Invalid index!");
        }

        return this.data[indexOfRow][indexOfColumn];
    }

    public void set(final int indexOfRow, final int indexOfColumn, final float data) {
        if (indexOfRow >= this.numberOfRows || indexOfColumn >= this.numberOfColumns) {
            throw new IllegalArgumentException("Invalid index!");
        }

        this.data[indexOfRow][indexOfColumn] = data;
    }

    public float[][] toArray() {
        return data.clone();
    }

    public boolean isSquareMatrix() {
        return numberOfRows == numberOfColumns;
    }

    public float calculateDeterminant() {
        if (isSquareMatrix()) {
            return computeDeterminant();
        }
        return 0;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    private float getMinor(final int excludedRow, final int excludedColumn) {
        int indexRowOffset = 0;
        int indexColumnOffset = 0;
        Matrix subMatrix = new Matrix(numberOfRows - 1, numberOfRows - 1);

        for (int i = 0; i < numberOfRows - 1; i++) {
            if (i == excludedRow) {
                indexRowOffset = 1;
            }
            indexColumnOffset = 0;
            for (int j = 0; j < numberOfRows - 1; j++) {
                if (j == excludedColumn) {
                    indexColumnOffset = 1;
                }
                subMatrix.set(i, j, get(i + indexRowOffset, j + indexColumnOffset));
            }
        }

        return subMatrix.computeDeterminant();
    }

    private float computeDeterminant() {
        int determinant = 0;
        if (numberOfRows == 1) {
            return data[0][0];
        }

        if (numberOfRows == 2) {
            final float mainDiagonal = data[0][0] * data[1][1];
            final float sideDiagonal = data[0][1] * data[1][0];
            return mainDiagonal - sideDiagonal;
        }

        if (numberOfRows > 2) {
            for (int j = 0; j < numberOfRows; j++) {
                float coefficient = data[0][j];
                int minorSign = (j % 2 == 0) ? 1 : -1;
                determinant += coefficient * minorSign * getMinor(0, j);
            }
        }

        return determinant;
    }

    private float[][] data;
    private int numberOfRows;
    private int numberOfColumns;
}
