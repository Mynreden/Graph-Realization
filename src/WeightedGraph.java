import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private ArrayList<Vertex<T>> list;

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public Vertex<T> addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        this.list.add(vertex);
        return vertex;
    }

    public void addEdge(T source, T dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);


        if (hasEdge(source, dest)
                || source.equals(dest))
            return; // reject parallels & self-loops
        //Vertex<T> vertex = new Vertex<>(source);
        for (Vertex<T> sourceVertex: this.list){
            if (sourceVertex.getData().equals(source)){
                for (Vertex<T> destVertex: this.list){
                    if (destVertex.getData().equals(dest)){
                        sourceVertex.addAdjacentVertices(destVertex, weight);
                        if (undirected) destVertex.addAdjacentVertices(sourceVertex, weight);
                    }
                }
            }
        }
    }

    public int getVerticesCount() {
        return this.list.size();
    }

    public int getEdgesCount() {

        int count = 0;
        for (Vertex<T> v : this.list) {
            count += v.getAdjacentVertices().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(T data) {
        return this.list.contains(new Vertex<T>(data));
    }

    public boolean hasEdge(T source, T dest) {
        if (!hasVertex(source)) return false;
        for (Vertex<T> vertex: this.list){
            if (vertex.getData().equals(source)){
                if (vertex.getAdjacentVertices().containsKey(new Vertex<>(dest))){
                    return true;
                }
            }
        }
        return false;
    }

    public Iterable<T> adjacencyList(T v) {
        if (!hasVertex(v)) return null;
        List<T> vertices = new LinkedList<>();
        for (Vertex<T> vertex : this.list){
            if (vertex.equals(new Vertex<>(v))){
                for (Vertex<T> key: vertex.getAdjacentVertices().keySet()){
                    vertices.add(key.getData());
                }
            }
        }
        return vertices;
    }

    public Iterable<Vertex<T>> getVertices(T v) {
        if (!hasVertex(v)) return null;
        Vertex<T> vertex = getVertex(v);
        List<Vertex<T>> vertices = new LinkedList<>();
        boolean b = vertices.addAll(vertex.getAdjacentVertices().keySet());
        return vertices;
    }

    private Vertex<T> getVertex(T data){
        for (Vertex<T> vertex: this.list){
            if (vertex.getData().equals(data)){
                return vertex;
            }
        }
        return new Vertex<>(data);
    }
}
