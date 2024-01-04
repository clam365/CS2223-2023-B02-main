package edu.wpi.cs2223.algorithms.week6;

/**
 * Representation of a set of vertices connected pairwise by (undirected) edges.
 */
public interface Graph {
    /**
     * Create an edge from vertex V to vertex W, and by implication from W to V.
     */
    void addEdge(int v, int w);

    Iterable<Integer> adjacentVertices(int v);

    /**
     * @return an iterator over all of the vertices that are adjacent to vertex V.
     *
    Iterable<Integer> adjacentVertices(int v);

    /**
     * @return number of vertices in this graph.
     */
    int numberOfVertices();

    /**
     * @return number of edges in this graph.
     */
    int numberOfEdges();
}
