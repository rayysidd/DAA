class MaxSubarraySum {

    // Helper function to find the max crossing sum
    private static int maxCrossingSum(int arr[], int left, int mid, int right) {
        // Find max sum on left side including mid
        int leftSum = Integer.MIN_VALUE;
        int sumLeft = 0;
        for (int i = mid; i >= left; i--) {
            sumLeft += arr[i];
            leftSum = Math.max(leftSum, sumLeft);
        }

        // Find max sum on right side including mid + 1
        int rightSum = Integer.MIN_VALUE;
        int sumRight = 0;
        for (int i = mid + 1; i <= right; i++) {
            sumRight += arr[i];
            rightSum = Math.max(rightSum, sumRight);
        }

        return leftSum + rightSum;
    }

    // Recursive function to find maximum subarray sum
    private static int maxSubarraySum(int arr[], int left, int right) {
        // Base case: only one element
        if (left == right)
            return arr[left];

        int mid = (left + right) / 2;

        int leftMax = maxSubarraySum(arr, left, mid);
        int rightMax = maxSubarraySum(arr, mid + 1, right);
        int crossMax = maxCrossingSum(arr, left, mid, right);

        return Math.max(leftMax, Math.max(rightMax, crossMax));
    }

    public static void main(String[] args) {
        int arr[] = { 2, 3, -8, 7, -1, 2, 3 };
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, 0, n - 1);
        System.out.println("Maximum Subarray Sum: " + maxSum);
    }
}
