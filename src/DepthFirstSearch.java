public class DepthFirstSearch<T> extends Search<T> {
    public DepthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);
        dfs(graph, source);
    }

    private void dfs(WeightedGraph<T> graph, T current) {
        marked.add(current);
        count++;
        for (T v : graph.adjacencyList(current)) {
            edgeTo.put(v, current);
            if (!marked.contains(v)) {
                dfs(graph, v);
            }
        }
    }

}
