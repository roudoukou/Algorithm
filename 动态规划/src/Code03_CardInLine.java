public class Code03_CardInLine {

    // 给定一个整型数组arr, 代表数值不同的纸牌排成一条线
    // 玩家A个玩家B一次拿走每张纸牌
    // 规定玩家A先按, 玩家B后拿
    // 但是每个玩家每次只能拿最左或最右的纸牌
    // 玩家A和玩家B都绝顶聪明
    // 请返回最后获胜者的分数

    // 根据规则, 返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length-1);
        int second = g1(arr, 0, arr.length-1);
        return Math.max(first, second);
    }

    // arr[L..R], 先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        if (L == R) { // base case 如果只剩余一张牌, 那么直接给先手这张牌
            return arr[L];
        }
        // 剩余的牌不止一张

        int p1 = arr[L] + g1(arr, L+1, R);
        int p2 = arr[R] + g1(arr, L, R-1);

        return Math.max(p1, p2);
    }

    public static int g1(int[] arr, int L, int R) {
        if (L == R) { // base case
            return 0;
        }

        int p1 = f1(arr, L+1, R);
        int p2 = f1(arr, L, R-1);

        return Math.min(p1, p2);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int first = f2(arr, 0, arr.length-1, fmap, gmap);
        int second = g2(arr, 0, arr.length-1, fmap, gmap);
        return Math.max(first, second);
    }

    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {

        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }

        int ans = 0;

        if (L == R) { // base case 如果只剩余一张牌, 那么直接给先手这张牌
            ans = arr[L];
        } else {
            // 剩余的牌不止一张
            int p1 = arr[L] + g2(arr, L+1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R-1, fmap, gmap);
            ans = Math.max(p1, p2);
        }

        fmap[L][R] = ans;

        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {

        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }

        int ans = 0;

        if (L == R) { // base case
            return 0;
        } else {
            int p1 = f2(arr, L+1, R, fmap, gmap);
            int p2 = f2(arr, L, R-1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }

//        int L = arr.length;
//        int R = arr.length;
//        for (int i = 0; i < L-1; i++) {
//            for (int j = i+1; j < R; j++) {
//                if (i < j) {
//                    fmap[i][j] = Math.max(arr[i] + gmap[i+1][j], arr[j] + gmap[i][j-1]);
//                    gmap[i][j] = Math.min(fmap[i+1][j], fmap[i][j-1]);
//                }
//            }
//        }

        // 不能够使用117行-126行代码来填充这个fmap和gmap两个dp表
        // 因为填充参数是从每一条副对角线开始填充的
        // 上面的代码是一行一行的填充的, 填充的方式不一样
        // 通俗点说, 上面的按照行的方式填充dp表的, 但是这个dp表需要按照副对角线的方式来填充

        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R =startCol;
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L+1][R], arr[R] + gmap[L][R-1]);
                gmap[L][R] = Math.min(fmap[L+1][R], fmap[L][R-1]);
                L++;
                R++;
            }
        }

        return Math.max(fmap[0][N-1], gmap[0][N-1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        int win1 = win1(arr);
        int win2 = win2(arr);
        int win3 = win3(arr);
        System.out.println(win1);
        System.out.println(win2);
        System.out.println(win3);
    }
}
