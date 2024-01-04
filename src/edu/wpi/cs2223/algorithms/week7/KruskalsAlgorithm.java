package edu.wpi.cs2223.algorithms.week7;

import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;
import edu.wpi.cs2223.algorithms.week2.cllam.WeightedQuickUnionUF;
import edu.wpi.cs2223.algorithms.week3.ArrayMinPriorityQueue;
import edu.wpi.cs2223.algorithms.week3.MinPriorityQueue;

import java.util.HashSet;
import java.util.Set;

/**
 * Kruskal's MST algorithm.
 */
public class KruskalsAlgorithm {
    final EdgeWeightedGraph edgeWeightedGraph;

    final MinPriorityQueue<Edge> minEdgesPQ;
    final WeightedQuickUnionUF unionFind;

    final LinkedListQueue<Edge> mstEdges;
    int edgesAdded = 0;

    public KruskalsAlgorithm(EdgeWeightedGraph edgeWeightedGraph) {
        this.edgeWeightedGraph = edgeWeightedGraph;

        this.minEdgesPQ = new ArrayMinPriorityQueue<>(edgeWeightedGraph.numberOfEdges());

        Set<Edge> addedEdges = new HashSet<>();
        for (int vertex = 0; vertex < edgeWeightedGraph.numberOfVertices(); vertex++) {
            for (Edge edge : edgeWeightedGraph.adjacentVertices(vertex)){
                if (!addedEdges.contains(edge)) {
                    minEdgesPQ.insert(edge);
                    addedEdges.add(edge);
                }
            }
        }

        this.unionFind = new WeightedQuickUnionUF();
        unionFind.initialize(edgeWeightedGraph.numberOfEdges);

        this.mstEdges = new LinkedListQueue<>();

        runKruskal();
    }

    void runKruskal(){
        int edgesInMST = 0;

        //bc edges will always be V-1 to avoid cyclic nature
        while (edgesInMST < edgeWeightedGraph.numberOfVertices() -1 ) {
            Edge edge = minEdgesPQ.removeMin();

            int vertexOne = edge.either();
            int vertexTwo = edge.other(vertexOne);

            if (unionFind.union(vertexOne, vertexTwo)){
                continue;
            }

            mstEdges.enqueue(edge);
            unionFind.union(vertexOne, vertexTwo);
            edgesInMST++;
        }
    }

    public Iterable<Edge> mst(){
        return (Iterable<Edge>) mstEdges;
    }
}
