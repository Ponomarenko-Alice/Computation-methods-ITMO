package org.example.optimizationMethods.lab2;

import org.example.optimizationMethods.CubePoint;
import org.example.optimizationMethods.Point;

public class NewtonMethod {
    public static void main(String[] args) {
        Point point = getExtremumPoint(0, 1, 0.001);
        System.out.println(point.getX());
        System.out.println(point.getY());
    }

    private static double getValueOfFunction(double x) {
        return Math.pow(x, 3) - 3 * Math.sin(x);
    }

    private static double getDerivativeOfFunction(double x) {
        return 3 * Math.pow(x, 2) - 3 * Math.cos(x);
    }

    private static double getDoubleDerivativeOfFunction(double x) {
        return 6 * x + 3 * Math.sin(x);
    }


    public NewtonMethod() {
    }

    private static Point getExtremumPoint(double leftSegmentEnd, double rightSegmentEnd, double epsilon) {
        double endPointDifference;
        CubePoint answerPoint = new CubePoint(0, 0, 0);
        double x = (leftSegmentEnd + rightSegmentEnd) / 2;
        double x1 = x - (getDerivativeOfFunction(x) / getDoubleDerivativeOfFunction(x));
        endPointDifference = Math.abs(x - x1);
        while (endPointDifference > 2 * epsilon) {
            x = x1;
            x1 = x - (getDerivativeOfFunction(x) / getDoubleDerivativeOfFunction(x));
            endPointDifference = Math.abs(x - x1);
        }
        double resultY = getValueOfFunction(x1);
        answerPoint.setX(x1);
        answerPoint.setY(resultY);
        return answerPoint;
    }

}
