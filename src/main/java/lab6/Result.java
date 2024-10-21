package lab6;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Result {

    public static void printDFT(List<Double> x, int n) {
        double[][] dftResult = computeDFT(x, n);
        for (int i = 0; i < n; i++) {
            System.out.println(dftResult[0][i] + " " + dftResult[1][i]);
        }
    }

    public static double[][] computeDFT(List<Double> x, int n) {
        double[] real = new double[n];

        for (int i = 0; i < n; i++) {
            real[i] = x.get(i);
        }

        double[] dftReal = new double[n];
        double[] dftImag = new double[n];

        for (int k = 0; k < n; k++) {
            for (int t = 0; t < n; t++) {
                double angle = 2 * Math.PI * t * k / n;
                dftReal[k] += real[t] * Math.cos(angle);
                dftImag[k] -= real[t] * Math.sin(angle);
            }
        }

        return new double[][]{dftReal, dftImag};
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());
        Result.printDFT(x, n);
        bufferedReader.close();
        bufferedWriter.close();
    }
}
