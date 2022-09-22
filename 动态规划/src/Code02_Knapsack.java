public class Code02_Knapsack {

    // 所有的货, 重量和价值, 都在w和v数组里
    // 为了方便, 其中没有负数
    // bag背包容量, 不能超过这个载重
    // 返回: 不超重的情况下, 能够得到的最大价值

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        // 尝试函数!
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            // 先判断背包容量, 然后再判断index, 注意, 这个两个判断的顺序不能颠倒
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        // 不要当前货物
        int p1 = process(w, v, index+1, bag);

        // 要当前的货物
        int p2 = 0;
        int next = process(w, v, index+1, bag-w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        if (w.length != v.length || w.length == 0 || bag < 0 || w == null || v == null) {
            return 0;
        }

        int N = w.length;
        // index 0 ~ N
        // rest 0 ~ bag
        int[][] dp = new int[N+1][bag+1];
        // dp[N][...] = 0
        // dp填好
        for (int index = N-1; index >=0;  index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index+1][rest-w[index]];
                if (next != -1) {
                    p2 = next + v[index];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weight = {3, 2, 4, 7};
        int[] value = {5, 6, 3, 19};
        int bag = 11;
        int ans1 = maxValue(weight, value, bag);
        int ans2 = dpWay(weight, value, bag);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
