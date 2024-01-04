package edu.wpi.cs2223.algorithms.week1.cllam;

import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;
import edu.wpi.cs2223.algorithms.shared.Queue;
import edu.wpi.cs2223.algorithms.shared.Stack;

/**
 * Stack implementation using Two Linked Node Queues and optimizing Push (putting load on pop)
 *
 * 1. In terms of N, the worst case number of operations when pop is performed is N + N, or 2N.
 *    We still have the same amount of elements when we need to convert from FIFO to LIFO, and we just need
 *    to do 2 loop operations in able to get to the correct order back to back.
 *
 * 2. The most challenging part of developing the pop method was definitely understanding what needs to be done.
 *    In able for pop to work, we'll have the empty base case of course. What we will do next is move all elements
 *    from QueueTwo into QueueOne with a while loop while having a counter for the # of elements.
 *    We will then have a for loop limited by the # of elements and reverse the order of the QueueOne, creating the
 *    LIFO for our state of the REAL QueueTwo Stack.
 *    To get our pop, we'll just get our value and dequeue the element of stack following LIFO.
 **/
public class TwoQueueStackPushOptimized<T> implements Stack<T> {

    final Queue<T> queueOne;
    final Queue<T> queueTwo;

    public TwoQueueStackPushOptimized() {
        queueOne = new LinkedListQueue<>();
        queueTwo = new LinkedListQueue<>();
    }

    @Override
    public void push(T value) {
        queueTwo.enqueue(value);
    }

    @Override
    public T pop() {
        if (queueTwo.isEmpty()) {
            return null;
        }
        int counter = 0;
        while (!queueTwo.isEmpty()) {
            T value = queueTwo.dequeue();
            queueOne.enqueue(value);
            counter++;
        }

        for (int i = 0; i < counter - 1; i++) {
            T reverseOrderValue = queueOne.dequeue();
            queueTwo.enqueue(reverseOrderValue);
        }

        T pushValue = queueOne.dequeue();
        return pushValue;
    }

    @Override
    public boolean isEmpty() {
        return (queueTwo.isEmpty());

    }
}
