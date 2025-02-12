import java.util.Arrays;

public class strassenMatrixMultiplication {

    // Function to multiply matrices using Strassen's Algorithm
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: If 1x1 matrix, multiply directly
        if (n == 1) {
            return new int[][] { { A[0][0] * B[0][0] } };
        }

        // Split matrices into 4 submatrices
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        // Divide A and B into submatrices
        divideMatrix(A, A11, 0, 0);
        divideMatrix(A, A12, 0, newSize);
        divideMatrix(A, A21, newSize, 0);
        divideMatrix(A, A22, newSize, newSize);
        divideMatrix(B, B11, 0, 0);
        divideMatrix(B, B12, 0, newSize);
        divideMatrix(B, B21, newSize, 0);
        divideMatrix(B, B22, newSize, newSize);

        // Compute the 7 Strassen multiplications
        int[][] M1 = strassenMultiply(addMatrix(A11, A22), addMatrix(B11, B22));
        int[][] M2 = strassenMultiply(addMatrix(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtractMatrix(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtractMatrix(B21, B11));
        int[][] M5 = strassenMultiply(addMatrix(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtractMatrix(A21, A11), addMatrix(B11, B12));
        int[][] M7 = strassenMultiply(subtractMatrix(A12, A22), addMatrix(B21, B22));

        // Compute submatrices of C
        int[][] C11 = addMatrix(subtractMatrix(addMatrix(M1, M4), M5), M7);
        int[][] C12 = addMatrix(M3, M5);
        int[][] C21 = addMatrix(M2, M4);
        int[][] C22 = addMatrix(subtractMatrix(addMatrix(M1, M3), M2), M6);

        // Combine results into the final matrix
        int[][] C = new int[n][n];
        joinMatrix(C11, C, 0, 0);
        joinMatrix(C12, C, 0, newSize);
        joinMatrix(C21, C, newSize, 0);
        joinMatrix(C22, C, newSize, newSize);

        return C;
    }

    // Helper function to add matrices
    private static int[][] addMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    // Helper function to subtract matrices
    private static int[][] subtractMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    // Helper function to divide a matrix into submatrices
    private static void divideMatrix(int[][] parent, int[][] child, int row, int col) {
        int size = child.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                child[i][j] = parent[row + i][col + j];
    }

    // Helper function to join submatrices into a single matrix
    private static void joinMatrix(int[][] child, int[][] parent, int row, int col) {
        int size = child.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                parent[row + i][col + j] = child[i][j];
    }

    // Helper function to print a matrix
    private static void printMatrix(String label, int[][] matrix) {
        System.out.println(label);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // Main function to test Strassen’s Algorithm
    public static void main(String[] args) {
        int[][] A = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };
        int[][] B = {
                { 17, 18, 19, 20 },
                { 21, 22, 23, 24 },
                { 25, 26, 27, 28 },
                { 29, 30, 31, 32 }
        };

        printMatrix("Matrix A:", A);
        printMatrix("Matrix B:", B);

        int[][] C = strassenMultiply(A, B);

        printMatrix("Resultant Matrix C (A * B):", C);
    }
}
