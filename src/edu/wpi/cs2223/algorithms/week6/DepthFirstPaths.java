package edu.wpi.cs2223.algorithms.week6;

import edu.wpi.cs2223.algorithms.shared.FixedCapacityArrayStack;
import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;

import java.util.Iterator;

/**
 * In a given graph, identifies paths from a vertex of interest to every other vertex in the graph.
 */
public class DepthFirstPaths {
    final Graph graph;
    final int vertexOfInterest;

    final boolean[] marked;
    final int[] edgeTo;

    public DepthFirstPaths(Graph graph, int vertexOfInterest) {
        this.graph = graph;
        this.vertexOfInterest = vertexOfInterest;

        this.marked = new boolean[graph.numberOfVertices()];
        this.edgeTo = new int[graph.numberOfVertices()];

        for (int a = 0; a < graph.numberOfVertices(); a++){
            edgeTo[a] = -1;
            marked[a] = false;
        }

        depthFirstSearch(vertexOfInterest);
    }

    void depthFirstSearch(int vertex) {
        marked[vertex] = true;
        for (int linkedVertex : graph.adjacentVertices(vertex)) {
            if (!marked[linkedVertex]) {
                depthFirstSearch(linkedVertex);
                edgeTo[linkedVertex] = vertex;
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterator<Integer> pathTo(int vertex) {
        if (!marked[vertex]) {
            return new FixedCapacityArrayStack<Integer>(0).iterator();
        }

        FixedCapacityArrayStack<Integer> pathStack = new FixedCapacityArrayStack<Integer>(graph.numberOfVertices());
        int currentIndex = vertex;

        while (currentIndex != vertexOfInterest) {
            pathStack.push(currentIndex);
            currentIndex=edgeTo[currentIndex];
        }

        pathStack.push(vertexOfInterest);
        return pathStack.iterator();
    }
}
