public class Code06_LongestCommonSubsequence {
    // 题目链接
    // https://leetcode.com/problems/longest-common-subsequence
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        return process1(s1, s2, s1.length()-1, s2.length()-1);
    }

    private static int process1(String s1, String s2, int i, int j) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            return str1[i] == str2[j] ? 1 : process1(s1, s2, i, j-1);
        } else if (j == 0) {
            return str1[i] == str2[j] ? 1 : process1(s1, s2, i-1, j);
        } else {
            int p1 = process1(s1, s2, i, j-1);
            int p2 = process1(s1, s2, i-1, j);
            int p3 = str1[i] == str2[j] ? (process1(s1, s2, i-1, j-1)+1 ) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }


    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        // i 0-N
        // j 0-M
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j-1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i-1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i][j-1];
                int p2 = dp[i-1][j];
                int p3 = str1[i] == str2[j] ? (dp[i-1][j-1]+1 ) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[N-1][M-1];
    }


    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence1("abcde", "ace"));
        System.out.println(longestCommonSubsequence2("abcde", "ace"));
    }
}
