import java.util.Arrays;

public class SearchComparison {
    // Linear search method
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return 1;
            }
        }
        return -1;
    }

    // Binary search method
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test with different sizes
        int[] sizes = { 500, 5000, 50000 };

        for (int size : sizes) {
            System.out.println("\nTesting with array size: " + size);
            System.out.println("-".repeat(40));

            // Create array and target
            int[] arr = new int[size];
            int target = (int) (Math.random() * 1000000);

            // Generate random numbers in the array
            for (int i = 0; i < size; i++) {
                arr[i] = (int) (Math.random() * 1000000);
            }

            // Linear search timing
            long start = System.nanoTime();
            linearSearch(arr, target);
            long totalLinearTime = System.nanoTime() - start;

            // Sort array for binary search
            Arrays.sort(arr);

            // Binary search timing
            start = System.nanoTime();
            binarySearch(arr, target);
            long totalBinaryTime = System.nanoTime() - start;

            // Print times (changed from %.2f to %d for long values)
            System.out.println("Linear Search Time: " + totalLinearTime + " ns");
            System.out.println("Binary Search Time: " + totalBinaryTime + " ns");
        }
    }
}