public class Code08_HourseJump {
    // 请同学们自行搜索或者想象一个象棋棋盘
    // 然后把整个棋盘放入第一象限, 棋盘的最左下角是(0, 0)位置
    // 那么整个棋盘就是横坐标上9条线, 纵坐标上10条线的区域
    // 给你三个参数x, y, k
    // 返回"马"从(0, 0)位置触发, 必须走k步
    // 最后落在(x, y)上的方法数有多少种?

    /**
     * 当前来到的位置是(x, y)
     * 还剩下rest步还需要条
     * 跳完rest步, 正好调到a, b方法数是多少?
     * 10 * 9
     */


    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int x, int y, int rest, int a, int b) {

        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0; // 越界的情况
        }

        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0; // 步数为0的可能性
        }

        int p1 = process(x + 2, y + 1, rest - 1, a, b);
        int p2 = process(x + 1, y + 2, rest - 1, a, b);
        int p3 = process(x - 1, y + 2, rest - 1, a, b);
        int p4 = process(x - 2, y + 1, rest - 1, a, b);
        int p5 = process(x - 2, y - 1, rest - 1, a, b);
        int p6 = process(x - 1, y - 2, rest - 1, a, b);
        int p7 = process(x + 1, y - 2, rest - 1, a, b);
        int p8 = process(x + 2, y - 1, rest - 1, a, b);

        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }

    public static int dpWay(int a, int b, int k) {

        int[][][] dp = new int[10][9][k+1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int p1 = pick(dp, x + 2, y + 1, rest - 1);
                    int p2 = pick(dp, x + 1, y + 2, rest - 1);
                    int p3 = pick(dp, x - 1, y + 2, rest - 1);
                    int p4 = pick(dp, x - 2, y + 1, rest - 1);
                    int p5 = pick(dp, x - 2, y - 1, rest - 1);
                    int p6 = pick(dp, x - 1, y - 2, rest - 1);
                    int p7 = pick(dp, x + 1, y - 2, rest - 1);
                    int p8 = pick(dp, x + 2, y - 1, rest - 1);

                    dp[x][y][rest] = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
                }
            }
        }

        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0; // 越界的情况
        }
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step));
        System.out.println(dpWay(x, y, step));
    }
}
