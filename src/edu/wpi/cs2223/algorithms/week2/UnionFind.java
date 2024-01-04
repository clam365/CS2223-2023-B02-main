package edu.wpi.cs2223.algorithms.week2;

/**
 * An implementation of the Union Find algorithm for the dynamic connectivity problem.
 */
public interface UnionFind {

    /**
     * Initialized the algorithm with size components.  Where the components are identified by
     * integers 0 through (size - 1).
     */
    void initialize (int size);

    /**
     * Creates a connection between p and node q.
     * @return true iff these nodes were already connected; false otherwise
     */
    boolean union(int p, int q);

    /**
     * @return true iff node p and node q are connected; false otherwise
     */
    boolean connected(int p, int q);

    /**
     * @return the component identifier for node p.
     */
    int find(int p);

    /**
     * @return the number of distinct components.  A component is a set of connected nodes.  Components
     * are distinct if they are not connected to each other (none of the nodes in component A are connected
     * to any of the nodes in component B).
     */
    int componentCount();

    /**
     * @return number of times next was called across all backing {@link LinkedNode} nodes of the implementation.
     */
    int countOfNextInvocations();

    /**
     * @return expected running time as a function of the number of nodes.
     */
    int runningTime(int nodeCount);


}
