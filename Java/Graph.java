import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * V - Vertex class
 * E - Edge class has info like {vertex1, vertex2, weight}
 */
public class Graph<V> {

    private Map<V, List<V>> adjacencyMap = new HashMap<>();

    public void addVertex(V vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new ArrayList<>());
            // System.out.println("Added vertext : " + vertex);
        } else {
            System.out.println("Vertex " + vertex + " already exists!");
        }
    }

    public void addEdge(V source, V dest) {
        if (!adjacencyMap.containsKey(source)) {
            System.out.println("Vertex " + source + " is not found!");
        } else if (!adjacencyMap.containsKey(dest)) {
            System.out.println("Vertex " + dest + " is not found!");
        } else {
            adjacencyMap.get(source).add(dest);
            // System.out.println("Edge from vertex(" + source + ") to vertex (" + dest + ") has been added!");
        }
    }

    public void bfs(V source) {
        Deque<V> queue = new ArrayDeque<>();
        Set<V> visited = new HashSet<>();
        queue.offer(source);
        String s = "START => ";
        while (queue.size() > 0) {
            V curr = queue.remove();
            if (visited.contains(curr))
                continue;
            s = s + curr + " => ";
            for (V neighbour : adjacencyMap.get(curr)) {
                if (!visited.contains(neighbour))
                    queue.offer(neighbour);
            }
            visited.add(curr);
        }
        s = s + "END";
        System.out.println("BFS Traversal : " + s);
    }

    public void dfs(V source) {
        System.out.println("DFS Traversal : START => " + dfs(source, new HashSet<V>()) + "END");
    }

    private String dfs(V source, Set<V> visited) {
        String s = "";
        s = s + source + " => ";
        visited.add(source);
        for (V vertex : adjacencyMap.get(source)) {
            if (!visited.contains(vertex))
                s = s + dfs(vertex, visited);
        }
        return s;
    }

    public void print() {
        System.out.println("Adjacency List");
        System.out.println("===============");
        for (V vertex : adjacencyMap.keySet()) {
            System.out.println(vertex + " : " + Arrays.toString(adjacencyMap.get(vertex).toArray()));
        }
    }

    public static void main(String[] args) {
        // Graph with string to reference existing states
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");
        graph.addEdge("C", "A");
        graph.addEdge("C", "D");
        graph.addEdge("D", "C");
        graph.addEdge("B", "E");
        graph.addEdge("E", "B");
        graph.addEdge("D", "E");
        graph.addEdge("E", "D");
        graph.print();
        graph.bfs("E");
        graph.dfs("E");
    }
}