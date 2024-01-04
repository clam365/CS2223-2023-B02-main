package edu.wpi.cs2223.algorithms.week6.cllam;
import edu.wpi.cs2223.algorithms.week6.Graph;
/**
 * Determines if a given graph is bipartite or not.  A graph is bipartite, if its vertices can be divided into two
 * sets such that all edges go from nodes in set one to nodes in set two and there are no intraset edges.
 *
 * 1. In terms of V, the number of vertices in a graph,
 *    and E, the number of edges, what is the running time of your algorithm?
 *
 *    The running time would be V*E, because with the foreach loop that we use
 *    to traverse must go through each vertex (nodes) along with all the edges
 *    (connecting lines).
 *
 */
public class IdentifyBiPartite {
    enum Color {
        RED, GREEN;

        public Color otherColor(){
            return (this == RED) ? GREEN : RED;
        }
    }

    final Graph graph;
    final Color[] colors;

    final boolean isBiPartite;

    public IdentifyBiPartite(Graph graph) {
        this.graph = graph;
        this.colors = new Color[graph.numberOfVertices()];

        for (int a = 0; a < graph.numberOfVertices(); a++) {
            this.colors[a] = null;
        }

        if (graph.numberOfVertices() == 0) {
            isBiPartite = true;
            return;
        }

        isBiPartite = dfsBipartite(0, Color.RED);
    }

    boolean dfsBipartite(int vertex, Color color) {

        // If the vertex is colored, we check if it does have the right one
        if (colors[vertex] != null) {
            return colors[vertex] == color;
        }

        // Coloring the current vertex
        colors[vertex] = color;

        // Traversing adjacent vertices for foreach loop
        // We use recursion to continue through, if not bipartite, return false
        for (int neighbor : graph.adjacentVertices(vertex)) {
            Color nextColor = color.otherColor();

            if (!dfsBipartite(neighbor, nextColor)) {
                return false;
            }
        }

        // If all adjacent vertices are bipartite and passes everything then we get true
        return true;
    }


    public boolean isBiPartite() {
        return isBiPartite;
    }

    public Color vertexColor(int vertex) {
        return colors[vertex];
    }
}
