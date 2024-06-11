package lab3;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.pow;

class SNAEFunctions {

    private static double first_function(List<Double> args) {
        return sin(args.getFirst());
    }

    private static double second_function(List<Double> args) {
        return (args.get(0) * args.get(1)) / 2;
    }

    private static double third_function(List<Double> args) {
        return
                pow(args.get(0), 2) * pow(args.get(1), 2) - 3 * pow(args.get(0), 3) - 6 * pow(args.get(1),
                        3) + 8;
    }

    private static double fourth_function(List<Double> args) {
        return pow(args.get(0), 4) - 9 * args.get(1) + 2;
    }

    private static double fifth_function(List<Double> args) {
        return args.get(0) + pow(args.get(0), 2) - 2 * args.get(1) * args.get(2) - 0.1;
    }

    private static double six_function(List<Double> args) {
        return args.get(1) + pow(args.get(1), 2) + 3 * args.get(0) * args.get(2) + 0.2;
    }


    private static double seven_function(List<Double> args) {
        return args.get(2) + pow(args.get(2), 2) + 2 * args.get(0) * args.get(1) - 0.3;
    }

    private static double default_function(List<Double> args) {
        return 0.0;
    }

    /*
     * How to use this function:
     *    List<Function<Double, List<Double>> funcs = get_functions(4);
     *    funcs[0].apply(List.of(0.0001, 0.002));
     */
    public static List<Function<List<Double>, Double>> get_functions(int n) {
        switch (n) {
            case (1):
                return List.of(SNAEFunctions::first_function, SNAEFunctions::second_function);
            case (2):
                return List.of(SNAEFunctions::third_function, SNAEFunctions::fourth_function);
            case (3):
                return List.of(SNAEFunctions::fifth_function, SNAEFunctions::six_function, SNAEFunctions::seven_function);
            default:
                return List.of(SNAEFunctions::default_function);
        }
    }
}


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int system_id = Integer.parseInt(bufferedReader.readLine().trim());

        int number_of_unknowns = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> initial_approximations = IntStream.range(0, number_of_unknowns).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> result = Result.solve_by_fixed_point_iterations(system_id, number_of_unknowns, initial_approximations);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
