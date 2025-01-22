import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class sorting {
    public static void main(String[] args) {
        int[] sizes = { 1000, 10000, 100000 };

        for (int size : sizes) {
            System.out.println("\nSorting " + size + " elements:");
            int[] randomArray = generateRandomArray(size);

            System.out.println("Bubble Sort Time: " + measureSortingTime(randomArray, "Bubble") + " ms");
            System.out.println("Selection Sort Time: " + measureSortingTime(randomArray, "Selection") + " ms");
            System.out.println("Insertion Sort Time: " + measureSortingTime(randomArray, "Insertion") + " ms");
            System.out.println("Tournament Sort Time: " + measureSortingTime(randomArray, "Tournament") + " ms");
        }
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    public static long measureSortingTime(int[] arr, String algorithm) {
        int[] copy = Arrays.copyOf(arr, arr.length); // Use a copy of the array
        long startTime = System.nanoTime();

        switch (algorithm) {
            case "Bubble":
                bubbleSort(copy);
                break;
            case "Selection":
                selectionSort(copy);
                break;
            case "Insertion":
                insertionSort(copy);
                break;
            case "Tournament":
                tournamentSort(copy);
                break;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            int temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
    }

    public static void tournamentSort(int[] arr) {
        int n = arr.length;
        int[] tree = new int[2 * n - 1];

        buildTree(arr, tree, n);

        for (int i = 0; i < n; i++) {
            // The root of the tree contains the smallest element
            arr[i] = tree[0];
            extractMinAndRebuild(tree, n - i);
        }
    }

    public static void buildTree(int[] arr, int[] tree, int n) {
        System.arraycopy(arr, 0, tree, n - 1, n);

        for (int i = n - 2; i >= 0; i--) {
            if (tree[2 * i + 1] < tree[2 * i + 2]) {
                tree[i] = tree[2 * i + 1];
            } else {
                tree[i] = tree[2 * i + 2];
            }
        }
    }

    public static void extractMinAndRebuild(int[] tree, int n) {
        int temp = tree[0];
        tree[0] = tree[--n];

        int i = 0;
        while (i < n) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            int smallest = i;

            if (leftChild < n && tree[leftChild] < tree[smallest]) {
                smallest = leftChild;
            }
            if (rightChild < n && tree[rightChild] < tree[smallest]) {
                smallest = rightChild;
            }
            if (smallest == i) {
                break;
            }

            int tempSwap = tree[i];
            tree[i] = tree[smallest];
            tree[smallest] = tempSwap;

            i = smallest;
        }
    }
}
