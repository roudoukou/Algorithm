import java.util.*;

public class Demo02 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 判断二叉树是否是搜索二叉树
    // 判断是否是搜索二叉树, 中序遍历看有无逆序
    // 左节点 < 父节点 < 右节点
    public static int preValue = Integer.MIN_VALUE;

    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBST = checkBST(head.left);
        if (!isLeftBST) {
            return false;
        }

        if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }

        return checkBST(head.right);
    }

    public static boolean checkBST2(Node head) {
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i-1).value >= inOrderList.get(i).value) {
                return false;
            }
        }
        return true;
    }

    public static void process2(Node head, List<Node> inOrderList) {
        if (head == null) {
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);
        process2(head.right, inOrderList);
    }

    public static boolean checkBST3(Node head) {
        if (head != null) {
            int preValue = Integer.MIN_VALUE;
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (head.value <= preValue) {
                        return false;
                    } else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            }
        }

        return true;
    }

    public static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnData process3(Node x) {
        if (x == null) {
            return null;
        }
        ReturnData leftData = process3(x.left);
        ReturnData rightData = process3(x.right);

        int max = x.value;
        int min = x.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }

        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.min >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.max <= x.value)) {
            isBST = false;
        }

//        boolean isBST = false;
//        if (    (leftData != null ? (leftData.isBST && leftData.min < x.value) : true)
//                &&
//                (rightData != null ? (rightData.isBST && rightData.max > x.value) : true)
//        ) {
//            isBST = true;
//        }

        return new ReturnData(isBST, min, max);
    }

    public static boolean checkBST4(Node head) {
        return process3(head).isBST;
    }


    // 判断二叉树是否是完全二叉树
    // CBT是完全二叉树
    // 就是二叉树的最底层 排满或者排不满, 但是左边有, 右边没有的树
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node l = null;
        Node r = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;

            // 如果遇到了不双全的节点之后, 又发现当前节点居然有孩子
            if ( ((leaf) && (l != null || r != null)) || (l == null && r != null) ) {
                return false;
            }

            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }

        return true;
    }


    // 判断二叉树是否是满二叉树
    public static boolean isF(Node head) {
        if (head == null) {
            return true;
        }

        Info data = process4(head);
        return (data.nodes == ((1 << data.height) - 1));
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info process4(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }

        Info leftData = process4(x.left);
        Info rightData = process4(x.right);

        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;

        return new Info(height, nodes);
    }


    // 判断二叉树是否是平衡二叉树
    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }

        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;

        return new ReturnType(isBalanced, height);
    }

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(4);
        head.right = new Node(8);
        head.left.left = new Node(3);
        head.left.right = new Node(5);
        head.right.left = new Node(7);
        head.right.right = new Node(9);

        System.out.println(checkBST(head));
        System.out.println(checkBST2(head));
        System.out.println(checkBST3(head));
        System.out.println(checkBST4(head));


        System.out.println(isCBT(head));

        System.out.println(isBalanced(head));

        System.out.println(isF(head));
    }
}
