package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M {

    public static void main(String[] args) {
        int[][] matrix = {
                {-1, 5, 3},
                {-4, 1, 2},
                {-1, 2, 5}
        };

        List<int[][]> result = generateColumnPermutations(matrix);
        for (int[][] permutedMatrix : result) {
            printMatrix(permutedMatrix);
            System.out.println(Arrays.toString(getPermutationVector(matrix, permutedMatrix)));
            System.out.println();
        }
    }

    public static List<int[][]> generateColumnPermutations(int[][] matrix) {
        List<int[][]> result = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] colIndices = new int[cols];

        for (int i = 0; i < cols; i++) {
            colIndices[i] = i;
        }

        List<List<Integer>> colPermutations = permute(colIndices);

        for (List<Integer> colIndexPermutation : colPermutations) {
            int[][] permutedMatrix = new int[rows][cols];
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

    private static int[] getPermutationVector(int[][] startMatrix, int[][] permutMatrix) {
        int[] permutationVector = new int[startMatrix[0].length]; // The dimension of the vector corresponds to the number of columns
        for (int j = 0; j < permutMatrix[0].length; j++) {
            int[] column = new int[permutMatrix.length];
            for (int i = 0; i < permutMatrix.length; i++) {
                column[i] = permutMatrix[i][j];
            }
            int index = findColumnIndex(startMatrix, column);
            permutationVector[j] = index;
        }
        return permutationVector;
    }


    private static int findColumnIndex(int[][] matrix, int[] column) {
        for (int j = 0; j < matrix[0].length; j++) {
            boolean match = true;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] != column[i]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return j;
            }
        }
        return -1;
    }


    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
