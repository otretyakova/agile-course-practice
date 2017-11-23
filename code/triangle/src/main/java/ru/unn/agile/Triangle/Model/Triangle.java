package ru.unn.agile.Triangle.Model;

import java.util.Arrays;

public class Triangle {
    private double[] a;
    private double[] b;
    private double[] c;

    public Triangle(final double[] a, final double[] b, final double[] c) {
        if (a.length != 2 || b.length != 2 || c.length != 2) {
            throw new Error();
        }

        if (isDegenerated(a, b, c)) {
            throw new Error();
        }

        this.a = new double[2];
        this.b = new double[2];
        this.c = new double[2];

        for (int i = 0; i < a.length; ++i) {
            this.a[i] = a[i];
            this.b[i] = b[i];
            this.c[i] = c[i];
        }
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
    private boolean isOnOneLine(final double[] a, final double[] b, final double[] c) {
        double lineCoeffK = (b[1] - a[1]) / (b[0] - a[0]);
        double lineCoeffB = a[1] - lineCoeffK * a[0];

        return c[1] == lineCoeffK * c[0] + lineCoeffB;
    }

    private boolean isDegenerated(final double[] a, final double[] b, final double[] c) {
        if (Arrays.equals(a, b) || Arrays.equals(b, c) || Arrays.equals(c, a)) {
            return true;
        }

        return isOnOneLine(a, b, c);
    }

    private double getLength(final double[] point1, final double[] point2) {
        return Math.sqrt(Math.pow((point1[0] - point2[0]), 2)
                + Math.pow((point1[1] - point2[1]), 2));
    }
}
