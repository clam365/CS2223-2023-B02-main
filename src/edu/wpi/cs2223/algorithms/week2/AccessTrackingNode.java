package edu.wpi.cs2223.algorithms.week2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An implementation of {@link LinkedNode} that supports tracking of the number of times that
 * next is called across all linked nodes.
 */
public class AccessTrackingNode<T> implements LinkedNode<T> {
    /**
     * AtomicInteger in Java serves two purposes:
     * 1. primarily they are a thread-safe structure that permit, safe, concurrent access from multiple
     * Java threads.
     * 2. since Java passes arguments by value, Atomic structures give us a way for the updates (increments
     * in our case) from inside the method call to be reflected - since the value is not being updated, but the
     * state inside of it is.
     */
    AtomicInteger nextCounter;
    LinkedNode<T> next;
    T value;

    public AccessTrackingNode(AtomicInteger nextCounter, T value, AccessTrackingNode<T> next) {
        this.nextCounter = nextCounter;

        this.value = value;
        this.next = next;
    }

    @Override
    public LinkedNode<T> next() {
        nextCounter.incrementAndGet();
        return next;
    }

    @Override
    public T value() {
        return value;
    }

    public void updateNext(LinkedNode<T> next) {
        this.next = next;
    }
}
