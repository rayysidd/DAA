package ese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tspApprox {
    static int[][] graph = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
    };
    static int V = 4;

    public static void main(String[] args) {
        List<List<Integer>> mst = mst();
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[V];
        dfs(0, visited, mst, tour);
        tour.add(0);
        int cost = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            cost += graph[tour.get(i)][tour.get(i + 1)];
        }

        System.out.println("Approximate TSP Tour: " + tour);
        System.out.println("Tour Cost: " + cost);
    }

    public static void dfs(int u, boolean[] visited, List<List<Integer>> mst, List<Integer> tour) {
        visited[u] = true;
        tour.add(u);
        for (int v : mst.get(u)) {
            if (!visited[v]) {
                dfs(v, visited, mst, tour);
            }
        }
    }

    public static List<List<Integer>> mst() {
        boolean[] selected = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < V; i++) {
            int u = -1;
            for (int j = 0; j < V; j++) {
                if (!selected[j] && (u == -1 || key[j] < key[u])) {
                    u = j;
                }
            }
            selected[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !selected[v] && (graph[u][v] < key[v])) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }
        List<List<Integer>> mst = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            mst.add(new ArrayList<>());
        }

        for (int i = 1; i < V; i++) {
            mst.get(i).add(parent[i]);
            mst.get(parent[i]).add(i);
        }
        return mst;
    }
}
