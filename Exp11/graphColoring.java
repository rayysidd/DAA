public class graphColoring {
    static int V = 4;
    static int[][] graph = {
            { 0, 1, 1, 1 },
            { 1, 0, 1, 0 },
            { 1, 1, 0, 1 },
            { 1, 0, 1, 0 }
    };
    static int[] color;
    static int m = 3;

    public static void main(String[] args) {
        color = new int[V];
        if (graphColoring(0)) {
            printColors();
        } else {
            System.out.println("No solution exists.");
        }
    }

    static boolean graphColoring(int v) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                color[v] = c;
                if (graphColoring(v + 1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    static boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c)
                return false;
        }
        return true;
    }

    static void printColors() {
        for (int i = 0; i < V; i++)
            System.out.println("Vertex " + i + " ---> Color " + color[i]);
    }
}
