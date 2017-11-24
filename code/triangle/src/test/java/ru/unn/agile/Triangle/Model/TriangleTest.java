package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import java.awt.geom.Point2D;
import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void canCreateTriangle2D() {
        Point2D a  = new Point2D.Double(0, 0);
        Point2D b  = new Point2D.Double(1, 0);
        Point2D c  = new Point2D.Double(0, 1);

        Triangle triangle = new Triangle(a, b, c);

        assertNotNull(triangle);
    }

    @Test
    public void canNotCreateDegeneratedTriangle() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(0, 1);

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
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(-1, -1);
        Point2D c = new Point2D.Double(1, 1);

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
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);
        Point2D c = new Point2D.Double(0, 1);

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthABWithNegatedCoordinates() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(0, 1);
        Point2D b = new Point2D.Double(-1, 0);

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthABWithDifferentCoordinates() {
        Point2D a = new Point2D.Double(1, 1);
        Point2D b = new Point2D.Double(-1, 1);
        Point2D c = new Point2D.Double(0, 0);

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAB = triangle.getLengthAB();
        assertTrue(lengthAB == targetLength);
    }

    @Test
    public void canGetLengthBC() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);
        Point2D c = new Point2D.Double(1, 1);

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthBCWithNegatedCoordinates() {
        Point2D a = new Point2D.Double(0, 1);
        Point2D b = new Point2D.Double(-1, 0);
        Point2D c = new Point2D.Double(1, 0);

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthBCWithDifferentCoordinates() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(-1, 1);
        Point2D c = new Point2D.Double(1, 1);

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthBC = triangle.getLengthBC();
        assertTrue(lengthBC == targetLength);
    }

    @Test
    public void canGetLengthAC() {
        Point2D a = new Point2D.Double(0, 1);
        Point2D b = new Point2D.Double(1, 0);
        Point2D c = new Point2D.Double(0, 0);

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetLengthACWithNegatedCoordinates() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(0, 1);
        Point2D c = new Point2D.Double(-1, 0);

        double targetLength = 1;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetLengthACWithDifferentCoordinates() {
        Point2D a = new Point2D.Double(1, 1);
        Point2D b = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(-1, 1);

        double targetLength = 2;

        Triangle triangle = new Triangle(a, b, c);

        double lengthAC = triangle.getLengthAC();
        assertTrue(lengthAC == targetLength);
    }

    @Test
    public void canGetPerimeter() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);
        Point2D c = new Point2D.Double(0, 1);

        double targetPerimeter = 2 + Math.sqrt(2);

        Triangle triangle = new Triangle(a, b, c);

        double permieter = triangle.getPerimeter();
        assertTrue(permieter == targetPerimeter);
    }

    @Test
    public void canGetPerimeterWithNegativeCoordinates() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(-1, -1);
        Point2D c = new Point2D.Double(0, 1);

        double targetPerimeter = 1 + Math.sqrt(2) + Math.sqrt(5);

        Triangle triangle = new Triangle(a, b, c);

        double perimeter = triangle.getPerimeter();
        assertTrue(perimeter == targetPerimeter);
    }

    @Test
    public void canGetPerimeterWithDifferentCoordinates() {
        Point2D a = new Point2D.Double(1, 1);
        Point2D b = new Point2D.Double(-1, -1);
        Point2D c = new Point2D.Double(0, -2);

        double targetPerimeter = Math.sqrt(8) + Math.sqrt(2) + Math.sqrt(10);

        Triangle triangle = new Triangle(a, b, c);

        double perimeter = triangle.getPerimeter();
        assertTrue(perimeter == targetPerimeter);
    }

    @Test
    public void canGetSurfaceArea() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);
        Point2D c = new Point2D.Double(0, 1);

        double targetSurfaceArea = 0.5;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetSurfaceAreaOfIsoscelesTriangle() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(2, 0);
        Point2D c = new Point2D.Double(1, 1);

        double targetSurfaceArea = 1;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetSurfaceAreaOfVersatileTriangle() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(2, 0);
        Point2D c = new Point2D.Double(0.5, 1);

        double targetSurfaceArea = 1;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double surfaceArea = triangle.getSurfaceArea();
        assertTrue(Math.abs(surfaceArea - targetSurfaceArea) < precision);
    }

    @Test
    public void canGetABCAngleOfRectangularTriangle() {
        Point2D a = new Point2D.Double(0, 1);
        Point2D b = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(1, 0);

        double targetABCAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetABCAsObtuseAngle() {
        Point2D a = new Point2D.Double(1, 0);
        Point2D b = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(-1, 1);

        double targetABCAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetABCAsAcuteAngle() {
        Point2D a = new Point2D.Double(1, 0);
        Point2D b = new Point2D.Double(0, 0);
        Point2D c = new Point2D.Double(1, 1);

        double targetABCAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getABCAngle();
        assertTrue(Math.abs(angle - targetABCAngle) < precision);
    }

    @Test
    public void canGetBCAAngleOfRectangularTriangle() {
        Point2D b = new Point2D.Double(0, 1);
        Point2D c = new Point2D.Double(0, 0);
        Point2D a = new Point2D.Double(1, 0);

        double targetBCAAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetBCAAsObtuseAngle() {
        Point2D b = new Point2D.Double(-1, 1);
        Point2D c = new Point2D.Double(0, 0);
        Point2D a = new Point2D.Double(1, 0);

        double targetBCAAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetBCAAsAcuteAngle() {
        Point2D b = new Point2D.Double(1, 1);
        Point2D c = new Point2D.Double(0, 0);
        Point2D a = new Point2D.Double(1, 0);

        double targetBCAAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getBCAAngle();
        assertTrue(Math.abs(angle - targetBCAAngle) < precision);
    }

    @Test
    public void canGetCABAngleOfRectangularTriangle() {
        Point2D c = new Point2D.Double(0, 1);
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);

        double targetCABAngle = 90 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }

    @Test
    public void canGetCABAsObtuseAngle() {
        Point2D c = new Point2D.Double(-1, 1);
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);

        double targetCABAngle = 135 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }

    @Test
    public void canGetCABAsAcuteAngle() {
        Point2D c = new Point2D.Double(1, 1);
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 0);

        double targetCABAngle = 45 * Math.PI / 180;

        double precision = 1e-15;

        Triangle triangle = new Triangle(a, b, c);

        double angle = triangle.getCABAngle();
        assertTrue(Math.abs(angle - targetCABAngle) < precision);
    }
}
