package org.example.optimizationMethods.lab4;

public class GradientDescent {
    private static final double epsilon = 0.001;
    private static final double delta = 0.0001;

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
        double a = 0.1, b = 0.1, c = 0.1;
        double functionValue = getValueOfFunction(a, b, c);
        CubeGradient cubeGradient = getCubeGradient(a, b, c);
        double newA = a + delta * cubeGradient.getA();
        double newB = b + delta * cubeGradient.getB();
        double newC = c + delta * cubeGradient.getC();
        double newFunctionValue = getValueOfFunction(newA, newB, newC);
        while (Math.abs(newFunctionValue - functionValue) > epsilon) {
            cubeGradient = getCubeGradient(newA, newB, newC);
            System.out.println("grad" + cubeGradient.getA() + " " + cubeGradient.getB() + " " + cubeGradient.getC());
            functionValue = newFunctionValue;
            a = newA;
            b = newB;
            c = newC;
            newA = a + delta * cubeGradient.getA();
            newB = b + delta * cubeGradient.getB();
            newC = c + delta * cubeGradient.getC();
            newFunctionValue = getValueOfFunction(newA, newB, newC);
            System.out.println(newFunctionValue);
            System.out.println(newA + " " + newB + " " + newC);
        }
        System.out.println("Optimal function value: " + (newFunctionValue + epsilon));
        System.out.println("Optimal point: (" + newA + ", " + newB + ", " + newC + ")");
    }
}
