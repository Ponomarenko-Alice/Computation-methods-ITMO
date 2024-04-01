package org.example.optimizationMethods.lab4;

public class GradientDescent {
    private static final double epsilon = 0.01;
    private static final double gamma = 0.25;

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

    private static CubeGradient getCubeGradient(double a, double b, double c) {
        return new CubeGradient(getADerivativeFunc(a, b, c),
                getBDerivativeFunc(a, b, c),
                getCDerivativeFunc(a, b, c));
    }

    public static void main(String[] args) {
        double a = 0, b = 0, c = 0;
        double functionValue = getValueOfFunction(a, b, c);
        CubeGradient cubeGradient = getCubeGradient(a, b, c);
        double newA = a + gamma * cubeGradient.getA();
        double newB = b + gamma * cubeGradient.getB();
        double newC = c + gamma * cubeGradient.getC();
        double newFunctionValue = getValueOfFunction(newA, newB, newC);
        while (Math.abs(newFunctionValue - functionValue) > epsilon) {
            cubeGradient = getCubeGradient(newA, newB, newC);
            functionValue = newFunctionValue;
            a = newA;
            b = newB;
            c = newC;
            newA = a + gamma * cubeGradient.getA();
            newB = b + gamma * cubeGradient.getB();
            newC = c + gamma * cubeGradient.getC();
            newFunctionValue = getValueOfFunction(newA, newB, newC);
        }
        System.out.println(newFunctionValue);

    }
}
