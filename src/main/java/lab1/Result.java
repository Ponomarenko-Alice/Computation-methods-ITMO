package lab1;

import java.util.List;

class Result {
    public static double interpolate_by_newton(List<Double> x_axis, List<Double> y_axis, double x) {
        int pointsMount = getValidateInputCount(x_axis, y_axis);
        double valueOfPolinom = y_axis.getFirst();
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
     * @param x_axis arguments array
     * @param y_axis List of function values of corresponding arguments
     * @param n      number of points (i.e. for the divided difference f(x0, x1, ... xn).
     * @return Newton's polynomial coefficient is the divided difference for n points.
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

