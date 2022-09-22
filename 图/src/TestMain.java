public class TestMain {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {1, 2, 0},
                {1, 3, 0},
                {1, 5, 0},
                {3, 1, 0},
                {3, 2, 0},
                {3, 4, 0},
                {4, 3, 0},
                {4, 2, 0},
                {4, 5, 0},
                {2, 1, 0},
                {2, 3, 0},
                {2, 4, 0},
                {2, 5, 0},
                {5, 1, 0},
                {5, 2, 0},
                {5, 4, 0}
        };

        Graph graph = GraphGenerator.createGraph(matrix);
        Code01_BFS.bfs(graph.nodes.get(1));
    }
}
