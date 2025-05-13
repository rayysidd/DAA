import java.util.*;

public class VertexCover {
    private static Set<Integer> approximateVertexCover(int[][] edges, int n) {
        Set<Integer> cover = new HashSet<>();
        boolean[] visited = new boolean[n];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (!visited[u] && !visited[v]) {
                cover.add(u);
                cover.add(v);
                visited[u] = true;
                visited[v] = true;
            }
        }

        return cover;
    }

    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 } };
        int n = 5;
        Set<Integer> vertexCover = approximateVertexCover(edges, n);
        System.out.println("Approximate Vertex Cover: " + vertexCover);
    }
}