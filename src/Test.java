import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
//        System.out.println(new StringBuffer(s).reverse().toString()); // 逆序


        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }

        int cnt = (int)(Math.random()*100); // 循环的次数
        for (int i = 0; i < cnt; i++) {
            Collections.shuffle(list);
            list.forEach(System.out::print);
            System.out.println();
        }

        System.out.println(cnt);
    }
}
