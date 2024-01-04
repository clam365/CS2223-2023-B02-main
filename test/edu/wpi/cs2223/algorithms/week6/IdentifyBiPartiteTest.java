package edu.wpi.cs2223.algorithms.week6;

import org.junit.jupiter.api.Test;
import edu.wpi.cs2223.algorithms.week6.cllam.IdentifyBiPartite;
import static org.junit.jupiter.api.Assertions.*;

class IdentifyBiPartiteTest {
    @Test
    public void smallNotBipartiteGraph(){
        Graph graph = new BasicGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);

        IdentifyBiPartite identifyBiPartite = new IdentifyBiPartite(graph);
        assertFalse(identifyBiPartite.isBiPartite());
    }

    @Test
    public void smallBipartiteGraph(){
        Graph graph = new BasicGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        IdentifyBiPartite identifyBiPartite = new IdentifyBiPartite(graph);
        assertTrue(identifyBiPartite.isBiPartite());
    }

    @Test
    public void noBipartiteGraphOne() {
        Graph graph = new BasicGraph(8);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(4, 7);
        graph.addEdge(5, 7);

        IdentifyBiPartite identifyBiPartite = new IdentifyBiPartite(graph);
        assertFalse(identifyBiPartite.isBiPartite());
    }

    @Test
    public void bipartiteGraphTwo() {
        Graph graph = new BasicGraph(7);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);

        IdentifyBiPartite identifyBiPartite = new IdentifyBiPartite(graph);
        assertTrue(identifyBiPartite.isBiPartite());

        assertTrue(identifyBiPartite.vertexColor(0) == identifyBiPartite.vertexColor(3));
        assertTrue(identifyBiPartite.vertexColor(0) == identifyBiPartite.vertexColor(4));
        assertTrue(identifyBiPartite.vertexColor(3) == identifyBiPartite.vertexColor(4));

        assertTrue(identifyBiPartite.vertexColor(1) == identifyBiPartite.vertexColor(2));
        assertTrue(identifyBiPartite.vertexColor(1) == identifyBiPartite.vertexColor(5));
        assertTrue(identifyBiPartite.vertexColor(1) == identifyBiPartite.vertexColor(6));
    }

}