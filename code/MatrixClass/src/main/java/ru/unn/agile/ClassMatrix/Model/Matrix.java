package ru.unn.agile.ClassMatrix.Model;

public class Matrix {

    public Matrix(final int[][] array) throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("Can't create matrix from null!");
        }
        sizeOfRows = array.length;
        sizeOfColumns = array[0].length;
        for (int i = 1; i < sizeOfRows; i++) {
            if (array[i].length != sizeOfColumns) {
                throw new IllegalArgumentException("Column has different number!");
            }
        }

        matrixData = array.clone();
    }

    public int[][] toArray() {
        return matrixData;
    }

    public boolean isQuadric() {
        return sizeOfRows == sizeOfColumns;
    }

    public int calculateDeterminant() {
        int determinant = 0;
        if (isQuadric()) {
            determinant = computeDeterminant(matrixData, sizeOfRows);
        }
        return determinant;
    }

    public int getNumberOfRows() {
        return sizeOfRows;
    }

    public int getNumberOfColumns() {
        return sizeOfColumns;
    }

    private Matrix getSubMatrix(final int excludedRow, final int excludedColumn) {

        int row = 0;
        int col = 0;
        int size = sizeOfRows;
        int[][] calculateArray = new int[size - 1][size - 1];

        for (int i = 0; i < size - 1; i++) {
            if (i == excludedRow) {
                row = 1;
            }
            col = 0;
            for (int j = 0; j < size - 1; j++) {
                if (j == excludedColumn) {
                    col = 1;
                }
                calculateArray[i][j] = matrixData[i + row][j + col];
            }
        }

        return new Matrix(calculateArray);
    }

    private int computeDeterminant(final int[][] array, final int size) {
        int determinant = 0;
        int signMinor = 1;

        if (size == 1) {
            return array[0][0];
        }

        if (size == 2) {
            int mainDiagonal = array[0][0] * array[1][1];
            int sideDiagonal = array[0][1] * array[1][0];
            return mainDiagonal - sideDiagonal;
        }

        if (size > 2) {

            Matrix subMatrix;
            for (int j = 0; j < size; j++) {
                subMatrix = getSubMatrix(0, j);
                int ratio = array[0][j];
                int resultEndPart = 0;
                resultEndPart = signMinor * ratio * computeDeterminant(subMatrix.toArray(),
                        size - 1);
                determinant = determinant + resultEndPart;
                signMinor = -signMinor;
            }

        }

        return determinant;
    }

    private int[][] matrixData;
    private int sizeOfRows;
    private int sizeOfColumns;
}
