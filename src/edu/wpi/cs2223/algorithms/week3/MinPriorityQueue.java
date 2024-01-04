package edu.wpi.cs2223.algorithms.week3;

/**
 * Minimum Priority Queue.
 *
 * @param <T> type of comparable elements stored in the priority queue.
 */
public interface MinPriorityQueue<T extends Comparable> {
    /**
     * Insert a new value into the priority queue.
     */
    void insert(T value);

    /**
     * Remove the single, minimal value from the queue.
     */
    T removeMin();

    /**
     * @return true iff the queue is empty.
     */
    boolean isEmpty();

    /**
     * @return number of elements currently in the queue
     */
    int size();
}
