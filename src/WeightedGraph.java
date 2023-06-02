import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> vertices = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public Vertex<T> addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        this.vertices.put(data, vertex);
        return vertex;
    }

    public void addEdge(T source, T dest, double weight) {
        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops
        Vertex<T> sourceVertex = getVertex(source);
        Vertex<T> destVertex = getVertex(dest);
        if (sourceVertex == null || destVertex == null){
            return;
        }
        sourceVertex.addAdjacentVertices(destVertex, weight);
    }

    public int getVerticesCount() {
        return this.vertices.size();
    }

    public int getEdgesCount() {

        int count = 0;
        for (var v : vertices.entrySet()) {
            count += v.getValue().countEdges();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(T data) {
        return this.vertices.containsKey(data);
    }

    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        return vertices.get(source).containEdge(new Vertex<>(dest));
    }

    public Iterable<T> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        List<T> list = new ArrayList<>();
        for (Vertex<T> vertex : vertices.get(v).adjacencyList()){
            list.add(vertex.getData());
        }
        return list;
    }

    public Iterable<Vertex<T>> getVertices(T v) {
        if (!hasVertex(v)) return null;
        return vertices.get(v).adjacencyList();
    }

    private Vertex<T> getVertex(T data){
        if (!hasVertex(data)) return null;
        return vertices.get(data);
    }
}
