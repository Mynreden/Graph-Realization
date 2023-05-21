import java.util.Map;
import java.util.Objects;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data){
        this.data = data;
    }

    public void addAdjacentVertices(Vertex<V> destination, double weight){
        this.adjacentVertices.put(destination, weight);
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(){
        return this.adjacentVertices;
    }

    public void deleteAdjacentVertices(Vertex<V> destination){
        this.adjacentVertices.remove(destination);
    }

    public V getData(){
        return this.data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(data, vertex.data) && Objects.equals(adjacentVertices, vertex.adjacentVertices);
    }
}