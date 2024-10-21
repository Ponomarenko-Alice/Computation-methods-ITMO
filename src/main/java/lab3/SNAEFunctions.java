package lab3;

import java.util.List;
import java.util.function.Function;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sin;

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


    public static List<Function<List<Double>, Double>> get_functions(int n) {
        return switch (n) {
            case (1) -> List.of(SNAEFunctions::first_function, SNAEFunctions::second_function);
            case (2) -> List.of(SNAEFunctions::third_function, SNAEFunctions::fourth_function);
            case (3) ->
                    List.of(SNAEFunctions::fifth_function, SNAEFunctions::six_function, SNAEFunctions::seven_function);
            default -> List.of(SNAEFunctions::default_function);
        };
    }
}
