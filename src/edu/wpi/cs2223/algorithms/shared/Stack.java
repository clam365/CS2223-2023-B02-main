package edu.wpi.cs2223.algorithms.shared;

/**
 * Simple, generic LIFO (last in first out) stack.
 *
 * @param <T> the type of elements stored in the stack.
 */
public interface Stack<T> {
    /**
     * Add an element to the top of the stack.
     * @param value element to be added.
     */
    void push(T value);

    /**
     * @return the element on the top of the stack; null if the stack is empty.
     */
    T pop();

    /**
     * @return true iff (if and only if) the stack is empty.
     */
    boolean isEmpty();
}
