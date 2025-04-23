import java.util.*;

public class VertexCoverApprox {
    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        // Add edges
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(3);
        graph.get(4).add(5);
        graph.get(5).add(4);

        boolean[] visited = new boolean[V];
        Set<Integer> vertexCover = new HashSet<>();

        for (int u = 0; u < V; u++) {
            if (visited[u])
                continue;
            for (int v : graph.get(u)) {
                if (!visited[v]) {
                    visited[u] = visited[v] = true;
                    vertexCover.add(u);
                    vertexCover.add(v);
                    break;
                }
            }
        }

        System.out.println("Approximate Vertex Cover: " + vertexCover);
    }
}
