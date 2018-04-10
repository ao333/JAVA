import java.util.*;

public class DepthFirst {

    public static void main(String args[]) {
        DepthFirst g = new DepthFirst(4);
        g.addEdge(0, 1); g.addEdge(0, 2); g.addEdge(1, 2);
        g.addEdge(2, 0); g.addEdge(2, 3); g.addEdge(3, 3);
        g.DFS(2);
    }

    int V; // No. of V
    LinkedList<Integer> adj[]; // Adjacency Lists
    DepthFirst(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; i++) adj[i] = new LinkedList();
    }

    void addEdge(int v,int w) { adj[v].add(w); }

    void DFS(int v) {
        boolean visited[] = new boolean[V]; // Mark all the V as not visited (set as false by default in java)
        Stack(v, visited); // Call the recursive helper function to print DepthFirst traversal
    }

    void Stack(int v, boolean visited[]) {
        visited[v] = true; // Mark the current node as visited and print it
        System.out.print(v+" ");
        Iterator<Integer> i = adj[v].listIterator(); // Repeat for all the V adjacent to this vertex
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) Stack(n, visited);
        }
    }
}