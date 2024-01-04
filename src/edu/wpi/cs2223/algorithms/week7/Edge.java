package edu.wpi.cs2223.algorithms.week7;

import java.util.HashSet;
import java.util.Set;

/**
 * Weighted edge implementation.
 */
public class Edge implements Comparable<Edge>{
    final int v;
    final int w;
    final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }

    /**
     * @return one of the vertices of the edge.
     */
    public int either() {
        return v;
    }

    /**
     * @return the vertex of this edge that is not v.
     */
    public int other(int v){
        return (this.v == v) ? w : v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        Set<Integer> theseEdges = new HashSet<>();
        theseEdges.add(v);
        theseEdges.add(w);

        Set<Integer> thoseEdges = new HashSet<>();
        thoseEdges.add(edge.v);
        thoseEdges.add(edge.w);

        if (!theseEdges.equals(thoseEdges)) {
            return false;
        }

        return Double.compare(weight, edge.weight) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 31 * v + 31 * w;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
