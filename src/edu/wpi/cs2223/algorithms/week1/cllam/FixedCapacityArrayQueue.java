package edu.wpi.cs2223.algorithms.week1.cllam;

import edu.wpi.cs2223.algorithms.shared.Node;
import edu.wpi.cs2223.algorithms.shared.Queue;

import java.util.Arrays;

/**
 * A simple generic queue backed by fixed capacity array.
 *
 * @param <T> type of value stored in the queue.
 *
 * 1. Node backed implementation is better because it allows dynamic memory growth and memory efficiency with each additional node.
 *    With an array implementation we can access elements when needed and is more efficient when having a fixed size.
 * 2. There will be an exception (most likely Out of Bounds); this will require us to increase the array size.
 */
public class FixedCapacityArrayQueue<T> implements Queue<T> {

    //Basic Array Setup
    int first = 0;
    int last = 0;
    T arrayQueue[];
    final int size;

    public FixedCapacityArrayQueue(int arraySize) {
        //creating array
        this.size = arraySize;
        this.arrayQueue = ( (T[])new Object[size]);
    }
    public void enqueue(T value){
        if (first == -1) {
            first ++;
            return;
        }

        arrayQueue[last] = value;
        last++;
    }

    public T dequeue(){
        if (first == -1) {
            return null; // if there are no elements left
        }
        T returnValue = arrayQueue[first];
        first ++;

        return returnValue;
    }

    public boolean isEmpty(){
        if (first == last) {
            return true;
        }
        else {
            return false;
        }
    }
}
