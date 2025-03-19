import java.util.Arrays;

class BellmanFord {
    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void bellmanFord(int vertices, int edges, Edge[] graph, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax edges |V| - 1 times
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : graph) {
                if (distance[edge.src] != Integer.MAX_VALUE && distance[edge.src] + edge.weight < distance[edge.dest]) {
                    distance[edge.dest] = distance[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative weight cycle
        for (Edge edge : graph) {
            if (distance[edge.src] != Integer.MAX_VALUE && distance[edge.src] + edge.weight < distance[edge.dest]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }

        // Print shortest distances
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t" + distance[i]);
        }
    }

    public static void main(String[] args) {
        int vertices = 5, edges = 8;
        Edge[] graph = {
                new Edge(0, 1, -1), new Edge(0, 2, 4),
                new Edge(1, 2, 3), new Edge(1, 3, 2),
                new Edge(1, 4, 2), new Edge(3, 2, 5),
                new Edge(3, 1, 1), new Edge(4, 3, -3)
        };

        bellmanFord(vertices, edges, graph, 0);
    }
}
