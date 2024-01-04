package edu.wpi.cs2223.algorithms.week2;

public class QuickUnionUF implements UnionFind{

    int[] parents;

    int componentCount;

    @Override
    public void initialize(int size) { // THIS CREATES THE NODES, THEY JUST POINT TO THEMSELVES, NO CONNECTIONS
        parents = new int[size];

        // initialize parents to be the nodes themselves to start - since there are no connections yet
        for (int a = 0; a < parents.length; a++) {
            parents[a] = a;
        }

        componentCount = size;
    }

    @Override
    public int find(int p) { //finding node we want
        int current = parents[p];

        // traverse parent pointers until we get to the root (a node pointing to itself)
        while (current != parents[current]) {
            current = parents[current];
        }

        return current;
    }

    @Override
    public boolean connected(int p, int q) { //checking if there is a connection
        int rootP = find(p);
        int rootQ = find(q);

        return (rootP == rootQ); // if it is connected to the same head
    }

    @Override
    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) { // if it is connected
            return true;
        }

        parents[p] = parents[q]; // if they were not union already, this merges them effectively

        componentCount--; //because we are combining 2 sets of nodes, we combine them into one big one, must subtract
        return false;//false b/c it was not union before doing it
    }

    @Override
    public int componentCount() {
        return componentCount;
    }

    @Override
    public int countOfNextInvocations() {
        // not applicable to this algorithm
        return 0;
    }

    @Override
    public int runningTime(int nodeCount) {

        return nodeCount;
    }
}
