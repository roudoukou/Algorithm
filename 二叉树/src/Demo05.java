import java.util.LinkedList;
import java.util.Queue;

public class Demo05 {
    // 序列化与反序列化

    public static class Node {
        public int value;
        public Node right;
        public Node left;

        public Node(int value) {
            this.value = value;
        }
    }

    // 序列化
    // 以head为头的树, 请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 反序列化
    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    // 先序方式序列化
    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(1);
        head.left.left = null;
        head.left.right = new Node(1);
        head.left.right.left = null;
        head.left.right.right = null;
        head.right = null;

        System.out.println(serialByPre(head));
        Node node = reconByPreString(serialByPre(head));

        System.out.println(serialByPre(node));


    }
}
