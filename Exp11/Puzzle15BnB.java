import java.util.*;

class PuzzleNode implements Comparable<PuzzleNode> {
    int[][] mat;
    int x, y, cost, level;
    PuzzleNode parent;

    PuzzleNode(int[][] mat, int x, int y, int level, PuzzleNode parent) {
        this.mat = new int[4][4];
        for (int i = 0; i < 4; i++)
            this.mat[i] = mat[i].clone();
        this.x = x;
        this.y = y;
        this.level = level;
        this.parent = parent;
        this.cost = level + calculateHeuristic(mat);
    }

    public int compareTo(PuzzleNode other) {
        return this.cost - other.cost;
    }

    static int calculateHeuristic(int[][] mat) {
        int dist = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                int val = mat[i][j];
                if (val != 0) {
                    int ti = (val - 1) / 4, tj = (val - 1) % 4;
                    dist += Math.abs(i - ti) + Math.abs(j - tj);
                }
            }
        return dist;
    }

    boolean isGoal() {
        int[][] goal = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 0 }
        };
        return Arrays.deepEquals(this.mat, goal);
    }
}

public class Puzzle15BnB {
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        int[][] start = {
                { 1, 2, 3, 4 },
                { 5, 6, 0, 8 },
                { 9, 10, 7, 11 },
                { 13, 14, 15, 12 }
        };

        solve(start);
    }

    static void solve(int[][] start) {
        PriorityQueue<PuzzleNode> pq = new PriorityQueue<>();
        PuzzleNode root = new PuzzleNode(start, 1, 2, 0, null);
        pq.offer(root);

        while (!pq.isEmpty()) {
            PuzzleNode node = pq.poll();

            if (node.isGoal()) {
                printPath(node);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newX = node.x + dx[i];
                int newY = node.y + dy[i];

                if (newX >= 0 && newY >= 0 && newX < 4 && newY < 4) {
                    int[][] newMat = new int[4][4];
                    for (int k = 0; k < 4; k++)
                        newMat[k] = node.mat[k].clone();

                    // swap 0 with neighbor
                    newMat[node.x][node.y] = newMat[newX][newY];
                    newMat[newX][newY] = 0;

                    PuzzleNode child = new PuzzleNode(newMat, newX, newY, node.level + 1, node);
                    pq.offer(child);
                }
            }
        }
    }

    static void printPath(PuzzleNode node) {
        if (node == null)
            return;
        printPath(node.parent);
        for (int[] row : node.mat) {
            for (int cell : row)
                System.out.print((cell < 10 ? " " : "") + cell + " ");
            System.out.println();
        }
        System.out.println();
    }
}
