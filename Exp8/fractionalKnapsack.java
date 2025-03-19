import java.util.Arrays;

class FractionalKnapsack {

    static double fractionalKnapsack(int[][] items, int capacity) {
        // Sort items based on value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare((double) b[1] / b[0], (double) a[1] / a[0]));

        double totalValue = 0.0;

        for (int[] item : items) {
            int weight = item[0];
            int value = item[1];

            if (capacity >= weight) {
                capacity -= weight;
                totalValue += value;
            } else {
                totalValue += ((double) value / weight) * capacity;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[][] items = { { 10, 60 }, { 20, 100 }, { 30, 120 } };
        int capacity = 50;
        System.out.println("Maximum value in Knapsack = " + fractionalKnapsack(items, capacity));
    }
}
