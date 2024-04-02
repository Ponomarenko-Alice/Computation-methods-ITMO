package org.example.optimizationMethods.lab4;

import org.example.optimizationMethods.CubePoint;


public class FastestDescent {
    private static final double epsilon = 0.01;
    private static final double delta = 0.25;

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


    public static void main(String[] args) {
        CubePoint point = new CubePoint(0, 0, 0);
        double functionValue = getValueOfFunction(point.getX(), point.getY(), point.getZ());

        //double newFunctionValue = getValueOfFunction(newA, newB, newC);
//        while (Math.abs(newFunctionValue - functionValue) > epsilon) {
//            functionValue = newFunctionValue;
//
//            newA = a + delta * cubeGradient.getA();
//            newB = b + delta * cubeGradient.getB();
//            newC = c + delta * cubeGradient.getC();
//            newFunctionValue = getValueOfFunction(newA, newB, newC);
//        }
//        System.out.println(newFunctionValue);

    }
}
