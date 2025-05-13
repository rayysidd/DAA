package ese;

public class sumOfSubsets {
    static int[] arr = { 10, 7, 5, 18, 12, 20, 15 };
    static int target = 35;
    static boolean[] included = new boolean[arr.length];

    public static void main(String[] args) {
        rec(0, 0);
    }

    public static void rec(int index, int currSum) {
        if (currSum == target) {
            printSubset();
            return;
        }
        if (index >= arr.length || currSum > target)
            return;

        included[index] = true;
        rec(index + 1, currSum + arr[index]);
        included[index] = false;
        rec(index + 1, currSum);
    }

    public static void printSubset() {
        for (int i = 0; i < arr.length; i++) {
            if (included[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
