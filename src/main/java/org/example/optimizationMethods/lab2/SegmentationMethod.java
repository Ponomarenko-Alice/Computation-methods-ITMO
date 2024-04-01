package org.example.optimizationMethods.lab2;

import org.example.optimizationMethods.Point;

public class SegmentationMethod {
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

    private static Point getExtremumPoint(double leftSegmentEnd, double rightSegmentEnd, double epsilon) {
        double endPointDifference;
        Point answerPoint = new Point();
        double x = leftSegmentEnd - ((leftSegmentEnd - rightSegmentEnd) * getDerivativeOfFunction(leftSegmentEnd) / (getDerivativeOfFunction(leftSegmentEnd) - getDerivativeOfFunction(rightSegmentEnd)));
        double derX = getDerivativeOfFunction(x);
        double derA = getDerivativeOfFunction(leftSegmentEnd);
        if (derA * derX < 0) {
            leftSegmentEnd = x;
            endPointDifference = rightSegmentEnd - leftSegmentEnd;
        } else {
            rightSegmentEnd = x;
            endPointDifference = rightSegmentEnd - leftSegmentEnd;
        }
        while (endPointDifference > 2 * epsilon) {
            x = leftSegmentEnd - ((leftSegmentEnd - rightSegmentEnd) * getDerivativeOfFunction(leftSegmentEnd) / (getDerivativeOfFunction(leftSegmentEnd) - getDerivativeOfFunction(rightSegmentEnd)));
            derX = getDerivativeOfFunction(x);
            derA = getDerivativeOfFunction(leftSegmentEnd);
            if (derA * derX < 0) {
                leftSegmentEnd = x;
                endPointDifference = rightSegmentEnd - leftSegmentEnd;
            } else {
                rightSegmentEnd = x;
                endPointDifference = rightSegmentEnd - leftSegmentEnd;
            }
        }
        double resultX = (leftSegmentEnd + rightSegmentEnd) / 2;
        double resultY = getValueOfFunction(resultX);
        answerPoint.setX(resultX);
        answerPoint.setY(resultY);
        return answerPoint;
    }

}





