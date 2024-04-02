package org.example.optimizationMethods;

public class CubePoint extends Point {
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    private double z;
    public CubePoint(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
}
