import java.util.*;

public class mcm {

    static void matrixChainOrder(int[] p, int n) {
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for (int i = 1; i < n; i++) {
            m[i][i] = 0; // cost is 0 for multiplying one matrix
        }

        for (int l = 2; l < n; l++) { // l is chain length
            for (int i = 1; i <= n - l; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println("Minimum number of multiplications is: " + m[1][n - 1]);
        System.out.print("Optimal Parenthesization is: ");
        printOptimalParens(s, 1, n - 1);
    }

    static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] p = { 30, 35, 15, 5, 10, 20, 25 }; // Example: 6 matrices
        int n = p.length;
        matrixChainOrder(p, n);
    }
}
