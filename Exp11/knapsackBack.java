public class knapsackBack {
    static int[] weights = { 2, 3, 4, 5 };
    static int[] values = { 3, 4, 5, 6 };
    static int W = 5;
    static int n = weights.length;
    static int maxProfit = 0;

    public static void main(String[] args) {
        knapsack(0, 0, 0);
        System.out.println("Maximum Profit: " + maxProfit);
    }

    static void knapsack(int index, int currentWeight, int currentProfit) {
        if (currentWeight <= W && currentProfit > maxProfit)
            maxProfit = currentProfit;

        if (index == n || currentWeight > W)
            return;

        // Include current item
        knapsack(index + 1, currentWeight + weights[index], currentProfit + values[index]);

        // Exclude current item
        knapsack(index + 1, currentWeight, currentProfit);
    }
}