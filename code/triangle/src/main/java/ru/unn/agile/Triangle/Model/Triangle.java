package ru.unn.agile.Triangle.Model;

import java.awt.geom.Point2D;

public class Triangle {
    private Point2D a;
    private Point2D b;
    private Point2D c;

    public Triangle(final Point2D a, final Point2D b, final Point2D c) {
        if (isDegenerated(a, b, c)) {
            throw new Error();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return getLengthAB() + getLengthBC() + getLengthAC();
    }

    public double getLengthAB() {
        return getLength(a, b);
    }

    public double getLengthBC() {
        return getLength(b, c);
    }

    public double getLengthAC() {
        return getLength(a, c);
    }

    public double getSurfaceArea() {
        double p = getSemiperimeter();

        double ab = getLengthAB();
        double bc = getLengthBC();
        double ac = getLengthAC();

        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
    }

    public double getABCAngle() {
        double ab = getLengthAB();
        double bc = getLengthBC();
        double ac = getLengthAC();

        return Math.acos((bc * bc + ab * ab - ac * ac) / (2 * bc * ab));
    }

    public double getBCAAngle() {
        return Math.PI - (getABCAngle() + getCABAngle());
    }

    public double getCABAngle() {
        double ab = getLengthAB();
        double bc = getLengthBC();
        double ac = getLengthAC();

        return Math.acos((ac * ac + ab * ab - bc * bc) / (2 * ac * ab));
    }

    private double getSemiperimeter() {
        return getPerimeter() / 2;
    }
    private boolean isOnOneLine(final Point2D a, final Point2D b, final Point2D c) {
        double lineCoeffK = (b.getY() - a.getY()) / (b.getX() - a.getX());
        double lineCoeffB = a.getY() - lineCoeffK * a.getX();

        return c.getY() == lineCoeffK * c.getX() + lineCoeffB;
    }

    private boolean isDegenerated(final Point2D a, final Point2D b, final Point2D c) {

        if (a.equals(b) || b.equals(c) || c.equals(a)) {
            return true;
        }

        return isOnOneLine(a, b, c);
    }

    private double getLength(final Point2D point1, final Point2D point2) {
        return Math.sqrt(Math.pow((point1.getX() - point2.getX()), 2)
                + Math.pow((point1.getY() - point2.getY()), 2));
    }
}
