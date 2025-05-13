package ese;

public class knapSackDP {
    public static void main(String[] args) {
        int[] weights = new int[] { 2, 3, 4, 5 };
        int[] values = new int[] { 3, 4, 5, 6 };
        int W = 5;
        int n;
        int maxProfit;
        System.out.println(knapsack(weights, values, W));
    }

    public static int knapsack(int[] W, int[] profit, int capacity) {
        int n = W.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (W[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], profit[i - 1] + dp[i - 1][j - W[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[n][capacity];
    }
}
