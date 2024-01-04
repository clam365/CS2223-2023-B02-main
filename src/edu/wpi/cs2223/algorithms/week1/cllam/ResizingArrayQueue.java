package edu.wpi.cs2223.algorithms.week1.cllam;

import edu.wpi.cs2223.algorithms.shared.Queue;


/**
 * A simple generic queue backed by fixed capacity array that can resize.
 *
 * @param <T> type of value stored in the queue.
 *
 * 1. The worst case number of operations that my implementation would need to perform when enqueue is 2N
 *    because if you run out of array space, you must double it and iterate to copy through it and queue.
 *
 * 2. The worst case number of operations when dequeue is called in terms of N is still N. Enqueue handles all the upfront
 *    tasks of doubling, dequeue just needs to get past one specific index in an array.
 *
 * 3. When isEmpty is called, the worst case number of operations is 1 in terms of N. If we have completely iterated
 *    through the entire array or it started out empty, it only checks once with a boolean operation.
 *
 * 4. We would want to shrink the size of the backing array for optimization of memory usage and efficiency, along with performance.
 *    To implement that, we can analyze the array through the dequeue method and resize to shrink.
 *    To make sure we are not stuck in a growing/shrinking/growing cycle when adding single elements, we can check if it
 *    passes a certain threshold say like if it's less than 25% or anything like that, then continue with our shrink method.
 */
public class ResizingArrayQueue<T> implements Queue<T> {

    //Basic Array Setup
    int first = 0;
    int last = 0;
    T arrayQueue[];
    int size;
    int pushSize = 0;

    public ResizingArrayQueue(int arraySize) {
        //creating array
        this.size = arraySize;
        pushSize = size;
        this.arrayQueue = ( (T[])new Object[size]);
    }
    public void enqueue(T value){
        if (first == -1) {
            first ++;
            return;
        }

        if (size == arrayQueue.length) {
            resize(2 * arrayQueue.length);
            size = arrayQueue.length;
        }
        arrayQueue[last] = value;
        last++;
    }

    public void resize(int newCapacity) {
        T newArrayQueue[] = ( (T[])new Object[newCapacity]);
        for (int i = 0; i < arrayQueue.length; i++) {
            newArrayQueue[i] = arrayQueue[i];
        }
        arrayQueue = newArrayQueue;
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
