import java.util.Scanner;

public class DisjointSet {

    public static class DSU {
        int n;
        int[] parent;
        int[] rank;

        public DSU(int n) {
            this.n = n;
            rank = new int[n];
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                rank[i] = 0;
                parent[i] = i;
            }
        }

        public int findParent(int n) {
            if (parent[n] == n) {
                return n;
            }
            return parent[n] = findParent(parent[n]);
        }

        public boolean union(int n1, int n2) {
            n1 = findParent(n1);
            n2 = findParent(n2);

            if (n1 == n2) {
                return false;
            } else if (rank[n1] > rank[n2]) {
                parent[n2] = n1;
                rank[n1] += rank[n2];
            } else {
                parent[n1] = n2;
                rank[n2] += rank[n2];
            }
            return true;
        }

        // Method to display the parent and rank arrays
        public void display() {
            System.out.println("Parent Array: ");
            for (int i = 0; i < n; i++) {
                System.out.print(parent[i] + " ");
            }
            System.out.println();

            System.out.println("Rank Array: ");
            for (int i = 0; i < n; i++) {
                System.out.print(rank[i] + " ");
            }
            System.out.println();
        }

        // Method to check if two elements are in the same set
        public boolean areInSameSet(int n1, int n2) {
            return findParent(n1) == findParent(n2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        DSU dsu = new DSU(n);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Find Parent");
            System.out.println("2. Union");
            System.out.println("3. Display Arrays");
            System.out.println("4. Check if Same Set");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter element to find parent: ");
                    int element = sc.nextInt();
                    System.out.println("Parent of " + element + ": " + dsu.findParent(element));
                    break;
                case 2:
                    System.out.print("Enter two elements to union: ");
                    int n1 = sc.nextInt();
                    int n2 = sc.nextInt();
                    if (dsu.union(n1, n2)) {
                        System.out.println("Union successful!");
                    } else {
                        System.out.println("Elements are already in the same set.");
                    }
                    break;
                case 3:
                    dsu.display(); // Display the parent and rank arrays
                    break;
                case 4:
                    System.out.print("Enter two elements to check if they are in the same set: ");
                    int n1ToCheck = sc.nextInt();
                    int n2ToCheck = sc.nextInt();
                    if (dsu.areInSameSet(n1ToCheck, n2ToCheck)) {
                        System.out.println(n1ToCheck + " and " + n2ToCheck + " are in the same set.");
                    } else {
                        System.out.println(n1ToCheck + " and " + n2ToCheck + " are not in the same set.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}