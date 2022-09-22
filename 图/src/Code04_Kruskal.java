import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Code04_Kruskal {
    // 最小生成树
    // 要求图连通, 并且权值最小
    // 常见两种算法, K算法, P算法
    // K算法 以边出发, 对边进行排个序, 依次选择最小的边, 看会不会形成环, 如果不会形成环加起来, 如果形成环了就忽略这条边

    // 这里判断是否成环的方式是通过集合来判断
    public static class MySets {
        public HashMap<Node, List<Node>> setMap;
        public MySets(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet; // 判断两集合地址是否相同, 如果相同, 说明两个集合是一样的
        }

        // 把from集合 和 to集合合并
        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);

            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet); // 把to集合中的节点指向from集合中
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        return null;
    }


    // 复制的并查集代码, 还没测试, 不晓得对不对
//    https://github.com/algorithmzuo/algorithmbasic2020/blob/master/src/class14/Code05_UnionFind.java
    //	public static class Node<V> {
    //		V value;
    //
    //		public Node(V v) {
    //			value = v;
    //		}
    //	}
    //
    //	public static class UnionFind<V> {
    //		public HashMap<V, Node<V>> nodes;
    //		public HashMap<Node<V>, Node<V>> parents;
    //		public HashMap<Node<V>, Integer> sizeMap;
    //
    //		public UnionFind(List<V> values) {
    //			nodes = new HashMap<>();
    //			parents = new HashMap<>();
    //			sizeMap = new HashMap<>();
    //			for (V cur : values) {
    //				Node<V> node = new Node<>(cur);
    //				nodes.put(cur, node);
    //				parents.put(node, node);
    //				sizeMap.put(node, 1);
    //			}
    //		}
    //
    //		// 给你一个节点，请你往上到不能再往上，把代表返回
    //		public Node<V> findFather(Node<V> cur) {
    //			Stack<Node<V>> path = new Stack<>();
    //			while (cur != parents.get(cur)) {
    //				path.push(cur);
    //				cur = parents.get(cur);
    //			}
    //			while (!path.isEmpty()) {
    //				parents.put(path.pop(), cur);
    //			}
    //			return cur;
    //		}
    //
    //		public boolean isSameSet(V a, V b) {
    //			return findFather(nodes.get(a)) == findFather(nodes.get(b));
    //		}
    //
    //		public void union(V a, V b) {
    //			Node<V> aHead = findFather(nodes.get(a));
    //			Node<V> bHead = findFather(nodes.get(b));
    //			if (aHead != bHead) {
    //				int aSetSize = sizeMap.get(aHead);
    //				int bSetSize = sizeMap.get(bHead);
    //				Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
    //				Node<V> small = big == aHead ? bHead : aHead;
    //				parents.put(small, big);
    //				sizeMap.put(big, aSetSize + bSetSize);
    //				sizeMap.remove(small);
    //			}
    //		}
    //
    //		public int sets() {
    //			return sizeMap.size();
    //		}
    //
    //	}
}
