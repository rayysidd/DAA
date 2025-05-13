package ese;

import java.util.ArrayList;
import java.util.List;

import ese.kruskal.UnionFind;

public class kruskal {
    public static void main(String[] args) {
        int v = 4;
        // arr : source,dest,weight
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }

        // Edge 0-1 weigth 10
        graph.get(0).add(new int[] { 1, 10 });
        graph.get(1).add(new int[] { 0, 10 });

        // Edge 0-2 weight 6
        graph.get(0).add(new int[] { 2, 6 });
        graph.get(2).add(new int[] { 0, 6 });

        // Edge: 0 - 3 (weight 5)
        graph.get(0).add(new int[] { 3, 5 });
        graph.get(3).add(new int[] { 0, 5 });

        // Edge: 1 - 3 (weight 15)
        graph.get(1).add(new int[] { 3, 15 });
        graph.get(3).add(new int[] { 1, 15 });

        // Edge: 2 - 3 (weight 4)
        graph.get(2).add(new int[] { 3, 4 });
        graph.get(3).add(new int[] { 2, 4 });
        kruskal(graph);
    }

    public static void kruskal(List<List<int[]>> graph) {
        List<int[]> edges = new ArrayList<>();
        int n = graph.size();
        for (int u = 0; u < n; u++) {
            for (int[] adj : graph.get(u)) {
                int v = adj[0], w = adj[1];
                if (u < v) {
                    edges.add(new int[] { u, v, w });
                }
            }
        }
        UnionFind uf = new UnionFind(n);
        edges.sort((a, b) -> a[2] - b[2]);

        List<int[]> mst = new ArrayList<>();
        int mstWeight = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (uf.find(u) != uf.find(v)) {
                mst.add(new int[] { u, v, w });
                mstWeight += w;
                uf.union(u, v);
            }

        }
        System.out.println("Total weight: " + mstWeight);
    }

    static class UnionFind {
        int[] rank, parent;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY)
                return;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
