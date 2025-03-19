import java.util.*;

class Dijkstra {
    static class Edge {
        int dest, weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static void dijkstra(Map<Integer, List<Edge>> graph, int source, int vertices) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Edge edge : graph.getOrDefault(u, new ArrayList<>())) {
                int v = edge.dest, weight = edge.weight;
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        System.out.println("Vertex\tShortest Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t" + (distance[i] == Integer.MAX_VALUE ? "∞" : distance[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Map<Integer, List<Edge>> graph = new HashMap<>();

        graph.put(0, Arrays.asList(new Edge(1, 7), new Edge(4, 3)));
        graph.put(1, Arrays.asList(new Edge(2, 5), new Edge(4, 8)));
        graph.put(2, Arrays.asList(new Edge(3, 2)));
        graph.put(3, Arrays.asList(new Edge(0, 4), new Edge(2, 9)));
        graph.put(4, Arrays.asList(new Edge(1, 6), new Edge(2, 1), new Edge(3, 7)));
        dijkstra(graph, 0, vertices);
    }
}
