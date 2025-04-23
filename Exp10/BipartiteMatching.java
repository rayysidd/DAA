import java.util.*;

public class BipartiteMatching {
    public static int maxBipartiteMatching(int[] U, int[] V, int[][] edges) {
        int n = U.length;
        int m = V.length;
        int size = n + m + 2;
        int source = size - 2;
        int sink = size - 1;

        int[][] graph = new int[size][size];

        // Source to U
        for (int i = 0; i < n; i++) {
            graph[source][i] = 1;
        }

        // V to Sink
        for (int j = 0; j < m; j++) {
            graph[n + j][sink] = 1;
        }

        // U to V edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u][n + v] = 1;
        }

        return FordFulkerson.fordFulkerson(graph, source, sink);
    }

    public static void main(String[] args) {
        int[] U = { 0, 1, 2 };
        int[] V = { 0, 1, 2 };
        int[][] edges = {
                { 0, 0 },
                { 0, 1 },
                { 1, 1 },
                { 2, 2 }
        };

        int maxMatch = maxBipartiteMatching(U, V, edges);
        System.out.println("Maximum Bipartite Matching = " + maxMatch);
    }
}
