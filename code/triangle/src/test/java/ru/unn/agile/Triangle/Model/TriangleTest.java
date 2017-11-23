package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void canCreateTriangle2D() {
        double[] a = {0, 0};
        double[] b = {1, 0};
        double[] c = {0, 1};

        Triangle triangle = new Triangle(a, b, c);

        assertNotNull(triangle);
    }

    @Test
    public void canNotCreateTriangle3D() {
        double[] a = {0, 0, 0};
        double[] b = {1, 0, 0};
        double[] c = {0, 1, 0};

        try {
            Triangle triangle = new Triangle(a, b, c);
        } catch (Error e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void canNotCreateDegeneratedTriangle() {
        double[] a = {0, 0};
        double[] b = {0, 0};
        double[] c = {0, 1};

        try {
            Triangle triangle = new Triangle(a, b, c);
        } catch (Error e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void canNotCreateDegeneratedTriangleWithCoordinatesOnOneLine() {
        double[] a = {0, 0};
        double[] b = {-1, -1};
        double[] c = {1, 1};

        try {
            Triangle triangle = new Triangle(a, b, c);
        } catch (Error e) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    public void canGetLengthAB() {
        double[] a = {0, 0};
        double[] b = {1, 0};
        double[] c = {0, 1};

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthABWithNegatedCoordinates() {
        double[] a = {0, 0};
        double[] b = {-1, 0};
        double[] c = {0, 1};

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthABWithDifferentCoordinates() {
        double[] a = {1, 1};
        double[] b = {-1, 1};
        double[] c = {0, 0};

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthBC() {
        double[] a = {0, 0};
        double[] b = {1, 0};
        double[] c = {1, 1};

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthBCWithNegatedCoordinates() {
        double[] a = {0, 1};
        double[] b = {-1, 0};
        double[] c = {1, 0};

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthBCWithDifferentCoordinates() {
        double[] a = {0, 0};
        double[] b = {-1, 1};
        double[] c = {1, 1};

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthAC() {
        double[] a = {0, 1};
        double[] b = {1, 0};
        double[] c = {0, 0};

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetLengthACWithNegatedCoordinates() {
        double[] a = {0, 0};
        double[] b = {0, 1};
        double[] c = {-1, 0};

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetLengthACWithDifferentCoordinates() {
        double[] a = {1, 1};
        double[] b = {0, 0};
        double[] c = {-1, 1};

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetPerimeter() {
        double[] a = {0, 0};
        double[] b = {1, 0};
        double[] c = {0, 1};

        double targetPerimeter = 2 + Math.sqrt(2);

        Triangle triangle = new Triangle(a, b, c);

        double permieter = triangle.getPerimeter();
        assertTrue(permieter == targetPerimeter);
    }

    @Test
    public void canGetPerimeterWithNegativeCoordinates() {
        double[] a = {0, 0};
        double[] b = {-1, -1};
        double[] c = {0, 1};

        double targetPerimeter = 1 + Math.sqrt(2) + Math.sqrt(5);

        Triangle triangle = new Triangle(a, b, c);

        double perimeter = triangle.getPerimeter();
        assertTrue(perimeter == targetPerimeter);
    }

    @Test
    public void canGetPerimeterWithDifferentCoordinates() {
        double[] a = {1, 1};
        double[] b = {-1, -1};
        double[] c = {0, -2};

        double targetPerimeter = Math.sqrt(8) + Math.sqrt(2) + Math.sqrt(10);

        Triangle triangle = new Triangle(a, b, c);

        double perimeter = triangle.getPerimeter();
        assertTrue(perimeter == targetPerimeter);
    }

    @Test
    public void canGetSurfaceArea() {
        double[] a = {0, 0};
        double[] b = {1, 0};
        double[] c = {0, 1};

        double targetSurfaceArea = 0.5;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetSurfaceAreaOfIsoscelesTriangle() {
        double[] a = {0, 0};
        double[] b = {2, 0};
        double[] c = {1, 1};

        double targetSurfaceArea = 1;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetSurfaceAreaOfVersatileTriangle() {
        double[] a = {0, 0};
        double[] b = {2, 0};
        double[] c = {0.5, 1};

        double targetSurfaceArea = 1;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetABCAngleOfRectangularTriangle() {
        double[] a = {0, 1};
        double[] b = {0, 0};
        double[] c = {1, 0};

        double targetABCAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetABCAsObtuseAngle() {
        double[] a = {1, 0};
        double[] b = {0, 0};
        double[] c = {-1, 1};

        double targetABCAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetABCAsAcuteAngle() {
        double[] a = {1, 0};
        double[] b = {0, 0};
        double[] c = {1, 1};

        double targetABCAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetBCAAngleOfRectangularTriangle() {
        double[] b = {0, 1};
        double[] c = {0, 0};
        double[] a = {1, 0};

        double targetBCAAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetBCAAsObtuseAngle() {
        double[] b = {-1, 1};
        double[] c = {0, 0};
        double[] a = {1, 0};

        double targetBCAAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetBCAAsAcuteAngle() {
        double[] b = {1, 1};
        double[] c = {0, 0};
        double[] a = {1, 0};

        double targetBCAAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetCABAngleOfRectangularTriangle() {
        double[] c = {0, 1};
        double[] a = {0, 0};
        double[] b = {1, 0};

        double targetCABAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }

    @Test
    public void canGetCABAsObtuseAngle() {
        double[] c = {-1, 1};
        double[] a = {0, 0};
        double[] b = {1, 0};

        double targetCABAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }

    @Test
    public void canGetCABAsAcuteAngle() {
        double[] c = {1, 1};
        double[] a = {0, 0};
        double[] b = {1, 0};

        double targetCABAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }
}
