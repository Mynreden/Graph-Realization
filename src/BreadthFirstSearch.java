import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T>{

    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T current) {
        marked.add(current);
        Queue<T> queue = new LinkedList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            T v = queue.remove();
            for (T node : graph.adjacencyList(v)) {
                if (!marked.contains(node)) {
                    marked.add(node);
                    edgeTo.put(node, v);
                    queue.add(node);
                }
            }
        }
    }
}
