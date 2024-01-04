package edu.wpi.cs2223.algorithms.week3;

/**
 * Array backed implementation of {@link MinPriorityQueue}.  This implementation takes advantage of the fact
 * that representing and navigating a balanced binary tree is very straightforward with an array.  For
 * index math convenience, we leave the first element as null and treat the backing array is a one-based
 * index.
 */
public class ArrayMinPriorityQueue<T extends Comparable<T>> implements MinPriorityQueue<T> {

    final int maxSize;
    final T[] nodes;

    int size = 0;

    public ArrayMinPriorityQueue(int maxSize) {
        this.maxSize = maxSize;
        nodes = (T[]) new Comparable[maxSize + 1];

        // first element is a null to make index math easier; root is at nodes[1]
        nodes[0] = null;
    }

    /**
     * Package privaqte constructor made available only for {@link HeapSort} purposes.
     */
    ArrayMinPriorityQueue(T[] arrayForSort) {
        nodes = arrayForSort;

        maxSize = arrayForSort.length - 1;
        size = arrayForSort.length -1 ;
    }

    /**
     * Convenience method for finding the array index of the parent of a given node.
     */
    int parentIndex(int index){
        return index / 2;
    }

    /**
     * Convenience method for finding the array index of the left child of a given node.  Note that this
     * method itself does not check whether the passed-in index represents a node that has a left child.
     */
    int leftChildIndex(int index) {
        return index * 2;
    }

    /**
     * Convenience method for finding the array index of the right child of a given node.  Note that this
     * method itself does not check whether the passed-in index represents a node that has a right child.
     */
    int rightChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * An enumeration of the possible results when looking for a node's smallest child.
     */
    enum SmallestChild {
        NEITHER,
        LEFT,
        RIGHT
    }

    @Override
    public void insert(T value) {
        // special case if we are at capacity
        if (size == maxSize) {
            if (value.compareTo(nodes[1]) <= 0){
                // we are storing maxSize largest values, if we are at capacity, and the new value is smaller than
                // our min, we don't care about it
                return;
            }

            nodes[1] = value;
            sink(1);
            return;
        }

        size++;
        nodes[size] = value;

        swim(size);
    }

    void exchange(int indexOne, int indexTwo) {
        T tempVal = nodes[indexOne];
        nodes[indexOne] = nodes[indexTwo];
        nodes[indexTwo] = tempVal;
    }

    /**
     * Convenience method for checking if a parent of a given node is bigger than that node.  Useful for
     * supporting the swim operation.
     */
    boolean isParentBigger(int childIndex) {
        if (childIndex < 2) {
            return false; // there is no parent
        }

        T child = nodes[childIndex];
        T parent = nodes[parentIndex(childIndex)];

        return (parent.compareTo(child) > 0);
    }

    /**
     * Implements the swim binary heap operation - starting with the node at the given index,
     * repeatedly, until we get to the root, compare the node to its parent and,
     *  * swap them if the parent is bigger (keep going from the node's new location)
     *  * terminate procedure if a parent is found to be smaller
     */
    void swim(int index){
        // TODO: implement!
        int currentIndex = index;
        while(isParentBigger(currentIndex)) {
            int parentIndex = parentIndex(currentIndex);
            exchange(currentIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    /**
     * Convenience method for determining whether:
     *  1. neither of a node's children are smaller than it
     *  2. the left child is smaller than the parent and the right child
     *  3. the right child is smaller than the parent and the left child.
     *
     *  This is useful for supporting the sink operation.
     */
    SmallestChild compareToChildren(int parentIndex) {
        T parent = nodes[parentIndex];

        T leftChild = (leftChildIndex(parentIndex) <= size) ? nodes[leftChildIndex(parentIndex)] : null;
        T rightChild = (rightChildIndex(parentIndex) <= size) ? nodes[rightChildIndex(parentIndex)] : null;

        if (leftChild == null && rightChild == null) {
            return SmallestChild.NEITHER;
        }

        if (leftChild == null) {
            return (rightChild.compareTo(parent) < 0) ? SmallestChild.RIGHT : SmallestChild.NEITHER;
        }

        if (rightChild == null) {
            return (leftChild.compareTo(parent) < 0) ? SmallestChild.LEFT : SmallestChild.NEITHER;
        }

        if (leftChild.compareTo(parent) >= 0 && rightChild.compareTo(parent) >= 0){
            return SmallestChild.NEITHER;
        }

        return (leftChild.compareTo(rightChild) < 0) ? SmallestChild.LEFT : SmallestChild.RIGHT;
    }

    /**
     * Implements the sink binary heap operation.  Starting with the node at the given index,
     * compare it to its two children (if it has any) and:
     *  * if neither child is smaller (or they don't exist), terminate procedure
     *  * if the left child is smaller than the parent and the right child, swap the parent with the left child,
     *  and keep the algorithm going from that location
     *  * if the right child is smaller than the parent and the left child, swap the parent with the right child,
     *  and keep the algorithm going from that location
     */
    void sink(int index) {
        // TODO: implement!
        switch (compareToChildren(index)){
            case LEFT:
                exchange(index, leftChildIndex(index));
                sink(leftChildIndex(index));
                break;
            case RIGHT:
                exchange(index, rightChildIndex(index));
                sink(rightChildIndex(index));
                break;
        }
    }

    @Override
    public T removeMin() {
        if (size == 0) {
            return null;
        }

        T minVal = nodes[1];
        exchange(1, size);

        nodes[size] = null;
        size--;

        sink(1);

        return minVal;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
