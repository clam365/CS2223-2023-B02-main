package edu.wpi.cs2223.algorithms.week6;

import org.junit.jupiter.api.Test;

import edu.wpi.cs2223.algorithms.week6.cllam.BreadthFirstPaths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstPathsTest {

    @Test
    public void findsShortestPath_adjacent() {
        Graph graph = new BasicGraph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        DepthFirstPaths depthPaths = new DepthFirstPaths(graph, 0);
        Iterator<Integer> depthPathToFive = depthPaths.pathTo(5);

        int depthPathToFiveLength = 0;
        while (depthPathToFive.hasNext()){
            depthPathToFiveLength++;
            depthPathToFive.next();
        }

        assertTrue(depthPathToFiveLength > 2);

        BreadthFirstPaths breadthPaths = new BreadthFirstPaths(graph, 0);
        Iterable<Integer> breadthPathToFive = breadthPaths.pathTo(5);
        Iterable<Integer> breadthPathToFour = breadthPaths.pathTo(4);

        Iterator<Integer> breadthPathToFourIterator = breadthPathToFour.iterator();

        assertEquals(0, breadthPathToFourIterator.next());
        assertEquals(2, breadthPathToFourIterator.next());
        assertEquals(4, breadthPathToFourIterator.next());
        assertFalse(breadthPathToFourIterator.hasNext());

    }

    @Test
    public void findsShortestPath_lectureGraph() {
        Graph graph = new BasicGraph(7);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(3, 4);
        graph.addEdge(5, 3);
        graph.addEdge(5, 4);
        graph.addEdge(6, 4);

        DepthFirstPaths depthPaths = new DepthFirstPaths(graph, 0);
        Iterator<Integer> depthPathToFour = depthPaths.pathTo(4);

        int depthPathToFourLength = 0;
        while (depthPathToFour.hasNext()){
            depthPathToFourLength++;
            depthPathToFour.next();
        }

        assertTrue(depthPathToFourLength > 2);

        BreadthFirstPaths breadthPaths = new BreadthFirstPaths(graph, 0);
        Iterable<Integer> breadthPathToFour = breadthPaths.pathTo(4);

        Iterator<Integer> breadthPathToFourIterator = breadthPathToFour.iterator();

        assertEquals(0, breadthPathToFourIterator.next());
        assertEquals(5, breadthPathToFourIterator.next());
        assertEquals(4, breadthPathToFourIterator.next());
        assertFalse(breadthPathToFourIterator.hasNext());
    }
}

