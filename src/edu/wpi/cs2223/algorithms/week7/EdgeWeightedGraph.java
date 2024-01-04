package edu.wpi.cs2223.algorithms.week7;

import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;

/**
 * Representation of an undirected graph with weighted edges.
 */
public class EdgeWeightedGraph {
    final int numberOfVertices;
    final LinkedListQueue<Edge>[] adjacentVertices;

    int numberOfEdges = 0;

    public EdgeWeightedGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacentVertices = new LinkedListQueue[numberOfVertices];

        for (int a = 0; a < numberOfVertices; a++) {
            adjacentVertices[a] = new LinkedListQueue<>();
        }
    }

    public void addEdge(Edge edge) {
        int sideOne = edge.either();
        int sideTwo = edge.other(sideOne);

        adjacentVertices[sideOne].enqueue(edge);
        adjacentVertices[sideTwo].enqueue(edge);

        numberOfEdges++;
    }

    public Iterable<Edge> adjacentVertices(int v) {
        return (Iterable<Edge>) adjacentVertices[v];
    }

    public int numberOfVertices() {
        return numberOfVertices;
    }

    public int numberOfEdges() {
        return numberOfEdges;
    }
}
