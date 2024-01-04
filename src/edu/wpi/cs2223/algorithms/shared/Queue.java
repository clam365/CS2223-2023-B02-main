package edu.wpi.cs2223.algorithms.shared;

/**
 * Simple, generic FIFO (first in first out) queue.
 *
 * @param <T> the type of elements stored in the queue.
 */
public interface Queue<T> {
    /**
     * Add an element to the back of the queue.
     * @param value element to be added.
     */
    void enqueue(T value);

    /**
     * @return the element at the front of the queue; null if the queue is empty.
     */
    T dequeue();

    /**
     * @return true iff (if and only if)  the queue is empty.
     */
    boolean isEmpty();
}
