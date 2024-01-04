package edu.wpi.cs2223.algorithms.week6;

/**
 * Representation of a set of vertices connected by directed edges from one vertex to another.
 */
public interface DiGraph {
    /**
     * Create an edge from fromVertex (tail) to toVertex (head).
     */
    void addEdge(int fromVertex, int toVertex);

    /**
     * @return an iterator over all of the vertices that are at the head end of a tail edge originating at fromVertex.
     */
    Iterable<Integer> adjacentVertices(int fromVertex);

    /**
     * @return number of vertices in this graph.
     */
    int numberOfVertices();

    /**
     * @return number of edges in this graph.
     */
    int numberOfEdges();

    /**
     * @return a digraph with all of the same vertices as this one but with the direction of all edges flipped (heads
     * become tails and tails become heads).  This operation can be useful to answer the question of "what edges
     * are pointing at a given node", since that question is not immediately answerable from the direct structure
     * of the digraph.
     */
    DiGraph reverse();
}
