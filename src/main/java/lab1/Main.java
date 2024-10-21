package lab1;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Double> x_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> y_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        double x = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.interpolate_by_newton(x_axis, y_axis, x);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
