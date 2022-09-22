public class Code07_PalindromeSubsequence {
    // 题目:
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    // 给定一个字符串str, 返回这个字符串的最长回文子序列长度
    // 比如: str = "a12b3c43def2ghi1kpm"
    // 最长回文子序列是 "1234321" 或者 "123c321" , 返回长度7

    // 思路: str1 = "a12b3c43def2ghi1kpm"
    //      str2 = "mpk1ihg2fed34c3b21a"
    //      可以转换为Code06中求两个的最长公共子序列, 这个序列就是最长回文子序列

    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    // str[L..R]最长回文子序列长度返回
    private static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1; // base case
        }

        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }

        // X L  X R str[L..R] -> str[L+1..R-1]
        int p1 = f(str, L+1, R-1);
        int p2 = f(str, L, R-1);
        int p3 = f(str, L+1, R);
        int p4 = str[L] != str[R] ? 0 : (f(str, L+1, R-1) + 2);

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // L 0..N-1
        // R 0..N-1
        char[] str = s.toCharArray();
        int N = s.length();
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N-1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = str[i] == str[i+1] ? 2 : 1;
        }

        for (int L = N-3; L >= 0; L--) {
            for (int R = L+2; R < N; R++) {
                int p1 = dp[L+1][R-1];
                int p2 = dp[L][R-1];
                int p3 = dp[L+1][R];
                int p4 = str[L] != str[R] ? 0 : (dp[L+1][R-1] + 2);

                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return dp[0][N-1];
    }

    public static int lpsl3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // L 0..N-1
        // R 0..N-1
        char[] str = s.toCharArray();
        int N = s.length();
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N-1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = str[i] == str[i+1] ? 2 : 1;
        }

        for (int L = N-3; L >= 0; L--) {
            for (int R = L+2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R-1], dp[L+1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], dp[L+1][R-1] + 2);
                }
            }
        }

        return dp[0][N-1];
    }

    public static void main(String[] args) {
        int lpsl1 = lpsl1("bbbab");
        System.out.println(lpsl1);

        int lpsl2 = lpsl2("bbbab");
        System.out.println(lpsl2);
    }
}

// a12b3c43def2ghi1kpm
// bbbab
