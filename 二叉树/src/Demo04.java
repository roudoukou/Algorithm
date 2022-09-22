public class Demo04 {
    public static class Node {
        public char value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(char value) {
            this.value = value;
        }
    }

    public static Node getSuccess(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node) { // 当前节点是父亲节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public static void main(String[] args) {

        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');
        A.left = B;
        A.right = C;
        A.parent = null;
        B.left = D;
        B.right = E;
        B.parent = A;
        D.left = null;
        D.right = null;
        D.parent = B;
        E.left = null;
        E.right = null;
        E.parent = B;
        C.left = F;
        C.right = G;
        C.parent = A;
        F.left = null;
        F.right = null;
        F.parent = C;
        G.left = null;
        G.right = null;
        G.parent = C;

        Node node = getSuccess(G);
        if (node != null) { // 处理中序遍历的最后一个节点, 因为最后一个节点的值是null不能够直接输出
            System.out.println(node.value);
        } else {
            System.out.println("null");
        }
    }
}
