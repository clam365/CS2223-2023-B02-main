package edu.wpi.cs2223.algorithms.week6;
import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;

/**
 * Adjacency list backed implementation of a DiGraph.
 */
public class BasicDiGraph implements DiGraph{
    final int numberOfVertices;
    final LinkedListQueue<Integer>[] adjacentVertices;

    int numberOfEdges = 0;

    public BasicDiGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacentVertices = new LinkedListQueue[numberOfVertices];

        for (int a = 0; a < numberOfVertices; a++) {
            adjacentVertices[a] = new LinkedListQueue<>();
        }
    }

    @Override
    public void addEdge(int fromVertex, int toVertex) {
        adjacentVertices[fromVertex].enqueue(toVertex);
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

    @Override
    public DiGraph reverse() {
        BasicDiGraph reversedDiGraph = new BasicDiGraph(numberOfVertices);
        for (int a = 0; a < numberOfVertices; a++){
            LinkedListQueue<Integer> adjacencies = adjacentVertices[a];
            for (Object adjacency :(Iterable) adjacencies) {
                reversedDiGraph.addEdge((Integer) adjacency, a);
            }
        }

        return reversedDiGraph;
    }
}
