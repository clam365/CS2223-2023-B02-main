package edu.wpi.cs2223.algorithms.shared;

/**
 *
 * A single-linked node with a generic value type <T>.
 */
public class Node<T> {
    public Node<T> next;
    public T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }
}
