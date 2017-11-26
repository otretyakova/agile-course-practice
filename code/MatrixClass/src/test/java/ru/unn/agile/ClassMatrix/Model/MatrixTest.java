package ru.unn.agile.ClassMatrix.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    private Matrix someIntMatrix;

    @Test
    public void whenIfMatrixInt2x2Quadriq() {
        int[][]arrayForCreateIntMatrix2x2 = {{1, 2, },
                                               {3, 5 }
        };

        someIntMatrix = new Matrix(arrayForCreateIntMatrix2x2);

        assertTrue(someIntMatrix.matrixIsQuadric());
    }

    @Test
    public void whenIfMatrixInt3x2IsNotQuadriq() {
        int[][]arrayForCreateIntMatrix3x2 = {{1, 2, },
                                               {3, 5, },
                                               {1, 5 }
        };

        someIntMatrix = new Matrix(arrayForCreateIntMatrix3x2);

        assertFalse(someIntMatrix.matrixIsQuadric());
    }

    @Test
    public void initiliseMatrix2x2WhenSomeIntNumbers() {
        int[][]expectedSomeTestMatrix2x2 = {{1, 2, },
                                           {3, 5 }
                                                 };

        int[][]arrayForCreateIntMatrix2x2 = {{1, 2, },
                                               {3, 5 }
                                                        };

        someIntMatrix = new Matrix(arrayForCreateIntMatrix2x2);
        int[][]getSomeIntMatric = someIntMatrix.getMatrix();

        assertArrayEquals(expectedSomeTestMatrix2x2, getSomeIntMatric);
    }

    @Test
    public void initiliseMatrix3x3WhenSomeIntNumbers() {
        int[][]expectedSomeTestMatrix3x3 = {{1, 2, 3 },
                                           {3, 5, 4 },
                                           {1, 5, 4 }
        };

        int[][]arrayForCreateIntMatrix3x3 = {{1, 2, 3 },
                                               {3, 5, 4 },
                                               {1, 5, 4 }
        };

        someIntMatrix = new Matrix(arrayForCreateIntMatrix3x3);
        int[][]getSomeIntMatric = someIntMatrix.getMatrix();

        assertArrayEquals(expectedSomeTestMatrix3x3, getSomeIntMatric);
    }

    @Test
    public void initiliseMatrix3x2WhenSomeIntNumbers() {
        int[][]expectedSomeTestMatrix3x2 = {{1, 2, },
                                           {3, 5, },
                                           {1, 5 }
        };

        int[][]arrayForCreateIntMatrix3x2 = {{1, 2, },
                                               {3, 5, },
                                               {1, 5 }
        };

        someIntMatrix = new Matrix(arrayForCreateIntMatrix3x2);
        int[][]getSomeIntMatric = someIntMatrix.getMatrix();

        assertArrayEquals(expectedSomeTestMatrix3x2, getSomeIntMatric);
    }

    @Test
    public void canCalculateDeterminantIntMatrix2x2WithResult18() {
        int[][]someTestMatrix2x2 = {{6, 4, },
                                       {3, 5 }
                                             };

        someIntMatrix = new Matrix(someTestMatrix2x2);

        int calculateIntMatrixDeterminant = someIntMatrix.determinant(
                someIntMatrix.getMatrix(),
                someIntMatrix.getColumns());

        int expectedDeterminantResult = 18;

        assertEquals(expectedDeterminantResult, calculateIntMatrixDeterminant);
    }

    @Test
    public void areTrueWhenCalculateDeterminantIntMatrix3x2() {
        int[][]someTestMatrix3x2 = {{6, 4, },
                                      {3, 5, },
                                      {6, 5 }
                                              };

        someIntMatrix = new Matrix(someTestMatrix3x2);

        int calculateIntMatrixDeterminant = someIntMatrix.determinant(
                someIntMatrix.getMatrix(),
                someIntMatrix.getColumns());

        assertTrue(calculateIntMatrixDeterminant == 0);
    }

    @Test
    public void canCalculateDeterminantIntMatrix3x3WithResult1() {
        int[][]someTestMatrix3x3 = {{1, 0, 1, },
                                      {0, 1, 0, },
                                      {0, 0, 1},
        };

        someIntMatrix = new Matrix(someTestMatrix3x3);

        int calculateIntMatrixDeterminant = someIntMatrix.determinant(
                someIntMatrix.getMatrix(),
                someIntMatrix.getColumns());

        int expectedDeterminantResult = 1;

        assertEquals(expectedDeterminantResult, calculateIntMatrixDeterminant);
    }

}
