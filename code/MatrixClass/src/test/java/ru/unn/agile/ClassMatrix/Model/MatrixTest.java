package ru.unn.agile.ClassMatrix.Model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void canNotCreateNullMatrix() {
        try {
            Matrix testMatrix = new Matrix(null);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void canCreateSquareMatrix() {
        float[][] array = {{1, 2},
                {3, 5}
        };

        Matrix testMatrix = new Matrix(array);
        assertTrue(testMatrix.getNumberOfColumns() == testMatrix.getNumberOfRows());
    }

    @Test
    public void canCreateMatrixAsArray() {
        float[][] array = {{1, 2},
                {3, 5, 3},
                {5}
        };

        try {
            Matrix testMatrix = new Matrix(array);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void canFillMatrix2x2() {
        Matrix testMatrix = new Matrix(2, 2);

        testMatrix.set(0, 0, 2);
        testMatrix.set(0, 1, 3);
        testMatrix.set(1, 0, 3);
        testMatrix.set(1, 1, 5);

        float[][] array = {{2, 3},
                {3, 5}
        };

        assertArrayEquals(array, testMatrix.toArray());
    }

    @Test
    public void canFillMatrix2x3() {
        Matrix testMatrix = new Matrix(2, 3);

        testMatrix.set(0, 0, 2);
        testMatrix.set(0, 1, 3);
        testMatrix.set(0, 2, 6);
        testMatrix.set(1, 0, 3);
        testMatrix.set(1, 1, 5);
        testMatrix.set(1, 2, 2);

        float[][] array = {{2, 3, 6},
                {3, 5, 2}
        };

        assertArrayEquals(array, testMatrix.toArray());
    }

    @Test
    public void canCreateSquareMatrix2x2AsArraySuccessful() {
        float[][] array = {{1.3f, 2.3f},
                {3f, 5f}
        };

        Matrix testMatrix = new Matrix(array);
        assertArrayEquals(array, testMatrix.toArray());
    }

    @Test
    public void isSquareMatrixDeterminedAsSquare() {
        float[][] array = {{1f, 2f},
                {3f, 5f}
        };

        Matrix testMatrix = new Matrix(array);
        assertTrue(testMatrix.isSquareMatrix());
    }


    @Test
    public void isNotSquareMatrixDeterminedAsNotSquare() {
        float[][] array = {{1f, 2f, 3f},
                {3f, 5f, 9f}
        };

        Matrix testMatrix = new Matrix(array);
        assertTrue(!testMatrix.isSquareMatrix());
    }

    @Test
    public void canCreateSquareMatrixAsArray3x3Successful() {
        float[][] array = {{1f, 2f, 3f},
                {3f, 5f, 4f},
                {1f, 5f, 4f}
        };

        Matrix testMatrix = new Matrix(array);
        assertArrayEquals(array, testMatrix.toArray());
    }

    @Test
    public void canCreateMatrixAsArraySuccessful() {
        float[][] arrayForCreateMatrix = {{1f, 2f},
                {3f, 5f},
                {5f, 5f}
        };

        float[][] arrayForComparison = {{1f, 2f},
                {3f, 5f},
                {5f, 5f}
        };

        Matrix testMatrix = new Matrix(arrayForComparison);
        assertArrayEquals(testMatrix.toArray(), arrayForCreateMatrix);
    }

    @Test
    public void canCalculateDeterminantForMatrix1x1() {
        float[][] array = {{777f}};

        Matrix testMatrix = new Matrix(array);
        assertEquals(777f, testMatrix.calculateDeterminant(), 1.0e-4);
    }

    @Test
    public void canCalculateDeteriminantForSquareMatrix2x2Expects18() {
        float[][] array = {{6.1f, 4.5f},
                {3.7f, 5.2f}
        };

        Matrix testMatrix = new Matrix(array);
        assertEquals(15.07f, testMatrix.calculateDeterminant(), 1.0e-4);
    }

    @Test
    public void canNotCalculateDeterminantOfANonSquareMatrix() {
        float[][] array = {{6.2f, 4.1f},
                {3.1f, 5.3f},
                {6.4f, 5.2f}
        };

        Matrix testMatrix = new Matrix(array);
        assertTrue(testMatrix.calculateDeterminant() == 0);
    }

    @Test
    public void canCalculateDeteriminantForMatrix3x3Expects1() {
        float[][] array = {{1f, 0f, 1f},
                {0f, 1f, 0f},
                {0f, 0f, 1f},
        };

        Matrix testMatrix = new Matrix(array);
        assertEquals(1f, testMatrix.calculateDeterminant(), 1.0e-4);
    }

    @Test
    public void canCalculateDeteriminantForMatrix3x3Expects0() {
        float[][] array = {{1f, 2f, 4f},
                {1f, 1f, 1f},
                {1f, 1f, 1f},
        };

        Matrix testMatrix = new Matrix(array);
        assertEquals(0f, testMatrix.calculateDeterminant(), 1.0e-4);
    }
}
