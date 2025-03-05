package Exp5;

public class mergeSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 500, -250, 0, 42, -42, 7, -7, 100, -100, 3, -3, 3, -3, 250, -500 };
        System.out.println("Unsorted:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        mergeSort(arr, 0, arr.length - 1);
        System.out.println();
        System.out.println("Sorted:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {

        int[] temp = new int[r - l + 1];

        int i = l, j = mid + 1, k = 0;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        for (; i <= mid; i++) {
            temp[k++] = arr[i];
        }
        for (; j <= r; j++) {
            temp[k++] = arr[j];
        }

        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }
}
