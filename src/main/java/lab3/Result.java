package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

class Result {
    private static final double EPSILON = 1e-5;
    private static final int MAX_ITERATIONS = 100;

    public static List<Double> solve_by_fixed_point_iterations(int system_id, int number_of_unknowns, List<Double> initial_approximations) {
        List<Function<List<Double>, Double>> functions = SNAEFunctions.get_functions(system_id);
        List<Double> currentApproximations = new ArrayList<>(initial_approximations);
        List<Double> nextApproximations = new ArrayList<>(Collections.nCopies(number_of_unknowns, 0.0));

        boolean convergence;
        int iterationCounter = 0;

        while (iterationCounter < MAX_ITERATIONS) {
            for (int i = 0; i < number_of_unknowns; i++) {
                nextApproximations.set(i, functions.get(i).apply(currentApproximations));
            }

            convergence = true;
            for (int i = 0; i < number_of_unknowns; i++) {
                if (Math.abs(nextApproximations.get(i) - currentApproximations.get(i)) > EPSILON) {
                    convergence = false;
                    break;
                }
            }
            if (convergence) {
                break;
            }

            currentApproximations = new ArrayList<>(nextApproximations);
            iterationCounter++;
        }

        for (int i = 0; i < number_of_unknowns; i++) {
            if (Double.isNaN(currentApproximations.get(i)) || Double.isInfinite(currentApproximations.get(i)))
                currentApproximations.set(i, initial_approximations.get(i));
        }

        return getCeilResult(currentApproximations);
    }

    private static List<Double> getCeilResult(List<Double> list) {
        double scale = Math.pow(10, 6);
        list.replaceAll(aDouble -> Math.ceil(aDouble * scale) / scale);
        return list;
    }
}
