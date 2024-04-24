import java.util.*;

public class Graph {
    int vertices;
    Map<Integer, Set<Integer>> adjacencyList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new HashMap<>();
        for (int i = 1; i <= vertices; i++) adjacencyList.put(i, new HashSet<>());
    }

    void addEdge(int node, int neighbour) {
        adjacencyList.get(node).add(neighbour);
    }

    void findShortestPath(int source, int destination) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        List<Integer> parents = new ArrayList<>();
        List<Integer> distanceFromSource = new ArrayList<>();

        for (int i = 0; i <= vertices; i++) {
            parents.add(null);
            distanceFromSource.add(null);
        }

        parents.set(source, source);
        distanceFromSource.set(source, 0);
        toVisit.add(source);

        while (!toVisit.isEmpty()) {
            int currentNode = toVisit.remove();
            if (!visited.contains(currentNode)) {
                for (Integer neighbor : adjacencyList.get(currentNode)) {
                    if (!visited.contains(neighbor) && !toVisit.contains(neighbor)) {
                        parents.set(neighbor, currentNode);
                        distanceFromSource.set(neighbor, distanceFromSource.get(currentNode) + 1);
                        toVisit.add(neighbor);
                    }
                }
                visited.add(currentNode);
            }
        }

        StringBuilder path = new StringBuilder(destination + "");
        int parent = parents.get(destination);
        path.insert(0, parent + " -> ");

        do {
            parent = parents.get(parent);
            path.insert(0, parent + " -> ");
        } while (parent != source);

        System.out.println("Shortest path with distance " + distanceFromSource.get(destination) + " is " + path);
    }

    public static void main(String[] args) {
        Graph g = new Graph(10);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);

        g.addEdge(2, 1);
        g.addEdge(2, 5);

        g.addEdge(3, 1);
        g.addEdge(3, 6);
        g.addEdge(3, 7);

        g.addEdge(4, 1);
        g.addEdge(4, 8);

        g.addEdge(5, 2);
        g.addEdge(5, 9);

        g.addEdge(6, 3);
        g.addEdge(6, 10);

        g.addEdge(7, 3);

        g.addEdge(8, 4);

        g.addEdge(9, 5);

        g.addEdge(10, 6);

        g.findShortestPath(9, 10);

    }
}
