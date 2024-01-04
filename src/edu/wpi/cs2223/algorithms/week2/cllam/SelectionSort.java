package edu.wpi.cs2223.algorithms.week2.cllam;

import edu.wpi.cs2223.algorithms.week2.InstrumentorResult;
import edu.wpi.cs2223.algorithms.week2.LinkedNode;
import edu.wpi.cs2223.algorithms.week2.cllam.PreviousAndCurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generic, Singly-linked list implementation of the SelectionSort algorithm.
 */
public class SelectionSort<T extends Comparable<T>> {
    /**
     * AtomicInteger in Java serves two purposes:
     * 1. primarily they are a thread-safe structure that permit, safe, concurrent access from multiple
     * Java threads.
     * 2. since Java passes arguments by value, Atomic structures give us a way for the updates (increments
     * in our case) from inside the method call to be reflected - since the value is not being updated, but the
     * state inside of it is.
     */
    AtomicInteger nextCounter;
    LinkedNode<T> head;
    LinkedNode<T> accessTrackingHead;

    public SelectionSort(LinkedNode<T> head) { //Constructor
        this.head = head;

        InstrumentorResult<T> instrumentorResult = new LinkedNodeInstrumentor<T>().instrument(head);
        this.nextCounter = instrumentorResult.nextCounter;
        this.accessTrackingHead = instrumentorResult.head;
    }

    /**
     * Implementation of the time complexity of this algorithm.  Given the number of elements in the input
     * linked list, this method should return the (rough) expected number of invocations to the "next" operation
     * of the nodes in that list.
     *
     * @param elementCount number of nodes in input list
     * @return number of invocations to next across all nodes of that list necessary to get the list into its
     * sorted form.
     */
    public int runningTime(int elementCount) {
        int expected = (elementCount * elementCount);
        //runs on O(n^2) time
        return expected;
    }

    /**
     * Applies Selection Sort to the list that this instance was initialized with.  The implementation should
     * perform the sort in place - that is, after the constructor of this implementation creates a chain of
     * AccessTrackingNode headed by accessTrackingHead, the implementation of this method should move nodes around
     * in this chain rather than creating new nodes or a new chain.
     *
     * @return reference to the node that is the head of the sorted chain.
     *
     * (Attempted at Problem 1)
     *
     * 1. Run Time of My Program is in N^2. Has nested while loops to iterate and find minNode and keep going through to place.
     *
     * 2. The running time does not very based on what the input values are. Even if it is in correct order, it has to parse through
     *    everything for "potential minimum values" always.
     *
     * 3. doesn't run :(
     *
     * 4. Base Case : T(1) = 1^2 = 1
     *    want to imply T(N+1)
     *
     *    T(N+1) = (n+1)^2
     *           = n^2 + 2n + 1
     *           = T(N) + 2n + 1
     *
     *    By mathematical induction, T(N) = N^2 holds true for base case n=1 and also holds true for T(N+1)
     */

    public LinkedNode<T> sort() {
        //Setup
        LinkedNode<T> current = accessTrackingHead;
        LinkedNode<T> prevOfCurrent = null;
        LinkedNode<T> prevOfMinValue;
        LinkedNode<T> minValue;
        LinkedNode<T> next;
        LinkedNode<T> prevOfNext;

        // Base Case of Empty; No Nodes at all
        if (current == null) {
            return null;
        }

        // Base Case of Singular Linked List
        if (current.next() == null) {
            return accessTrackingHead;
        }

        // Starting off minValue with first node
        next = current.next();
        prevOfNext = current;
        minValue = current;
        prevOfMinValue = null;

        // Finding minimum value
        while (next != null){
            if (next.value().compareTo(minValue.value()) < 0){
                prevOfMinValue = prevOfNext;
                minValue = next;
            }
            prevOfNext = next;
            next = next.next(); //iterating through
        }

        // no need to reorder the linked list, only update pointers
        if (current.value().compareTo(minValue.value()) == 0) {
            accessTrackingHead = current;
        }

        else {
            // reorder the linked list
            current.updateNext(minValue.next());
            accessTrackingHead = minValue;
            accessTrackingHead.updateNext(current);
        }
        prevOfCurrent = current;
        current = current.next();

        // Implementation after first iteration
        while (current.next() != null) {

            minValue = current; // first iteration b/c it's the first one
            prevOfMinValue = prevOfCurrent; //would be null on first iteration

            next = current.next();
            prevOfNext = current;

            // Finding the minimum value
            while (next.next() != null) {

                // Comparison to find minimum value
                if (next.value().compareTo(minValue.value()) <= 0) {
                    minValue = next;
                    prevOfMinValue = prevOfNext;
                }
                // Updating values
                prevOfNext = next; //keeping track of previous of Min Value in each iteration
                next = next.next(); //moving onto next iteration to find possible minValue
            }

            // Swapping values

            // Case where minimum value / node is in the right place; move on
            if (minValue.value().equals(current.value())) {
                break;
            }

            // General case of swapping pointers;
            current.updateNext(minValue.next());
            prevOfCurrent.updateNext(minValue);
            minValue.updateNext(current);

            // updating pointers big loop (moving onto next increment of current)
            prevOfCurrent = current;
            current = current.next();
        }
        return accessTrackingHead;
    }


    /**
     * @return the number of times that next has been called across all linked nodes by this instance of the
     * selection sort algorithm.
     */
    public int countOfNextInvocations() {
        return nextCounter.get();
    }
}
