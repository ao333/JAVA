import java.util.*;

public class DFS {

    public static void main(String args[]) {
        DFS g = new DFS(4);
        g.addEdge(0, 1); g.addEdge(0, 2); g.addEdge(1, 2);
        g.addEdge(2, 0); g.addEdge(2, 3); g.addEdge(3, 3);
        g.DepthFirstSearch(2);
    }

    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency Lists
    private DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; i++) adj[i] = new LinkedList();
    }

    private void addEdge(int v,int w) { adj[v].add(w); }

    private void DepthFirstSearch(int v) {
        boolean visited[] = new boolean[V]; // Mark all the vertices as not visited (set as false by default in java)
        Stack(v, visited); // Call the recursive helper function to print DFS traversal
    }

    private void Stack(int v, boolean visited[]) {
        visited[v] = true; // Mark the current node as visited and print it
        System.out.print(v+" ");
        Iterator<Integer> i = adj[v].listIterator(); // Repeat for all the vertices adjacent to this vertex
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) Stack(n, visited);
        }
    }
}