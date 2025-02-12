import java.util.Arrays;

public class selectionInLinearTime {

    // Function to find the k-th smallest element in an array
    public static int select(int[] arr, int k) {
        if (arr.length == 1)
            return arr[0];

        // Step 1: Divide into groups of 5 and find medians
        int[] medians = getMedians(arr);

        // Step 2: Find median of medians
        int medianOfMedians = select(medians, medians.length / 2);

        // Step 3: Partition the array using the median of medians as pivot
        int[] partitioned = partition(arr, medianOfMedians);
        int leftSize = partitioned[0]; // Number of elements in the left partition

        // Step 4: Determine which part to recurse on
        if (k < leftSize)
            return select(Arrays.copyOfRange(arr, 0, leftSize), k);
        else if (k > leftSize)
            return select(Arrays.copyOfRange(arr, leftSize + 1, arr.length), k - leftSize - 1);
        else
            return medianOfMedians;
    }

    // divide array into groups of 5 and get medians
    private static int[] getMedians(int[] arr) {
        int n = arr.length;
        int numGroups = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, n);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group); // Sort the group
            medians[i] = group[group.length / 2]; // Get median
        }
        return medians;
    }

    // partition array around pivot
    private static int[] partition(int[] arr, int pivot) {
        int left = 0, right = arr.length - 1;
        int i = 0;
        while (i <= right) {
            if (arr[i] < pivot) {
                swap(arr, left++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, right--, i);
            } else {
                i++;
            }
        }
        return new int[] { left, right };
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Main function to test the algorithm
    public static void main(String[] args) {
        int[] arr = { 29, 5, 22, 18, 7, 15, 40, 1, 12, 30, 50, 8 };
        int k = 4;
        System.out.print("Array:");
        for (int num : arr) {
            System.out.print(" " + num + " ");
        }
        System.out.println();
        System.out.println("The " + k + "-th smallest element is: " + select(arr, k - 1));
    }
}
