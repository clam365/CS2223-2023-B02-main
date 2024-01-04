package edu.wpi.cs2223.algorithms.shared;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Simple array backed stack implementation where the size of the array, and thus maximum size of
 * stack, is determined at construction time.
 *
 * @param <T> type of elements stored in stack.
 */
public class FixedCapacityArrayStack<T> implements Stack<T>, Iterable<T>{
    final int size;
    final T[] values;

    int head = -1;

    public FixedCapacityArrayStack(int size) {
        this.size = size;

        // Java does not support instantiation of a generically typed array, so we have to
        // create an array of Objects and cast it to our generic Array
        // the reasons for this limitation are involved but you are welcome to research them :)
        this.values = ((T[])new Object[size]);
    }

    @Override
    public void push(T value) {
        head++;
        values[head] = value;
    }

    @Override
    public T pop() {
        if (head == -1) {
            return null;
        }

        T returnValue = values[head];
        head--;
        return returnValue;
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator(values, head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    class StackIterator implements Iterator<T> {
        final T[] values;
        int head;

        public StackIterator(T[] values, int head) {
            this.values = values;
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head >= 0;
        }

        @Override
        public T next() {
            T val = values[head];
            head--;
            return val;
        }
    }
}

