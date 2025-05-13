package ese;

import java.util.*;

public class knapsackGreedy {
    public static void main(String[] args) {
        int[][] items = { { 10, 60 }, { 20, 100 }, { 30, 120 } };
        int capacity = 50;
        System.out.println(knapsack(items, capacity));
    }

    public static int knapsack(int[][] items, int capacity) {
        Arrays.sort(items, (a, b) -> (b[1] / b[0]) - (a[1] / a[0]));

        int ans = 0;

        for (int[] item : items) {
            int weight = item[0];
            int value = item[1];

            if (capacity >= weight) {
                capacity -= weight;
                ans += value;
            } else {
                ans += ((int) value / weight) * capacity;
                break;
            }
        }
        return ans;
    }
}
