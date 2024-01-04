package edu.wpi.cs2223.algorithms.week6;

import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;

/**
 * Simple linked list backed adjacency vertex implementation of a graph.
 */
public class BasicGraph implements Graph{
    final int numberOfVertices;
    final LinkedListQueue<Integer>[] adjacentVertices;

    int numberOfEdges = 0;

    public BasicGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacentVertices = new LinkedListQueue[numberOfVertices];

        for (int a = 0; a < numberOfVertices; a++) {
            adjacentVertices[a] = new LinkedListQueue<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        adjacentVertices[v].enqueue(w);
        adjacentVertices[w].enqueue(v);
        numberOfEdges++;
    }

    @Override
    public Iterable<Integer> adjacentVertices(int v) {
        return (Iterable<Integer>) adjacentVertices[v];
    }

    @Override
    public int numberOfVertices() {
        return numberOfVertices;
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }
}
