package edu.wpi.cs2223.algorithms.week2;

import edu.wpi.cs2223.algorithms.week2.cllam.LinkedNodeInstrumentor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple container for the values returned by the {@link LinkedNodeInstrumentor}.
 *
 * @param <T> the type of {@link LinkedNode} being instrumented.
 */
public class InstrumentorResult<T> {
    public final LinkedNode<T> head;
    public final AtomicInteger nextCounter;

    public final int linkedListSize;

    public InstrumentorResult(LinkedNode<T> head, AtomicInteger nextCounter, int linkedListSize) {
        this.head = head;
        this.nextCounter = nextCounter;
        this.linkedListSize = linkedListSize;
    }
}
