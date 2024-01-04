package edu.wpi.cs2223.algorithms.week7;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KruskalsAlgorithmTest {
    @Test
    public void distancesFromLecture(){
        EdgeWeightedGraph graph = new EdgeWeightedGraph(10);
        graph.addEdge(new Edge(0, 1, 6));
        graph.addEdge(new Edge(1, 2, 20));
        graph.addEdge(new Edge(2, 3, 18));
        graph.addEdge(new Edge(0, 4, 12));
        graph.addEdge(new Edge(4, 5, 14));
        graph.addEdge(new Edge(5, 6, 13));
        graph.addEdge(new Edge(4, 1, 10));
        graph.addEdge(new Edge(2, 5, 7));
        graph.addEdge(new Edge(3, 6, 5));
        graph.addEdge(new Edge(1, 5, 9));
        graph.addEdge(new Edge(4, 7, 8));
        graph.addEdge(new Edge(8, 7, 16));
        graph.addEdge(new Edge(8, 9, 17));
        graph.addEdge(new Edge(5, 8, 11));
        graph.addEdge(new Edge(6, 9, 15));
        graph.addEdge(new Edge(3, 9, 21));

        KruskalsAlgorithm kruskalsAlgorithm = new KruskalsAlgorithm(graph);

        Set<Edge> mstEdges = new HashSet<>();
        for (Edge edge : kruskalsAlgorithm.mst()){
            mstEdges.add(edge);
        }

        assertEquals(9, mstEdges.size());
        assertTrue(mstEdges.contains(new Edge(0, 1, 6)));
        assertTrue(mstEdges.contains(new Edge(1, 4, 10)));
        assertTrue(mstEdges.contains(new Edge(4, 7, 8)));
        assertTrue(mstEdges.contains(new Edge(1, 5, 9)));
        assertTrue(mstEdges.contains(new Edge(2, 5, 7)));
        assertTrue(mstEdges.contains(new Edge(5, 8, 11)));
        assertTrue(mstEdges.contains(new Edge(5, 6, 13)));
        assertTrue(mstEdges.contains(new Edge(3, 6, 5)));
        assertTrue(mstEdges.contains(new Edge(9, 6, 15)));
    }
}