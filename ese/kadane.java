package ese;

public class kadane {
    public static void main(String[] args) {
        int[] arr = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int n = arr.length;
        System.out.println(kadane(arr));
    }

    public static int kadane(int[] arr) {
        int currSum = arr[0];
        int maxSum = arr[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (currSum + arr[i] < arr[i]) {
                currSum = arr[i];
                tempStart = i;
            } else {
                currSum += arr[i];
            }

            if (currSum > maxSum) {
                maxSum = currSum;
                start = tempStart;
                end = i;
            }
        }

        System.out.print("Subarray: ");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        return maxSum;
    }
}
