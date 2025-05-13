package ese;

public class quickSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 0, -10, 500, -3, 2, 2, 1000000, -100000, 42, -42, 5, 1, 3, 3, 0 };
        System.out.println("Unsorted:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        quickSort(arr, 0, arr.length - 1);
        System.out.println();
        System.out.println("Sorted:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int pivot = partition(arr, l, r);
            quickSort(arr, pivot + 1, r);
            quickSort(arr, l, pivot - 1);
        }
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l;
        int j = r;

        while (i < j) {
            while (arr[i] <= pivot && i <= r - 1) {
                i++;
            }

            while (arr[j] >= pivot && j >= l + 1) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;

        return j;
    }
}
