package ese;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class prim {
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

        prim(graph, 0);
    }

    public static void prim(List<List<int[]>> graph, int startVertex) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        // arr : weight,currnode,source
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] { 0, startVertex, -1 });
        int mstWeight = 0;
        List<int[]> mstEdges = new ArrayList<>();

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int weight = temp[0], vertex = temp[1], source = temp[2];

            if (visited[vertex])
                continue;

            visited[vertex] = true;

            if (source != -1) {
                mstEdges.add(new int[] { source, vertex, weight });
                mstWeight += weight;
            }

            for (int[] neighbor : graph.get(vertex)) {
                int neighborVertex = neighbor[0];
                int edgeweight = neighbor[1];
                if (!visited[neighborVertex]) {
                    pq.add(new int[] { edgeweight, neighborVertex, vertex });
                }
            }
        }
        System.out.println(mstWeight);
    }
}
