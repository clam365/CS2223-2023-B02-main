package edu.wpi.cs2223.algorithms.shared;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * A simple generic queue backed by generic nodes.
 *
 * @param <T> type of value stored in the queue.
 */
public class LinkedListQueue<T> implements Queue<T>, Iterable<T> {

    Node<T> first = null;
    Node<T> last = null;

    public void enqueue(T value){
        if (first == null) {
            Node<T> newNode = new Node<>(null, value);
            first = newNode;
            last = newNode;
            return;
        }

        Node<T> newLast = new Node<>(null, value);
        last.next = newLast;
        last = newLast;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListQueueIterator();
    }

    class LinkedListQueueIterator implements Iterator<T> {

        Node<T> current;

        @Override
        public boolean hasNext() {
            checkForModifications();
            return current != null;
        }

        @Override
        public T next() {
            checkForModifications();

            T toReturn = current.value;
            current = current.next;

            return toReturn;
        }

        // use to detect Illegal
        Node<T> constructionTimeFirst;
        Node<T> constructionTimeLast;

        public LinkedListQueueIterator() {
            this.current = first;
            this.constructionTimeFirst = first;
            this.constructionTimeLast = last;
        }

        void checkForModifications(){
            if (constructionTimeFirst != first || constructionTimeLast != last) {
                throw new ConcurrentModificationException("state of queue has been modified");
            }
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    public T dequeue(){
        if (first == null) {
            return null;
        }
        T returnValue = first.value;
        first = first.next;

        return returnValue;
    }

    public boolean isEmpty(){
        return first == null;
    }
}
