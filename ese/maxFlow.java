package ese;

import java.util.LinkedList;
import java.util.Queue;

public class maxFlow {
    public static void main(String[] args) {

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

    public static int fordFulkerson(int[][] graph, int source, int sink) {

        int[][] rGraph = new int[graph.length][graph.length];
        int u = 0, v = 0;
        for (u = 0; u < graph.length; u++) {
            for (v = 0; v < graph.length; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }
        int[] parent = new int[rGraph.length];
        int maxFlow = 0;

        while (bfs(rGraph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static boolean bfs(int[][] graph, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();

        q.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < graph.length; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    visited[v] = true;
                    q.offer(v);
                    parent[v] = u;
                }
            }
        }
        return visited[sink];
    }
}
