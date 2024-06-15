package lab4;

import java.util.function.Function;

public class Result {
    public static String error_message = "";
    public static boolean has_discontinuity = false;

    private static double first_function(double x) {
        return 1 / x;
    }

    private static double second_function(double x) {
        return Math.sin(x) / x;
    }

    private static double third_function(double x) {
        return x * x + 2;
    }

    private static double fourth_function(double x) {
        return 2 * x + 2;
    }

    private static double fifth_function(double x) {
        return Math.log(x);
    }

    private static Function<Double, Double> get_function(int n) {
        return switch (n) {
            case 1 -> Result::first_function;
            case 2 -> Result::second_function;
            case 3 -> Result::third_function;
            case 4 -> Result::fourth_function;
            case 5 -> Result::fifth_function;
            default -> throw new UnsupportedOperationException("Function " + n + " not defined.");
        };
    }

    public static double calculate_integral(double a, double b, int f, double epsilon) {
        Function<Double, Double> func = get_function(f);
        boolean flag = !(a < b);
        double len = flag ? a - b : b - a;
        int n = (int) (len / epsilon);
        n = n % 2 == 0 ? n + 2 : n + 1;
        double h = len / n;

        // Проверка на то, имеется ли разрыв второго рода.
        for (double t = a; t < b; t += h) {
            if (func.apply(t).isInfinite() || func.apply(t).isNaN()) {
                has_discontinuity = true;
                error_message = "Integrated function has discontinuity or does not defined in current interval";
                return -1;
            }
        }

        //если a < b то полагаем отрезок [b, a]
        if (flag) {
            double c = a;
            a = b;
            b = c;
        }
        double integral = 0;
        double x0 = a;
        double x2 = a + 2 * h;

        while (x2 < b) {
            double x1 = x0 + h;
            double sum;
            try {
                sum = h / 3 * (func.apply(x0) + 4 * func.apply(x1) + func.apply(x2));
            } catch (ArithmeticException e) {
                //устранимая точка развыва, берем число рядом
                sum = h / 3 * (func.apply(x0 + h / 10) + 4 * func.apply(x1 + h / 10) + func.apply(x2 + h / 10));
            }
            integral += sum;
            x0 = x2;
            x2 += 2 * h;

        }
        // Последний шаг, который меньше 2
        if (x0 < b) {
            try {
                double x1 = (x0 + b) / 2;
                integral += (b - x0) / 6 * (func.apply(x0) + 4 * func.apply(x1) + func.apply(b));
            } catch (ArithmeticException e) {
                double x1 = (x0 + b) / 2;
                integral += (b - x0) / 6 * (func.apply(x0 - h / 10) + 4 * func.apply(x1 - h / 10) + func.apply(b - h / 10));
            }
        }
        return flag ? -integral : integral;
    }

    public static void main(String[] args) {
        // Пример использования
        double a = 1;
        double b = 2;
        int function_number = 1; // выбор функции по номеру (1-5)
        double epsilon = 0.12;

        double result = calculate_integral(a, b, function_number, epsilon);
        if (!has_discontinuity) {
            System.out.println("Integral result: " + result);
        } else {
            System.out.println("Error: " + error_message);
        }
    }
}
