import java.util.*;

public class travellingSalesman {
    static int N = 4;
    static int[][] cost = {
            { Integer.MAX_VALUE, 10, 15, 20 },
            { 10, Integer.MAX_VALUE, 35, 25 },
            { 15, 35, Integer.MAX_VALUE, 30 },
            { 20, 25, 30, Integer.MAX_VALUE }
    };

    static int minCost = Integer.MAX_VALUE;
    static List<Integer> bestPath = new ArrayList<>();

    public static void main(String[] args) {
        boolean[] visited = new boolean[N];
        visited[0] = true;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        tsp(0, 1, 0, path, visited);
        System.out.println("Minimum Cost: " + minCost);
        System.out.println("Path: " + bestPath);
    }

    static void tsp(int curr, int count, int costSoFar, List<Integer> path, boolean[] visited) {
        if (count == N && cost[curr][0] != Integer.MAX_VALUE) {
            int totalCost = costSoFar + cost[curr][0];
            if (totalCost < minCost) {
                minCost = totalCost;
                bestPath = new ArrayList<>(path);
                bestPath.add(0);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && cost[curr][i] != Integer.MAX_VALUE) {
                visited[i] = true;
                path.add(i);
                int bound = costSoFar + cost[curr][i];
                if (bound < minCost)
                    tsp(i, count + 1, bound, path, visited);
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
