package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.AccessTrackingNode;
import edu.wpi.cs2223.algorithms.week2.InstrumentorResult;
import edu.wpi.cs2223.algorithms.week2.LinkedNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Instruments {@link LinkedNode} for running time metric tracking.  Specifically, this instrumentor creates a
 * single counter that is augmented every time next is called on any of the nodes in the chain started by
 * head.
 *
 * @param <T>
 */
public class LinkedNodeInstrumentor<T> {
    public InstrumentorResult<T> instrument(LinkedNode<T> head){
        AtomicInteger nextCounter = new AtomicInteger(0);
        LinkedNode<T> accessTrackingHead = null;

        int numberOfNodes = 0;

        // convert input to access tracking nodes
        if (head != null) {
            numberOfNodes++;

            accessTrackingHead = new AccessTrackingNode<>(nextCounter, head.value(), null);
            LinkedNode<T> accessTrackingCurrent = accessTrackingHead;

            LinkedNode<T> inputCurrent = head.next();

            while (inputCurrent != null) {
                numberOfNodes++;

                AccessTrackingNode<T> newNode = new AccessTrackingNode<>(nextCounter, inputCurrent.value(), null);
                accessTrackingCurrent.updateNext(newNode);
                accessTrackingCurrent = newNode;

                inputCurrent = inputCurrent.next();
            }
        }

        return new InstrumentorResult<>(accessTrackingHead, nextCounter, numberOfNodes);
    }
}
