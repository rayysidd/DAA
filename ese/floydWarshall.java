package ese;

public class floydWarshall {
    static int inf = 10000000;

    public static void main(String[] args) {
        int[][] graph = {
                { 0, 3, inf, 7 },
                { 8, 0, 2, inf },
                { 5, inf, 0, 1 },
                { 2, inf, inf, 0 }
        };
        floydWarshall(graph);
    }

    public static void floydWarshall(int[][] graph) {
        int v = graph.length;
        int[][] dist = new int[v][v];
        int[][] next = new int[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != inf && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (dist[i][k] != inf && dist[k][j] != inf && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Detect negative cycles
        for (int i = 0; i < v; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print shortest distances
        System.out.println("Shortest Distance Matrix:");
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (dist[i][j] == inf)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

}
