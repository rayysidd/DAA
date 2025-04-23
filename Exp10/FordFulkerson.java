import java.util.*;

class FordFulkerson {

    // BFS to find if there's an augmenting path from source to sink
    static boolean bfs(int[][] graph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < graph.length; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[t];
    }

    // Ford-Fulkerson implementation using BFS
    public static int fordFulkerson(int[][] graph, int source, int sink) {
        int u, v;
        int[][] rGraph = new int[graph.length][graph.length];

        // Create residual graph
        for (u = 0; u < graph.length; u++) {
            for (v = 0; v < graph.length; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        int[] parent = new int[graph.length]; // stores path
        int maxFlow = 0;

        // Augment flow while a path exists
        while (bfs(rGraph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            // Find minimum capacity in the found path
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Update residual capacities
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Add flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        // Sample flow network as adjacency matrix
        int[][] graph = {
                { 0, 16, 13, 0, 0, 0 },
                { 0, 0, 10, 12, 0, 0 },
                { 0, 4, 0, 0, 14, 0 },
                { 0, 0, 9, 0, 0, 20 },
                { 0, 0, 0, 7, 0, 4 },
                { 0, 0, 0, 0, 0, 0 }
        };

        int source = 0;
        int sink = 5;

        int maxFlow = fordFulkerson(graph, source, sink);
        System.out.println("Maximum Flow = " + maxFlow);
    }
}
