import java.util.*;

public class BreadthFirst {

    public static void main(String args[]) {
        BreadthFirst g = new BreadthFirst(4);
        g.addEdge(0, 1); g.addEdge(0, 2); g.addEdge(1, 2);
        g.addEdge(2, 0); g.addEdge(2, 3); g.addEdge(3, 3);
        g.BFS(2);
    }

    int V; // No. of V
    LinkedList<Integer> adj[]; // Adjacency Lists
    BreadthFirst(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; i++) adj[i] = new LinkedList();
    }

    void addEdge(int v,int w) { adj[v].add(w); }

    void BFS(int s) {
        boolean visited[] = new boolean[V]; // Mark all V as unvisited (false)
        LinkedList<Integer> queue = new LinkedList<>(); // Create a queue
        visited[s]=true; // Mark the current node as visited and enqueue it
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll(); // Dequeue a vertex from queue and print it
            System.out.print(s+" "); // Get all adjacent V of the dequeued vertex s.
            // If a adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}