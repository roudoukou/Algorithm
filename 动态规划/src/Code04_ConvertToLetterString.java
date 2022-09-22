public class Code04_ConvertToLetterString {
    // 题目:
    // 规定1和A对应, 2和B对应, 3和C对应, ... , 26和Z对应
    // 那么一个数字字符串比如"111" 就可以转化为: "AAA", "KA", "AK"
    // 给定一个只有数字字符组成的字符串str, 返回有多少中转化结果

    // str只含有数字字符0~9
    // 返回多少中转换方案
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        return process(str.toCharArray(), 0);
    }

    // str[0 .. i-1]转换无需过问
    // str[i...]去转换, 返回有多少中转换方法
    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1; // base case
        }

        // i没到最后, 说明有字符
        if (str[i] == '0') {
            return 0;
        }

        // str[i] != '0'
        // 可能性一, i单转
        int ways = process(str, i+1);
        if (i+1 < str.length && (str[i] - '0') * 10 + str[i] - '0' < 27) {
            ways += process(str, i+2);
        }

        return ways;
    }

    public static int dpWay(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();

        // 0 - N
        int N = s.length();
        int[] dp = new int[N+1];
        dp[N] = 1;
        for (int i = N-1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i+1];
                if (i+1 < str.length && (str[i] - '0') * 10 + str[i] - '0' < 27) {
                    ways += dp[i+2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(number("111"));
        System.out.println(dpWay("111"));
    }
}
