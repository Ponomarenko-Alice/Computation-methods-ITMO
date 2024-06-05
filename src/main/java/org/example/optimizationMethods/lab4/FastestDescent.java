package org.example.optimizationMethods.lab4;

import org.example.optimizationMethods.CubePoint;

public class FastestDescent {
    private static final double epsilon = 0.001;

    private static double getValueOfFunction(double a, double b, double c) {
        return 2 * a * a + b * b * b + c * c - a * b + 2 * a * c - b;
    }

    private static double getADerivativeFunc(double a, double b, double c) {
        return 4 * a - b + 2 * c;
    }

    private static double getBDerivativeFunc(double a, double b, double c) {
        return 3 * b * b - a - 1;
    }

    private static double getCDerivativeFunc(double a, double b, double c) {
        return 2 * c + 2 * a;
    }

    private static CubePoint getGradient(double a, double b, double c) {
        return new CubePoint(getADerivativeFunc(a, b, c),
                getBDerivativeFunc(a, b, c),
                getCDerivativeFunc(a, b, c));
    }

    private static double findOptimalStep(CubePoint point, CubePoint gradient) {
        double a0 = point.getX();
        double b0 = point.getY();
        double c0 = point.getZ();
        double a1 = gradient.getX();
        double b1 = gradient.getY();
        double c1 = gradient.getZ();
        return -(2 * a1 * a1 + 6 * b1 * b1 * b0 + 2 * c1 * c1 - 3 * a1 * b1 + 2 * a1 * c1 + 2 * c1 * c1) / 6 * b1 * b1 * b1;

    }

    public static void main(String[] args) {
        CubePoint point = new CubePoint(-0.2, 0, 0);
        double functionValue = getValueOfFunction(point.getX(), point.getY(), point.getZ());

        while (true) {
            CubePoint gradient = getGradient(point.getX(), point.getY(), point.getZ());

            double optimalStep = findOptimalStep(point, gradient);
            point.setX(point.getX() - optimalStep * gradient.getX());
            point.setY(point.getY() - optimalStep * gradient.getY());
            point.setZ(point.getZ() - optimalStep * gradient.getZ());

            double newFunctionValue = getValueOfFunction(point.getX(), point.getY(), point.getZ());
            if (Math.abs(newFunctionValue - functionValue) < epsilon) {
                break;
            }

            functionValue = newFunctionValue;
        }

        System.out.println("Optimal function value: " + functionValue);
        System.out.println("Optimal point: (" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")");
    }
}
