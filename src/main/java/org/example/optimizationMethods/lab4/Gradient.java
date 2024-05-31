package org.example.optimizationMethods.lab4;

public abstract class Gradient {
    public static double newFunctionValue = -0.0584759305783;
    private double a;

    public Gradient(double a) {
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}
