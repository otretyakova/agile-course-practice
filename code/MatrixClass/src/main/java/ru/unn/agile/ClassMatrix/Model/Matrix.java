package ru.unn.agile.ClassMatrix.Model;

public class Matrix {

    private int[][]newMatrix;
    private int n;
    private int m;

    public Matrix(final int[][]dataMassive) {
        this.n = dataMassive.length;
        this.m = dataMassive[0].length;
        newMatrix = new int[n][m];
        newMatrix = dataMassive.clone();
    }

    public int[][] getMatrix() {
        return newMatrix;
    }

    public boolean matrixIsQuadric() {
        return n == m;
    }

   private void getMatrixWithoutRowAndCol(final int[][]matrix,
                                          final int size,
                                          final int row,
                                          final int col,
                                          final int[][]newMatrix) {
       int offsetRow = 0;
       int offsetCol = 0;
       for (int i = 0; i < size - 1; i++) {
           if (i == row) {
               offsetRow = 1;
           }
           offsetCol = 0;
           for (int j = 0; j < size - 1; j++) {
               if (j == col) {
                   offsetCol = 1;
               }
               newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
           }
       }
   }

    public int determinant(final int[][]forDeterminantMatrix, final int size) {
        int  result, degree;
        result = 0;
        degree = 1;
            if (matrixIsQuadric()) {
                if (size == 1) {
                    result = forDeterminantMatrix[0][0];
                    return result;
                }

                if (size == 2) {
                    int a1 = forDeterminantMatrix[0][0] * forDeterminantMatrix[1][1];
                    int a2 = forDeterminantMatrix[0][1] * forDeterminantMatrix[1][0];
                    result =  a1 - a2;
                    return result;
                }

                if (n > 2) {
                    int[][] p = new int[n - 1][n - 1];
                    for (int j = 0; j < size; j++) {
                        getMatrixWithoutRowAndCol(forDeterminantMatrix, size, 0, j, p);
                        int a1j = forDeterminantMatrix[0][j];
                        int resultEndPart = degree * a1j * determinant(p, size - 1);
                        result = result + (resultEndPart);
                        degree = -degree;
                    }
                }

            } else {
                return 0;
            }
        return result;
    }

    public int getColumns() {
        return n;
    }

    public int getRows() {
        return m;
    }
}
