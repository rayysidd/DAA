import java.util.*;

public class FloydWarshall {

    static final int INF = 10000000; // Representing Infinity

    public static void floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Initialize dist and next matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];

                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        // Core Floyd-Warshall Algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Check for Negative Cycle
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print Final Distance Matrix
        System.out.println("Shortest Distance Matrix:");
        printMatrix(dist);

        // Example: Reconstruct Path from 0 to 3
        System.out.println("Shortest Path from 0 to 3:");
        List<Integer> path = reconstructPath(0, 3, next);
        if (path == null) {
            System.out.println("No Path Exists");
        } else {
            System.out.println(path);
        }
    }

    // Function to Reconstruct Path
    public static List<Integer> reconstructPath(int u, int v, int[][] next) {
        if (next[u][v] == -1) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        path.add(u);

        while (u != v) {
            u = next[u][v];
            path.add(u);
        }

        return path;
    }

    // Print Matrix Utility
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == INF)
                    System.out.print("INF ");
                else
                    System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example Graph (Adjacency Matrix)
        int[][] graph = {
                { 0, 3, INF, 7 },
                { 8, 0, 2, INF },
                { 5, INF, 0, 1 },
                { 2, INF, INF, 0 }
        };

        floydWarshall(graph);
    }
}
