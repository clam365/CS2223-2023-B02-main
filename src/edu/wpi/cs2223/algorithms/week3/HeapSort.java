package edu.wpi.cs2223.algorithms.week3;

import edu.wpi.cs2223.algorithms.week2.SortAlgorithm;

/**
 * Implementation of the heap sort algorithm.  This implementation takes advantage of the fact that it
 * is in the same package as our {@link ArrayMinPriorityQueue} implementation and manipulates that state
 * directly.
 */
public class HeapSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public void sort(T[] input) {
        ArrayMinPriorityQueue<T> priorityQueue = new ArrayMinPriorityQueue<>(input);

        // create heap - we can start with the array that is backing our priority queue, and
        // invoke sink on all of the elements, starting from the last one.  This should result in a binary heap.
        // As an optimization, we could skip all elements that don't have any children because they are
        // trivially proper binary heaps already (starting at index input.length / 2 instead of input.length)
        // and working our way backwards..
        // TODO: implement!

        // move elements into position
        // now that we have created our heap, we just need to repeatedly swap the root with the last element,
        // remove that element from further consideration (reduce size of our backing heap by 1), sink our
        // new root to restore the heap, and repeat
        // TODO: implement!
    }

    @Override
    public void exchange(T[] array, int indexOne, int indexTwo) {

    }

    @Override
    public int numberOfExchanges() {
        // TODO: implement
        return 0;
    }

    @Override
    public int numberOfComparisons() {
        // TODO: implement
        return 0;
    }
}
