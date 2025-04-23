import java.util.*;

class Edge {
    int from, to, weight;

    Edge(int u, int v, int w) {
        this.from = u;
        this.to = v;
        this.weight = w;
    }
}

public class Johnson {
    static final int INF = Integer.MAX_VALUE;

    static int[] bellmanFord(List<Edge> edges, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        // Check for negative weight cycle
        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                return null; // Negative cycle detected
            }
        }

        return dist;
    }

    static int[] dijkstra(List<List<Edge>> adj, int src, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { src, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[] { v, dist[v] });
                }
            }
        }

        return dist;
    }

    static int[][] johnson(int V, List<Edge> edges) {
        // Step 1: Add dummy node (vertex V) with 0-weight edges to all vertices
        List<Edge> newEdges = new ArrayList<>(edges);
        for (int i = 0; i < V; i++) {
            newEdges.add(new Edge(V, i, 0));
        }

        // Step 2: Run Bellman-Ford from dummy vertex
        int[] h = bellmanFord(newEdges, V + 1, V);
        if (h == null) {
            throw new RuntimeException("Graph contains a negative weight cycle.");
        }

        // Step 3: Reweight all edges to ensure non-negative weights
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (Edge edge : edges) {
            int newWeight = edge.weight + h[edge.from] - h[edge.to];
            adj.get(edge.from).add(new Edge(edge.from, edge.to, newWeight));
        }

        // Step 4: Run Dijkstra from each vertex
        int[][] result = new int[V][V];
        for (int u = 0; u < V; u++) {
            int[] dist = dijkstra(adj, u, V);
            for (int v = 0; v < V; v++) {
                if (dist[v] < INF) {
                    result[u][v] = dist[v] + h[v] - h[u]; // Adjust back to original weights
                } else {
                    result[u][v] = INF;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int V = 5;
        List<Edge> edges = List.of(
                new Edge(0, 1, -1),
                new Edge(0, 2, 4),
                new Edge(1, 2, 3),
                new Edge(1, 3, 2),
                new Edge(1, 4, 2),
                new Edge(3, 2, 5),
                new Edge(3, 1, 1),
                new Edge(4, 3, -3));
        int[][] shortestPaths = johnson(V, edges);
        // Print result 
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print((shortestPaths[i][j] == INF ? "INF" : shortestPaths[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
