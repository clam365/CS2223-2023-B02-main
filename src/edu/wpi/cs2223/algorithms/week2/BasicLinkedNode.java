package edu.wpi.cs2223.algorithms.week2;

/**
 * Simple implementation of the generic {@link LinkedNode}.
 *
 * @param <T>
 */
public class BasicLinkedNode<T> implements LinkedNode<T>{
    T value;
    LinkedNode<T> next;

    public BasicLinkedNode(T value, BasicLinkedNode<T> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public LinkedNode<T> next() {
        return next;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public void updateNext(LinkedNode<T> next) {
        this.next = next;
    }
}
