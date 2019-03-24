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
    private Map<V, Map<V, Integer>> weightMap = new HashMap<>();
    private Map<V, V> parent = new HashMap<>();
    private Map<V, Integer> level = new HashMap<>();

    public void addVertex(V vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            adjacencyMap.put(vertex, new ArrayList<>());
            weightMap.put(vertex, new HashMap<>());
            // System.out.println("Added vertext : " + vertex);
        } else {
            System.out.println("Vertex " + vertex + " already exists!");
        }
    }

    public void addEdge(V source, V dest, int weight) {
        if (!adjacencyMap.containsKey(source)) {
            System.out.println("Vertex " + source + " is not found!");
        } else if (!adjacencyMap.containsKey(dest)) {
            System.out.println("Vertex " + dest + " is not found!");
        } else {
            adjacencyMap.get(source).add(dest);
            weightMap.get(source).put(dest, weight);
            // System.out.println("Edge from vertex(" + source + ") to vertex (" + dest + ") has been added!");
        }
    }

    public void addEdge(V source, V dest) {
        addEdge(source, dest, 1);
    }

    public void bfsBasic(V source) {
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

    public void bfs(V source) {
      String s = "START => " + source + " => ";
      Map<V, V> parent = new HashMap<>();
      parent.put(source, null);
      Map<V, Integer> level = new HashMap<>();
      int currLevel = 0;
      level.put(source, currLevel);
      Set<V> frontier = new HashSet<>();
      Set<V> next;
      frontier.add(source);
      currLevel++;
      while (frontier.size() > 0) {
        next = new HashSet<>();
        for (V vertex : frontier) {
          for (V childVertex : adjacencyMap.get(vertex)) {
            if (!level.containsKey(childVertex)) {
              parent.put(childVertex, vertex);
              level.put(childVertex, currLevel);
              s = s + childVertex + " => ";
              next.add(childVertex);
            }
          }
        }
        frontier = next;
        currLevel++;
      }
      s = s + "END";
      System.out.println("BFS Traversal : " + s);
    }

    public void dfs() {
        parent = new HashMap<>();
        String s = "START => ";
        for (V source : adjacencyMap.keySet()) {
            if (!parent.containsKey(source)) {
                parent.put(source, null);
                s = s + dfs(source, parent);
            }
        }
        s = s + "END";
        System.out.println("DFS Traversal : " + s);
    }

    public void dfs(V source) {
        parent.put(source, null);
        System.out.println("DFS Traversal : START => " + dfs(source, parent) + "END");
    }

    private String dfs(V source, Map<V, V> parent) {
        String s = "";
        s = s + source + " => ";
        for (V vertex : adjacencyMap.get(source)) {
            if (!parent.containsKey(vertex)) {
                parent.put(vertex, source);
                s = s + dfs(vertex, parent);
            }
        }
        return s;
    }

    int time = 0;
    public void classifyEdges() {
        parent = new HashMap<>();
        Map<V, Integer> timeMap = new HashMap<>(); // Stores the time at which vertex v was last visited
        Set<V> connectedSet = new HashSet<>();
        time = 0;
        for (V source : adjacencyMap.keySet()) {
            if (!parent.containsKey(source)) {
                parent.put(source, null);
                dfsEdgeClassify(source, timeMap, connectedSet);
                connectedSet.addAll(parent.keySet());
            }
        }
    }

    private void dfsEdgeClassify(V source, Map<V,Integer> timeMap, Set<V> preConnectedSet) {
        time = time + 1;
        timeMap.put(source, time);
        for (V child : adjacencyMap.get(source)) {
            if (parent.containsKey(child)) {
                if (preConnectedSet.contains(child)) {
                    System.out.println("Edge (" + source + ", " + child + ") is a cross edge!");
                } else if (timeMap.get(child) <= timeMap.get(source)) {
                    System.out.println("Edge (" + source + ", " + child + ") is a backward edge!");
                } else {
                    System.out.println("Edge (" + source + ", " + child + ") is a forward edge!");
                }
            } else {
                System.out.println("Edge (" + source + ", " + child + ") is a tree edge!");
                parent.put(child, source);
                dfsEdgeClassify(child, timeMap, preConnectedSet);
            }
        }
        time = time + 1;
        timeMap.put(source, time);
    }

    // Graph needs to be a DAG - Directed Acyclic Graph
    public void topologicalSort() {
        dfs();
        parent = new HashMap<>();
        List<V> priority = new ArrayList<>();
        for (V source : adjacencyMap.keySet()) {
            if (!parent.containsKey(source)) {
                parent.put(source, null);
                topologicalSort(source, priority);
            }
        }
        String s = "START => ";
        for (int index = priority.size()-1; index >= 0; index--) {
            s = s + priority.get(index) + " => ";
        }
        s = s + "END";
        System.out.println("Topological Sorted Flow : " + s);
    }

    private void topologicalSort(V source, List<V> priority) {
        for (V child : adjacencyMap.get(source)) {
            if (!parent.containsKey(child)) {
                parent.put(child, source);
                topologicalSort(child, priority);
            }
        }
        priority.add(source);
    }

    public void print() {
        System.out.println("Adjacency List");
        System.out.println("===============");
        for (V vertex : adjacencyMap.keySet()) {
            System.out.println(vertex + " : " + Arrays.toString(adjacencyMap.get(vertex).toArray()));
        }
    }

    public void shortestPath(V source) {
        Map<V, Integer> minPath = new HashMap<>();
        Map<V, V> pred = new HashMap<>();
        minPath.put(source, 0);
        pred.put(source, source);
        Deque<V> queue = new ArrayDeque<>();
        queue.offer(source);
        Set<V> visited = new HashSet<>();
        while (queue.size() > 0) {
            V vertex = queue.remove();
            for (V child : adjacencyMap.get(vertex)) {
                if (visited.contains(child)) {
                    if (minPath.get(child).compareTo(weightMap.get(vertex).get(child) + minPath.get(vertex)) > 0) {
                        minPath.put(child, weightMap.get(vertex).get(child) + minPath.get(vertex));
                        pred.put(child, vertex);
                    }
                    queue.offer(child);
                } else {
                    minPath.put(child, weightMap.get(vertex).get(child) + minPath.get(vertex));
                    pred.put(child, source);
                    queue.offer(child);
                }
            }
            visited.add(vertex);
        }
        for (V vertex : minPath.keySet()) {
            System.out.println("Shortest Path Dist from " + source + " to " + vertex + " : " + minPath.get(vertex));
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
        // graph.addVertex("F");
        graph.addEdge("A", "B", 4);
        graph.addEdge("C", "B", 2);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "D", 4);
        graph.addEdge("B", "E", 4);
        graph.addEdge("D", "E", 4);
        // graph.addEdge("A", "B");
        // graph.addEdge("A", "D");
        // graph.addEdge("B", "E");
        // graph.addEdge("E", "D");
        // graph.addEdge("D", "B");
        // graph.addEdge("C", "E");
        // graph.addEdge("C", "F");
        // graph.addEdge("F", "F");
        // graph.print();
        // graph.addVertex("undershorts");
        // graph.addVertex("pants");
        // graph.addVertex("belt");
        // graph.addVertex("shirt");
        // graph.addVertex("tie");
        // graph.addVertex("jacket");
        // graph.addVertex("watch");
        // graph.addVertex("socks");
        // graph.addVertex("shoes");
        // graph.addEdge("undershorts", "pants");
        // graph.addEdge("undershorts", "socks");
        // graph.addEdge("pants", "shoes");
        // graph.addEdge("pants", "belt");
        // graph.addEdge("belt", "jacket");
        // graph.addEdge("shirt", "tie");
        // graph.addEdge("tie", "jacket");
        // graph.addEdge("shirt", "belt");
        // graph.addEdge("socks", "shoes");

        // graph.bfs("A");
        // graph.dfs("A");
        // graph.classifyEdges();
        // graph.dfs();
        // graph.topologicalSort();
        graph.shortestPath("A");
    }
}
