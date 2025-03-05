package Exp5;

public class quickSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 1, 2, 3, 9, 4 };
        quickSort(arr, 0, arr.length - 1);

        for (int num : arr) {
            System.out.println(num);
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
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;

        return j;
    }
}
