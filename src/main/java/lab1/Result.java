package lab1;

import java.util.List;

class Result {

    /*
     * Complete the 'interpolate_by_newton' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. DOUBLE_ARRAY x_axis
     *  2. DOUBLE_ARRAY y_axis
     *  3. DOUBLE x
     */

    public static double interpolate_by_newton(List<Double> x_axis, List<Double> y_axis, double x) {
        int pointsMount = getValidateInputCount(x_axis, y_axis);
        double valueOfPolinom = y_axis.get(0);
        for (int q = 1; q < pointsMount; q++) {
            double multiplication = 1.0d;
            for (int i = 0; i <= q - 1; i++) {
                multiplication *= (x - x_axis.get(i));
            }
            try {
                valueOfPolinom += getDifference(x_axis, y_axis, q) * multiplication;
            } catch (DivisionByZeroException exp) {
                return valueOfPolinom;
            }
        }
        return valueOfPolinom;
    }

    /**
     * @param x_axis Список аргументов
     * @param y_axis Списов значений функции соответствующих аргументов
     * @param n      количество точек (т.е. для раздененной разности f(x0, x1, ... xn).
     * @return коэффициент полинома Ньютона - разделенную разность для n точек.
     */
    private static double getDifference(List<Double> x_axis, List<Double> y_axis, int n) throws DivisionByZeroException {
        double resultDifference = 0.0d;
        for (int j = 0; j < n + 1; j++) {
            double multiplication = 1.0d;
            for (int i = 0; i < n + 1; i++) {
                if (i != j) {
                    double mul = x_axis.get(j) - x_axis.get(i);
                    multiplication *= (mul);
                }
            }
            if (multiplication != 0) {
                resultDifference += (y_axis.get(j) / multiplication);
            } else throw new DivisionByZeroException();

        }
        return resultDifference;
    }

    /**
     * @param x_axis array of x-values of points.
     * @param y_axis array of y-values of points.
     * @return minimum of count of arrays' values. It must have the same quantity.
     */
    private static int getValidateInputCount(List<Double> x_axis, List<Double> y_axis) {
        return Math.min(x_axis.size(), y_axis.size());
    }
}

/**
 * lab1.Result will be calculated until the invalid point.
 */
class DivisionByZeroException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid input - two points with the same x coordinates";
    }
}
