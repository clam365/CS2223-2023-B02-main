package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.AccessTrackingNode;
import edu.wpi.cs2223.algorithms.week2.LinkedNode;
import edu.wpi.cs2223.algorithms.week2.UnionFind;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Math;

import static java.lang.Math.log;


/**
 * An implementation of the union find algorithm where the union operation is still quick, but has been
 * optimized to be intelligent in how it combines components - by re-parenting the smaller component
 * into the larger one instead of vice versa - this ensures that the size of components is as balanced as
 * possible.
 *
 * 1. Running time of find: O(logN) b/c it's binary search for an equal element
 *    Running time of connected: 2logN + 1
 *      - finding rootP and rootQ separately through find and then the extra constant time
 *    Running time of Union: 2logN + 1
 *      - using find and then just doing comparisons to find it
 *
 * 2. It maintains better balance in tree structure, and it doesn't get messy and long.
 *    The trees don't get as tall and thus fewer iterations to be completed.
 *
 */
public class WeightedQuickUnionUF implements UnionFind {
    // a convenience array that enables to locate the linked node of interest based
    // on its integer value
    LinkedNode<Integer>[] nodes;
    AtomicInteger nextCounter;

    // TODO: implement maintainance of componentSize and componentCount
    int[] componentSize;

    int componentCount;

    @Override
    public void initialize(int size) {
        nextCounter = new AtomicInteger(0);

        nodes = ((LinkedNode<Integer>[]) new LinkedNode[size]);
        componentSize = new int[size];

        componentCount = size;

        for (int a = 0; a < size; a++){
            nodes[a] = new AccessTrackingNode<>(nextCounter, a, null);
            componentSize[a] = 1;
        }
    }

    @Override
    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)  {
            return true;
        }

        if (componentSize[rootP] < componentSize[rootQ]) {
            nodes[rootP] = nodes[rootQ];
            componentSize[rootQ] += componentSize[rootP];
        }

        else {
            nodes[rootQ] = nodes[rootP];
            componentSize[rootP] += componentSize[rootQ];
        }

        componentCount--;
        return false;
    }

    @Override
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return (rootP == rootQ);
    }

    @Override
    public int find(int p) {
        while (p != nodes[p].value()) {
            p = nodes[p].value();
        }
        return p;
    }

    @Override
    public int componentCount() {
        return componentCount;
    }

    public int countOfNextInvocations(){
        return nextCounter.get();
    }

    public int runningTime(int elementCount) {
        double elementSize = (double) elementCount;
        double expected = 2 * Math.log(elementSize);
        return (int)expected;
    }
}
