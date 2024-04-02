package org.example.optimizationMethods.lab2;

import org.example.optimizationMethods.CubePoint;
import org.example.optimizationMethods.Point;

public class DoubleDevMethod {
    public static void main(String[] args) {
        Point point = getExtremumPoint(0, 1, 0.001);
        System.out.println(point.getX());
        System.out.println(point.getY());
    }

    private static double getValueOfFunction(double x) {
        return Math.pow(x, 3) - 3 * Math.sin(x);
    }

    private static Point getExtremumPoint(double leftSegmentEnd, double rightSegmentEnd, double epsilon) {
        double endPointDifference;
        CubePoint answerPoint = new CubePoint(0, 0, 0);
        double x1 = (leftSegmentEnd + rightSegmentEnd - epsilon) / 2;
        double x2 = (leftSegmentEnd + rightSegmentEnd + epsilon) / 2;
        double y1 = getValueOfFunction(x1);
        double y2 = getValueOfFunction(x2);

        if (y2 < y1) {
            leftSegmentEnd = x1;
            endPointDifference = rightSegmentEnd - leftSegmentEnd;
        } else {
            rightSegmentEnd = x2;
            endPointDifference = rightSegmentEnd - leftSegmentEnd;
        }
        while (endPointDifference > 2 * epsilon) {
            x1 = (leftSegmentEnd + rightSegmentEnd - epsilon) / 2;
            x2 = (leftSegmentEnd + rightSegmentEnd + epsilon) / 2;
            y1 = getValueOfFunction(x1);
            y2 = getValueOfFunction(x2);
            if (y2 < y1) {
                leftSegmentEnd = x1;
                endPointDifference = rightSegmentEnd - leftSegmentEnd;
            } else {
                rightSegmentEnd = x2;
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
