package org.example.optimizationMethods.lab3;

import org.example.optimizationMethods.CubePoint;
import org.example.optimizationMethods.Point;

import java.util.ArrayList;
import java.util.Collections;

public class QuadraticApproximation {
    public static void main(String[] args) {
        Point point = getExtremumPoint(0, 1, 0.0001);
        System.out.println(point.getX());
        System.out.println(point.getY());
    }

    private static double getValueOfFunction(double x) {
        return Math.pow(x, 3) - 3 * Math.sin(x);
    }

    private static double getQuadraticPolinomValue(double x1, double x2, double x3, double y1, double y2, double y3) throws Exception {
        double chisl = ((x2 * x2 - x3 * x3) * y1 + (x3 * x3 - x1 * x1) * y2 + (x1 * x1 - x2 * x2) * y3);
        double znam = ((x2 - x3) * y1 + (x3 - x1) * y2 + (x1 - x2) * y3);
        if (znam == 0) {
            throw new Exception();
        }
        return 0.5 * (chisl / znam);
    }


    private static Point getExtremumPoint(double leftSegmentEnd, double rightSegmentEnd, double epsilon) {
        double x1 = (leftSegmentEnd + rightSegmentEnd) / 2;
        double delX = x1 / 2;
        double x2 = x1 + delX;
        double y1 = getValueOfFunction(x1);
        double y2 = getValueOfFunction(x2);
        double x3 = (y1 > y2) ? x1 + 2 * delX : x1 - delX;
        double y3 = getValueOfFunction(x3);
        double Ymin = Math.min(y1, Math.min(y2, y3));
        double Xmin;
        if (Ymin == y1) {
            Xmin = x1;
        } else if (Ymin == y2) {
            Xmin = x2;
        } else {
            Xmin = x3;
        }
        double qx = x1;
        double yqx = getValueOfFunction(x1);
        try {
            qx = getQuadraticPolinomValue(x1, x2, x3, y1, y2, y3);
            yqx = getValueOfFunction(qx);
        } catch (Exception e) {
            x1 = Xmin;
            x2 = x1 + delX;
            y1 = getValueOfFunction(x1);
            y2 = getValueOfFunction(x2);
            x3 = (y1 > y2) ? x1 + 2 * delX : x1 - delX;
            y3 = getValueOfFunction(x3);
            Ymin = Math.min(y1, Math.min(y2, y3));
            if (Ymin == y1) {
                Xmin = x1;
            } else if (Ymin == y2) {
                Xmin = x2;
            } else {
                Xmin = x3;
            }
            try {
                qx = getQuadraticPolinomValue(x1, x2, x3, y1, y2, y3);
                yqx = getValueOfFunction(qx);
            } catch (Exception ex) {
                System.out.println(e.getMessage());
            }


        }

        while (Math.abs((Ymin - yqx) / yqx) < epsilon && Math.abs((Xmin - qx) / qx) < epsilon) {
            if (qx >= x1 && qx <= x3) {
                ArrayList<Double> arr = new ArrayList<>();
                arr.add(x1);
                arr.add(x2);
                arr.add(x3);
                arr.add(qx);
                Collections.sort(arr);
                x2 = arr.get(arr.indexOf(Math.min(Xmin, qx)));
                x1 = arr.get(arr.indexOf(Math.min(Xmin, qx)) - 1);
                x3 = arr.get(arr.indexOf(Math.min(Xmin, qx)) + 1);
                Ymin = Math.min(y1, Math.min(y2, y3));
                if (Ymin == y1) {
                    Xmin = x1;
                } else if (Ymin == y2) {
                    Xmin = x2;
                } else {
                    Xmin = x3;
                }
                try {
                    qx = getQuadraticPolinomValue(x1, x2, x3, y1, y2, y3);
                    yqx = getValueOfFunction(qx);
                } catch (Exception e) {
                    continue;
                }
            } else {
                x1 = (leftSegmentEnd + rightSegmentEnd) / 2;
                delX = x1 / 2;
                x2 = x1 + delX;
                y1 = getValueOfFunction(x1);
                y2 = getValueOfFunction(x2);
                x3 = (y1 > y2) ? x1 + 2 * delX : x1 - delX;
                y3 = getValueOfFunction(x3);
                Ymin = Math.min(y1, Math.min(y2, y3));
                if (Ymin == y1) {
                    Xmin = x1;
                } else if (Ymin == y2) {
                    Xmin = x2;
                } else {
                    Xmin = x3;
                }
                try {
                    qx = getQuadraticPolinomValue(x1, x2, x3, y1, y2, y3);
                    yqx = getValueOfFunction(qx);
                } catch (Exception e) {
                    continue;
                }
            }
        }
        CubePoint answerPoint = new CubePoint(0, 0, 0);
        answerPoint.setX(qx);
        answerPoint.setY(getValueOfFunction(qx));
        return answerPoint;
    }

}
