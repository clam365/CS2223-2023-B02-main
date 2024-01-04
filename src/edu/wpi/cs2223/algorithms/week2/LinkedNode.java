package edu.wpi.cs2223.algorithms.week2;

/**
 * Generic singly-linked node interface with support for updates of its next reference.
 */
public interface LinkedNode<T> {
    LinkedNode<T> next();
    T value();

    void updateNext(LinkedNode<T> next);
}
