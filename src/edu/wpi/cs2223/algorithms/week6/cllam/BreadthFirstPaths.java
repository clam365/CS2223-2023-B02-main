package edu.wpi.cs2223.algorithms.week6.cllam;

import edu.wpi.cs2223.algorithms.shared.FixedCapacityArrayStack;
import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;
import edu.wpi.cs2223.algorithms.week6.Graph;

import java.util.Iterator;

/**
 * For a given graph and vertex of interest, identifies paths to all other vertices in the graph from that vertex via
 * a breadth first search.
 *
 * 1. The underlying movies and actors are bipartite; an actor can't connect to an actor that makes no sense. Plus in the graph
 *    in the initial assignment shows different colors. There must always be a movie in between actors.
 *
 * 2. DepthFirstPaths would still work instead of BreadthFirstPaths but the implementation would be different of course.
 *    The connections instead could tend to be longer than BFP. BFP ensures our shortest path which is what we want.
 *
 * 3. The running time is O(V+E). We run through the while loop ONCE to go through each vertex,
 *    and the inside foreach loop goes through the edges ONCE also.
 */
public class BreadthFirstPaths {
    final Graph graph;
    final int vertexOfInterest;

    final boolean[] marked;
    final int[] edgeTo;

    public BreadthFirstPaths(Graph graph, int vertexOfInterest) {
        this.graph = graph;
        this.vertexOfInterest = vertexOfInterest;

        this.marked = new boolean[graph.numberOfVertices()];
        this.edgeTo = new int[graph.numberOfVertices()];

        for (int a = 0; a < graph.numberOfVertices(); a++){
            edgeTo[a] = -1;
            marked[a] = false;
        }

        breadthFirstSearch(vertexOfInterest);
    }

    void breadthFirstSearch(int vertex) {

        //Our queue where are enqueuing the fronts
        LinkedListQueue<Integer> iterateThrough = new LinkedListQueue<>();

        //Enqueuing the first vertex/node and marking visiting
        iterateThrough.enqueue(vertex);
        marked[vertex] = true;

        //While loop iterates through each vertex
        while (!iterateThrough.isEmpty()) {
            //Dequeue front
            int front = iterateThrough.dequeue();

            //Visiting the edges and neighbors of front, then enqueuing them while marking that we visited it
            for (int neighbor : graph.adjacentVertices(front)) {
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    edgeTo[neighbor] = front;
                    iterateThrough.enqueue(neighbor);
                }
            }
        }
    }


    /**
     * @return if there is a path from vertexOfInterest to vertex in the graph.
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    /**
     * @return an iterable over the vertices on the path from vertexOfInterest to vertex in the graph.
     */
    public Iterable<Integer> pathTo(int vertex) {
        if (!marked[vertex]) {
            return new FixedCapacityArrayStack<Integer>(0);
        }

        FixedCapacityArrayStack<Integer> pathStack = new FixedCapacityArrayStack<Integer>(graph.numberOfVertices());
        int currentIndex = vertex;

        while (currentIndex != vertexOfInterest) {
            pathStack.push(currentIndex);
            currentIndex=edgeTo[currentIndex];
        }

        pathStack.push(vertexOfInterest);
        return pathStack;
    }
}

