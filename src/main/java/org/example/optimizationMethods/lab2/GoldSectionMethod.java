package org.example.optimizationMethods.lab2;

import org.example.optimizationMethods.CubePoint;
import org.example.optimizationMethods.Point;

public class GoldSectionMethod {
    public static void main(String[] args) {
        Point point = getExtremumPoint(0, 1, 0.001);
        System.out.println(point.getX());
        System.out.println(point.getY());
    }

    private static double getValueOfFunction(double x) {
        return Math.pow(x, 3) - 3 * Math.sin(x);
    }

    private static Point getExtremumPoint(double leftSegmentEnd, double rightSegmentEnd, double epsilon) {
        CubePoint answerPoint = new CubePoint(0, 0, 0);
        double goldSection = 1.61803398875;
        double x1 = rightSegmentEnd - (rightSegmentEnd - leftSegmentEnd) / goldSection;
        double x2 = leftSegmentEnd + (rightSegmentEnd - leftSegmentEnd) / goldSection;
        double y1 = getValueOfFunction(x1);
        double y2 = getValueOfFunction(x2);
        if (y1 < y2) {
            rightSegmentEnd = x2;
            x2 = x1;
            x1 = leftSegmentEnd + rightSegmentEnd - x2;
        } else {
            leftSegmentEnd = x1;
            x1 = x2;
            x2 = rightSegmentEnd - x1 + leftSegmentEnd;
        }
        double difference = rightSegmentEnd - leftSegmentEnd;
        while (difference > 2 * epsilon) {
            if (y1 < y2) {
                rightSegmentEnd = x2;
                x2 = x1;
                x1 = leftSegmentEnd + rightSegmentEnd - x2;
                y1 = getValueOfFunction(x1);
                y2 = getValueOfFunction(x2);
                difference = rightSegmentEnd - leftSegmentEnd;
            } else {
                leftSegmentEnd = x1;
                x1 = x2;
                x2 = rightSegmentEnd - x1 + leftSegmentEnd;
                y1 = getValueOfFunction(x1);
                y2 = getValueOfFunction(x2);
                difference = rightSegmentEnd - leftSegmentEnd;
            }
        }
        if (y1 < y2) {
            answerPoint.setX(x1);
            answerPoint.setY(y1);
        } else {
            answerPoint.setX(x2);
            answerPoint.setY(y2);
        }
        return answerPoint;
    }
}
