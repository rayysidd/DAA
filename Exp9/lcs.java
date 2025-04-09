import java.util.*;

public class lcs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("String 1");
        String s1 = sc.nextLine();
        System.out.println("String 2");
        String s2 = sc.nextLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        System.out.println(rec(dp, s1, s2));

        int i = s1.length();
        int j = s2.length();

        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (dp[i - 1][j] == dp[i][j - 1]) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        System.out.println(sb.reverse().toString());
    }

    public static int rec(int[][] dp, String s1, String s2) {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}