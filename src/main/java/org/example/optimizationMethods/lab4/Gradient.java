package org.example.optimizationMethods.lab4;

public abstract class Gradient {
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
