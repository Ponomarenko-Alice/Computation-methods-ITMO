package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    private static final int MAX_ITERATIONS_COUNT = 100000;
    public static boolean isMethodApplicable = true;
    public static String errorMessage;

    /**
     * Complete the 'solveByGaussSeidel' function below.
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts following parameters:
     * 1. INTEGER n
     * 2. 2D_DOUBLE_ARRAY matrix
     * 3. INTEGER epsilon
     */
    public static List<Double> solveByGaussSeidel(int n, List<List<Double>> matrix, double epsilon) {
        double[][] massiveMatrix = getQuadraticMassiveOfMatrix(n, matrix);
        double[] bVector = getBVector(n, matrix);
        double[][] diagonalDominanceMatrix = getDiagonalDominanceMatrix(n, massiveMatrix);
        if (diagonalDominanceMatrix == null) {
            isMethodApplicable = false;
            errorMessage = "The system has no diagonal dominance for this method. Method of the Gauss-Seidel is not applicable.";
            return null; //null value will be ignored by error "isMethodApplicable"
        } else {
            double[] array = getXVector(n, diagonalDominanceMatrix, bVector, epsilon);
            return Arrays.stream(array).boxed().collect(Collectors.toList());
        }
    }

    private static double[] getXVector(int n, double[][] massiveMatrix, double[] bVector, double epsilon) {
        double[] firstApproaching = new double[n];
        for (int i = 0; i < n; i++) {
            firstApproaching[i] = bVector[i] / massiveMatrix[i][i];
        }
        double[] approaching = new double[n];
        double[] previousApproaching = new double[n];
        int iterationCount = 0;
        System.arraycopy(firstApproaching, 0, approaching, 0, n);
        do {
            //save x vector values from previous iteration
            System.arraycopy(approaching, 0, previousApproaching, 0, n);
            for (int i = 0; i < n; i++) {
                //sum1 - sum of values from current iteration
                double sum1 = 0;
                //sum2 - sum of values from previous iteration
                double sum2 = 0;
                for (int j = 0; j < i; j++) {
                    sum1 += (massiveMatrix[i][j] * approaching[j]);
                }
                for (int j = i + 1; j < n; j++) {
                    sum2 += (massiveMatrix[i][j] * previousApproaching[j]);
                }
                approaching[i] = (bVector[i] - sum1 - sum2) / massiveMatrix[i][i];
            }
            iterationCount++;
        } while (!checkStopPoint(n, massiveMatrix, approaching, bVector, epsilon) && iterationCount < MAX_ITERATIONS_COUNT);
        return approaching;
    }

    /**
     * @return true value only if iterations achieved epsilon approaching
     */
    private static boolean checkStopPoint(int n, double[][] matrix, double[] x, double[] b, double epsilon) {
        boolean stop = false;
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j] * x[i];
            }
            if (Math.abs(sum - b[i]) > epsilon) {
                stop = false;
                break;
            } else {
                stop = true;
            }
        }
        return stop;
    }

    private static double[] getBVector(int n, List<List<Double>> matrix) {
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = matrix.get(i).get(n);
        }
        return vector;
    }

    private static double[][] getQuadraticMassiveOfMatrix(int n, List<List<Double>> matrix) {
        double[][] newMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrix.get(i).get(j);
            }
        }
        return newMatrix;
    }

    /**
     * @param n      size of quadratic matrix
     * @param matrix initial matrix
     * @return new matrix equal with initial matrix with diagonal dominance or null if one doesn't exist
     */
    private static double[][] getDiagonalDominanceMatrix(int n, double[][] matrix) {
        boolean isDiagonalDominant;
        int countOfPermutations = 1;
        for (int i = 1; i < n + 1; i++) {
            countOfPermutations *= i;
        }
        for (int i = 0; i < countOfPermutations; i++) {
            isDiagonalDominant = true;
            List<double[][]> matrixPermutations = generateColumnPermutations(matrix);
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += Math.abs(matrixPermutations.get(i)[j][k]);
                }
                //subtract module of a_ii
                if (sum - Math.abs(matrixPermutations.get(i)[j][j]) > Math.abs(matrixPermutations.get(i)[j][j])) {
                    isDiagonalDominant = false;
                }
            }
            if (isDiagonalDominant) {
                return matrixPermutations.get(i);
            }
        }
        //there no matrix permutation which has diagonal permutation
        return null;
    }

    /**
     * @return list of all permutations of matrix by replacing columns to try to achieve diagonal domination
     */
    public static List<double[][]> generateColumnPermutations(double[][] matrix) {
        List<double[][]> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] colIndices = new int[cols];

        for (int i = 0; i < cols; i++) {
            colIndices[i] = i;
        }
        List<List<Integer>> colPermutations = permute(colIndices);

        for (List<Integer> colIndexPermutation : colPermutations) {
            double[][] permutedMatrix = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    permutedMatrix[i][j] = matrix[i][colIndexPermutation.get(j)];
                }
            }
            result.add(permutedMatrix);
        }
        return result;
    }

    private static List<List<Integer>> permute(int[] indices) {
        List<List<Integer>> result = new ArrayList<>();
        permute(indices, 0, result);
        return result;
    }

    private static void permute(int[] indices, int start, List<List<Integer>> result) {
        if (start == indices.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int index : indices) {
                permutation.add(index);
            }
            result.add(permutation);
            return;
        }
        for (int i = start; i < indices.length; i++) {
            swap(indices, start, i);
            permute(indices, start + 1, result);
            swap(indices, start, i);
        }
    }

    private static void swap(int[] indices, int i, int j) {
        int temp = indices[i];
        indices[i] = indices[j];
        indices[j] = temp;
    }
}


