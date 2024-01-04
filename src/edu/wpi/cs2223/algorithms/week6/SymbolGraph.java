package edu.wpi.cs2223.algorithms.week6;

import java.util.HashMap;
import java.util.Map;

/**
 * A wrapper around a Graph implementation that adds support for named vertices.  The underlying graph implementation
 * refers to all vertices via an integer from 0 to V-1, and this wrapper handles all of the mapping to/from
 * a string name of a vertex and its integer id.
 */
public class SymbolGraph {
    final int numberOfVertices;
    BasicGraph basicGraph;

    Map<String, Integer> vertexNameToIndex = new HashMap<>();
    String[] vertexIndexToName;

    public SymbolGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;

        this.basicGraph = new BasicGraph(numberOfVertices);
        this.vertexIndexToName = new String[numberOfVertices];
    }

    int getIndexWithLazyMap(String vertex) {
        if (vertexNameToIndex.containsKey(vertex)) {
            return vertexNameToIndex.get(vertex);
        }

        int index = vertexNameToIndex.size();
        vertexNameToIndex.put(vertex, index);
        vertexIndexToName[index] = vertex;

        return index;
    }

    public void addEdge(String vertexV, String vertexW) {
        int vertexVIndex = getIndexWithLazyMap(vertexV);
        int vertexWIndex = getIndexWithLazyMap(vertexW);

        basicGraph.addEdge(vertexVIndex, vertexWIndex);
    }

    public Graph graph(){
        return basicGraph;
    }

    public String convertIndexToName(int vertex){
        return vertexIndexToName[vertex];
    }

    public int convertNameToIndex(String vertex){
        return vertexNameToIndex.get(vertex);
    }
}
