package edu.wpi.cs2223.algorithms.week1.cllam;

import edu.wpi.cs2223.algorithms.shared.LinkedListQueue;
import edu.wpi.cs2223.algorithms.shared.Queue;
import edu.wpi.cs2223.algorithms.shared.Stack;


/**
 * Stack implementation using Two Linked Node Queues and optimizing Pop (putting load on push)
 *
 * 1. In terms of N, the worst case number of operation on push would be N. There is only one loop, then we need to
 *    copy the queue Stack to the other one to get a correct answer.
 *
 * 2. The most challenging part to make push work was definitely understanding it at first, just like for Pop.
 *    Once I got the diagrams down, I was able to implement correctly.
 *    The push method takes in the input wanted to be stacked into Q1, and we must move all values from our actual stack
 *    (Q2) onto Q1 into order. Afterwards, we just make Q1 into Q2 and reset Q1 to be empty.
 **/
public class TwoQueueStackPopOptimized<T> implements Stack<T> {

    Queue<T> queueOne;
    Queue<T> queueTwo;

    public TwoQueueStackPopOptimized() {
        queueOne = new LinkedListQueue<>();
        queueTwo = new LinkedListQueue<>();
    }

    @Override
    public void push(T value) {
        queueOne.enqueue(value);

        while (!queueTwo.isEmpty()) {
            T q2Value = queueTwo.dequeue();
            queueOne.enqueue(q2Value);
        }
        Queue<T> temp;
        temp = queueOne;
        queueOne = queueTwo;
        queueTwo = temp;

    }

    @Override
    public T pop() {
        if (queueTwo.isEmpty()) {
            return null;
        }
        T value = queueTwo.dequeue();
        return value;
    }

    @Override
    public boolean isEmpty() {
        return queueTwo.isEmpty();

    }
}
