import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {

        for (int cnt = 0; cnt < 100000; cnt++) {
            int i = (int)(Math.random() * 10);
            boolean flag = (int)(Math.random() * 10) % 2 == 0 ? true : false;
            int year = (int)(Math.random() * 2000);
            int f = f(i, flag, year);


            int ans = i * 100 + (2022-year);
            if (!flag) {
                ans--;
            }

            System.out.println("我脑子的数字是: " + i + "\t生日" + flag + year + "\t算出的结果是: " + f + "ans = " + ans);
        }
    }

    public static int f(int n, boolean flag, int year) {
        n = n * 2;
        n = n + 5;
        n = n * 50;

        if (flag) {
            n = n + 1772;
        } else {
            n = n + 1771;
        }

        n = n - year;

        return n;
    }
}
