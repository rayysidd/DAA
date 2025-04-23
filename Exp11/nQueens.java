public class nQueens {
    static int N = 8;
    static int[][] board = new int[N][N];

    public static void main(String[] args) {
        if (solveNQueens(0)) {
            printBoard();
        } else {
            System.out.println("No solution exists");
        }
    }

    static boolean solveNQueens(int row) {
        if (row == N)
            return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solveNQueens(row + 1))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static void printBoard() {
        for (int[] row : board) {
            for (int cell : row)
                System.out.print((cell == 1 ? "Q " : ". "));
            System.out.println();
        }
    }
}
