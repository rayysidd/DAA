package ese;

public class maxSubarray {
    public static void main(String[] args) {
        int[] arr = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, 0, n - 1);
        System.out.print("Array:");
        for (int num : arr) {
            System.out.print(" " + num + " ");
        }
        System.out.println();
        System.out.println("Maximum Subarray Sum: " + maxSum);
    }

    public static int maxSubarraySum(int[] arr, int l, int r) {
        // base case
        if (l == r) {
            return arr[l];
        }
        int mid = (l + r) / 2;
        int leftMax = maxSubarraySum(arr, l, mid);
        int rightMax = maxSubarraySum(arr, mid + 1, r);
        int crossMax = maxCrossingSum(arr, l, mid, r);

        return Math.max(crossMax, Math.max(leftMax, rightMax));
    }

    public static int maxCrossingSum(int[] arr, int l, int mid, int r) {
        int maxLeft = Integer.MIN_VALUE;
        int leftSum = 0;

        for (int i = mid; i >= l; i--) {
            leftSum += arr[i];
            maxLeft = Math.max(maxLeft, leftSum);
        }

        int maxRight = Integer.MIN_VALUE;
        int rightSum = 0;

        for (int i = mid + 1; i <= r; i++) {
            rightSum += arr[i];
            maxRight = Math.max(maxRight, rightSum);
        }

        return maxLeft + maxRight;
    }
}
