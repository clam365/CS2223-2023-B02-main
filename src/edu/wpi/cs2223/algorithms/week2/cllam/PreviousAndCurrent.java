package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.LinkedNode;

/**
 * Immutable tuple of two {@link LinkedNode} - current & previous.
 */
public class PreviousAndCurrent<T> {
    public final LinkedNode<T> previous;
    public final LinkedNode<T> current;

    public PreviousAndCurrent(LinkedNode<T> previous, LinkedNode<T> current) {
        this.previous = previous;
        this.current = current;
    }
}
