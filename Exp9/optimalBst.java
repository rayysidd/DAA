public class optimalBst {

    // Function to calculate the minimum cost of an Optimal BST
    public static int optimalSearchTree(int[] keys, int[] freq) {
        int n = keys.length;
        int[][] cost = new int[n][n];
        int[][] sumFreq = new int[n][n];

        // Base case: cost for one key
        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
            sumFreq[i][i] = freq[i];
        }

        // Now build up the cost table for chains of length 2 to n
        for (int L = 2; L <= n; L++) { // L is chain length
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Calculate sum of frequencies from i to j
                sumFreq[i][j] = sumFreq[i][j - 1] + freq[j];

                // Try making all keys in interval keys[i..j] as root
                for (int r = i; r <= j; r++) {
                    int left = (r > i) ? cost[i][r - 1] : 0;
                    int right = (r < j) ? cost[r + 1][j] : 0;
                    int total = left + right + sumFreq[i][j];
                    cost[i][j] = Math.min(cost[i][j], total);
                }
            }
        }

        return cost[0][n - 1];
    }

    public static void main(String[] args) {
        int[] keys = { 10, 12, 20 };
        int[] freq = { 34, 8, 50 };

        int minCost = optimalSearchTree(keys, freq);
        System.out.println("Minimum cost of Optimal BST: " + minCost);
    }
}