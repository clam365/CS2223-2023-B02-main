package edu.wpi.cs2223.algorithms.week6;

import edu.wpi.cs2223.algorithms.shared.FixedCapacityArrayStack;

/**
 * Determines whether a given graph has any cycles (path of 3 or more nodes that starts and ends at the same node, and
 * does not visit any edges more than once).  If the graph has at least one cycle, this class will return the nodes
 * that make up the first cycle that it finds.
 */
public class CycleFinder {
    final Graph graph;

    final boolean[] marked;
    String cycle = null;

    public CycleFinder(Graph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.numberOfVertices()];

        // marking everything false as initialized, we never visited anything yet
        for (int a = 0; a < graph.numberOfVertices(); a++){
            marked[a] = false;
        }

        // because the graph may not be fully connected, look for cycles starting from every vertex that has not
        // yet been marked
        for (int vertex = 0; vertex < graph.numberOfVertices(); vertex++){
            if (!marked[vertex]) {
                lookForCycle(vertex, new FixedCapacityArrayStack<Integer>(graph.numberOfVertices() + 1));
            }
        }

    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public String firstCycle(){
        return cycle;
    }

    /**
     * The intent of the path stack is to keep track of the path we are on so that if we do find a cycle, we can
     * record its path.
     */
    void lookForCycle(int vertex, FixedCapacityArrayStack<Integer> path) {
        Integer previousNode = path.pop();
        if (previousNode != null) {
            path.push(previousNode);
        }

        path.push(vertex);

        marked[vertex] = true;

        for (int linkedVertex : graph.adjacentVertices(vertex)) {
            if (!marked[linkedVertex]) {
                lookForCycle(linkedVertex, path);
            }
            else {
                if (previousNode == null || linkedVertex != previousNode) {
                    path.push(linkedVertex);
                    if (cycle == null) {
                        cycle = pathToString(path, linkedVertex);
                    }
                    path.pop();
                }
            }
        }
        path.pop();
    }

    /**
     * @return String representation of the vertex path in the path stack.
     */
    String pathToString(FixedCapacityArrayStack<Integer> path, int cycleNode) {
        int cycleNodeSeenTimes = 0;

        String cycle = "";
        while (!path.isEmpty()) {
            int nodeOnPath = path.pop();

            if (nodeOnPath == cycleNode) {
                cycleNodeSeenTimes++;
            }

            cycle = cycle + nodeOnPath + (path.isEmpty() || cycleNodeSeenTimes > 1 ? "" : " - ");

            if (cycleNodeSeenTimes > 1) {
                return cycle;
            }
        }

        return cycle;
    }
}
