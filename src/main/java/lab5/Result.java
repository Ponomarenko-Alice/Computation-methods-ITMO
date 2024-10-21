package lab5;

import java.io.*;
import java.util.function.BiFunction;

import static java.lang.Math.sin;

public class Result {

    // Define functions to be used in differential equation
    private static double first_function(double x, double y) {
        return sin(x);
    }

    private static double second_function(double x, double y) {
        return (x * y) / 2;
    }

    private static double third_function(double x, double y) {
        return y - (2 * x) / y;
    }

    private static double fourth_function(double x, double y) {
        return x + y;
    }

    private static double default_function(double x, double y) {
        return 0.0;
    }

    // Get the appropriate function based on input
    private static BiFunction<Double, Double, Double> get_function(int n) {
        return switch (n) {
            case 1 -> Result::first_function;
            case 2 -> Result::second_function;
            case 3 -> Result::third_function;
            case 4 -> Result::fourth_function;
            default -> Result::default_function;
        };
    }

    public static double solveByMilne(int f, double epsilon, double a, double y_a, double b) {
        if (epsilon <= 0 || a >= b) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }
        BiFunction<Double, Double, Double> func = get_function(f);

        // Calculate the number of steps and step size h
        int steps = (int) Math.ceil((b - a) / epsilon) + 1;
        double h = (b - a) / steps;

        double[] x = new double[4];
        double[] y = new double[4];

        // Calculate the first three points using Runge-Kutta method
        x[0] = a;
        y[0] = y_a;
        for (int i = 1; i < 4; i++) {
            x[i] = x[0] + i * h;
            y[i] = getRungeKuttaFunctionValue(func, x[i - 1], y[i - 1], h);
        }

        return getYFinalValue(steps, func, h, y, x, epsilon, a);
    }

    private static double getYFinalValue(int steps, BiFunction<Double, Double, Double> func,
                                         double h, double[] y, double[] x, double epsilon, double a) {
        double y_next;
        for (int i = 3; i < steps; i++) {
            double x_next = a + (i + 1) * h;
            y_next = y[0] + (4 * h / 3) * (2 * func.apply(x[3], y[3]) - func.apply(x[2], y[2])
                    + 2 * func.apply(x[1], y[1]));

            // Correct the predicted y_next using iterative method
            y_next = correctYNext(func, h, x, y, epsilon, x_next, y_next);

            // Shift arrays for the next iteration
            System.arraycopy(x, 1, x, 0, 3);
            System.arraycopy(y, 1, y, 0, 3);

            x[3] = x_next;
            y[3] = y_next;
        }
        return y[3];
    }

    private static double correctYNext(BiFunction<Double, Double, Double> func, double h, double[] x,
                                       double[] y, double epsilon, double x_next, double y_next) {
        double y_corrected;
        do {
            y_corrected = y_next;
            y_next = y[2] + (h / 3) *
                    (func.apply(x_next, y_corrected) + 4 * func.apply(x[3], y[3]) + func.apply(x[2], y[2]));
        } while (Math.abs(y_next - y_corrected) > epsilon);
        return y_next;
    }

    /**
     * @return next y value
     */
    private static double getRungeKuttaFunctionValue(BiFunction<Double, Double, Double> func, double x,
                                                     double y, double h) {
        double point1 = h * func.apply(x, y);
        double point2 = h * func.apply(x + h / 2, y + point1 / 2);
        double point3 = h * func.apply(x + h / 2, y + point2 / 2);
        double point4 = h * func.apply(x + h, y + point3);
        return y + (point1 + 2 * point2 + 2 * point3 + point4) / 6;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int f = Integer.parseInt(bufferedReader.readLine().trim());
        double epsilon = Double.parseDouble(bufferedReader.readLine().trim());
        double a = Double.parseDouble(bufferedReader.readLine().trim());
        double y_a = Double.parseDouble(bufferedReader.readLine().trim());
        double b = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.solveByMilne(f, epsilon, a, y_a, b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
