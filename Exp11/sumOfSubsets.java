import java.util.*;

public class sumOfSubsets {
    static int[] arr = { 10, 7, 5, 18, 12, 20, 15 };
    static int target = 35;
    static boolean[] included = new boolean[arr.length];

    public static void main(String[] args) {
        subsetSum(0, 0);
    }

    static void subsetSum(int index, int currentSum) {
        if (currentSum == target) {
            printSubset();
            return;
        }
        if (index >= arr.length || currentSum > target)
            return;

        included[index] = true;
        subsetSum(index + 1, currentSum + arr[index]);

        included[index] = false;
        subsetSum(index + 1, currentSum);
    }

    static void printSubset() {
        for (int i = 0; i < arr.length; i++)
            if (included[i])
                System.out.print(arr[i] + " ");
        System.out.println();
    }
}
