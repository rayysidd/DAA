import java.util.Scanner;

public class knapsack {
    public static int knapsack(int[] wt, int[] profit, int cap) {
        int n = wt.length;
        int[][] dp = new int[n + 1][cap + 1];

        // Filling the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], profit[i - 1] + dp[i - 1][j - wt[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[n][cap];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] profit = new int[n];

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.println("Enter profits of items:");
        for (int i = 0; i < n; i++) {
            profit[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        System.out.println("Maximum Profit: " + knapsack(wt, profit, W));
    }
}
