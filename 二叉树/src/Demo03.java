import java.util.HashMap;
import java.util.HashSet;

public class Demo03 {
    public static class Node {
        public char value;
        public Node left;
        public Node right;

        public Node(char value) {
            this.value = value;
        }
    }

    // o1 和 o2一定属于head为头的树
    // 返回o1 和 o2 的最低公共祖先

    public static Node lca(Node head, Node o1, Node o2) {
        // 创建一个哈希表, 用来存放每个节点的父节点
        // head的父节点就是head本身自己
        // process() 函数的功能就是用head下面的所有节点递归填充 哈希表
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        process(head, fatherMap);

        HashSet<Node> set1 = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);

        cur = o2;
        while (cur != fatherMap.get(cur)) {
            if (set1.contains(cur)) {
                return cur;
            }
            cur = fatherMap.get(cur);
        }

        return head;
    }

    public static void process(Node head, HashMap<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }

        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);

        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }

    // o1是o2的lca(最低公共祖先), 或者是o2是o1的lca
    // o1与o2不互为lca, 向上查找↑才能够找到lca
    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) { // base case
            // base case就是一个告诉递归什么时候不需要在划分了, 应该结束的一种情况
            // 有了base case, 才不会计算的没完没了,
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Node head = new Node('A');
        head.left = new Node('B');
        head.right = new Node('C');
        head.left.left = new Node('D');
        head.left.right = new Node('E');
        head.left.right.right = new Node('F');
        head.right.right = new Node('K');
        head.right.right.left = new Node('G');
        head.right.right.right = new Node('I');

        Node node = lca(head, head.left.right.right, head.left.right);
        System.out.println(node.value);
        node = lowestAncestor(head, head.left.right.right, head.left.right);
        System.out.println(node.value);
    }
}
