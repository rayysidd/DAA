package Exp5;

import java.util.*;

class MSTComparison {

    // Prim's Algorithm (Same as before)
    public static void prim(List<List<int[]>> graph, int startVertex) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        int mstWeight = 0;
        List<int[]> mstEdges = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] { 0, startVertex, -1 });

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int weight = edge[0], vertex = edge[1], parent = edge[2];

            if (visited[vertex])
                continue;
            visited[vertex] = true;

            if (parent != -1) {
                mstEdges.add(new int[] { parent, vertex, weight });
                mstWeight += weight;
            }

            for (int[] neighbor : graph.get(vertex)) {
                int neighborVertex = neighbor[0];
                int edgeWeight = neighbor[1];
                if (!visited[neighborVertex]) {
                    pq.add(new int[] { edgeWeight, neighborVertex, vertex });
                }
            }
        }

        System.out.println("Prim's MST Weight: " + mstWeight);
    }

    // Kruskal's Algorithm
    public static void kruskal(int numVertices, List<int[]> edges) {
        edges.sort(Comparator.comparingInt(a -> a[2])); // Sort edges by weight

        int mstWeight = 0;
        List<int[]> mstEdges = new ArrayList<>();
        UnionFind uf = new UnionFind(numVertices);

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];

            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mstEdges.add(new int[] { u, v, weight });
                mstWeight += weight;
            }
        }

        System.out.println("Kruskal's MST Weight: " + mstWeight);
    }

    // Union-Find (Disjoint Set)
    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY])
                    parent[rootY] = rootX;
                else if (rank[rootX] < rank[rootY])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    // Generate Random Graph (Same for Both Algorithms)
    public static List<List<int[]>> generateRandomGraph(int numVertices, int numEdges, List<int[]> edgesList) {
        Random rand = new Random();
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        Set<String> addedEdges = new HashSet<>();

        while (addedEdges.size() < numEdges) {
            int u = rand.nextInt(numVertices);
            int v = rand.nextInt(numVertices);
            int weight = rand.nextInt(100) + 1;

            if (u != v && !addedEdges.contains(u + "," + v)) {
                graph.get(u).add(new int[] { v, weight });
                graph.get(v).add(new int[] { u, weight });
                edgesList.add(new int[] { u, v, weight }); // Store edges for Kruskal
                addedEdges.add(u + "," + v);
                addedEdges.add(v + "," + u);
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        int numVertices = 1000; // Keeping vertices fixed
        int[] edgeCounts = { 5000, 10000, 20000 };

        for (int edges : edgeCounts) {
            System.out.println("\nGraph with " + edges + " edges:");

            List<int[]> edgesList = new ArrayList<>();
            List<List<int[]>> graph = generateRandomGraph(numVertices, edges, edgesList);

            // Run Prim's Algorithm
            long startTime = System.nanoTime();
            prim(graph, 0);
            long endTime = System.nanoTime();
            System.out.println("Prim's Execution Time: " + (endTime - startTime) / 1e6 + " ms");

            // Run Kruskal's Algorithm
            startTime = System.nanoTime();
            kruskal(numVertices, edgesList);
            endTime = System.nanoTime();
            System.out.println("Kruskal's Execution Time: " + (endTime - startTime) / 1e6 + " ms");
        }
    }
}