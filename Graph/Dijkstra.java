import java.util.*;

public class Dijkstra{

    public static void main(String[] arg) {
        Dijkstra path = new Dijkstra();
        Graph g = new Graph(9);
        g.addEdge(0, 1, 4); g.addEdge(0, 7, 8); g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11); g.addEdge(2, 1, 8); g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4); g.addEdge(2, 3, 7); g.addEdge(3, 2, 7);
        g.addEdge(3, 5, 14); g.addEdge(3, 4, 9); g.addEdge(4, 3, 9);
        g.addEdge(4, 5, 10); g.addEdge(5, 4, 10); g.addEdge(5, 3, 9);
        g.addEdge(5, 2, 4); g.addEdge(5, 6, 2); g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6); g.addEdge(6, 5, 2); g.addEdge(7, 0, 8);
        g.addEdge(7, 8, 7); g.addEdge(7, 1, 11); g.addEdge(7, 6, 1);
        g.addEdge(8, 2, 2); g.addEdge(8, 7, 7); g.addEdge(8, 6, 6);
        path.dijkstra(g.getV(0));
        // Print the minimum Distance.
        for(Vertex v:g.getPath()){
            System.out.print("Vertex "+v+" , Dist - "+ v.shortest +" , Path - ");
            for(Vertex pathvert:v.path) { System.out.print(pathvert+" "); }
            System.out.println(""+v);
        }
    }

    static class Graph {
        ArrayList<Vertex> V;
        Graph(int Vnum){
            V = new ArrayList<>(Vnum);
            for(int i=0; i<Vnum; i++) { V.add(new Vertex(Integer.toString(i))); }
        }
        void addEdge(int src, int dest, int weight){
            Vertex s = V.get(src);
            Edge newEdge = new Edge(V.get(dest),weight);
            s.adj.add(newEdge);
        }
        ArrayList<Vertex> getPath() { return V; }
        public Vertex getV(int vert) { return V.get(vert); }
    }

    static class Vertex implements Comparable<Vertex> {
        String name;
        ArrayList<Edge> adj;
        LinkedList<Vertex> path;
        double shortest = Double.POSITIVE_INFINITY;
        public int compareTo(Vertex other){ return Double.compare(shortest,other.shortest); }
        Vertex(String name){ this.name = name; adj = new ArrayList<>(); path = new LinkedList<>(); }
        public String toString(){ return name; }
    }

    static class Edge {
        Vertex target; double weight;
        Edge(Vertex target, double weight){ this.target = target; this.weight = weight; }
    }

    public void dijkstra(Vertex src) {
        src.shortest = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(src);

        while (!pq.isEmpty()) {
            Vertex u = pq.poll(); // Take the unvisited node with minimum weight.
            // Visit all its adj.
            for (Edge neighbour:u.adj){
                Double newDist = u.shortest + neighbour.weight;
                if (neighbour.target.shortest > newDist){
                    pq.remove(neighbour.target); // Remove the node from the pq to update the distance value.
                    neighbour.target.shortest = newDist;
                    // Take the path visited till now and add the new node s
                    neighbour.target.path = new LinkedList<>(u.path);
                    neighbour.target.path.add(u); // Update the distances for all the adj (In the Priority Queue).
                    //Reenter the node with new distance.
                    pq.add(neighbour.target);
                }
            }
        }
    }
}