package edu.wpi.cs2223.algorithms.week6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleFinderTest {
    @Test
    public void noCycle_linear() {
        Graph graph = new BasicGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertFalse(cycleFinder.hasCycle());
        assertNull(cycleFinder.cycle);
    }

    @Test
    public void noCycle_tree() {
        Graph graph = new BasicGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertFalse(cycleFinder.hasCycle());
        assertNull(cycleFinder.firstCycle());
    }

    @Test
    public void smallCycle() {
        Graph graph = new BasicGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertTrue(cycleFinder.hasCycle());
//        assertEquals("0 - 2 - 1 - 0", cycleFinder.firstCycle());
    }

    @Test
    public void farOffCycle() {
        Graph graph = new BasicGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(2, 6);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertTrue(cycleFinder.hasCycle());
//        assertEquals("2 - 6 - 5 - 2", cycleFinder.firstCycle());
    }

    @Test
    public void allCycle() {
        Graph graph = new BasicGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertTrue(cycleFinder.hasCycle());
        assertEquals("0 - 2 - 1 - 0", cycleFinder.firstCycle());
    }

    @Test
    public void multiCycle() {
        Graph graph = new BasicGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(1, 3);

        CycleFinder cycleFinder = new CycleFinder(graph);

        assertTrue(cycleFinder.hasCycle());
        assertEquals("0 - 4 - 3 - 2 - 1 - 0", cycleFinder.firstCycle());
    }
}