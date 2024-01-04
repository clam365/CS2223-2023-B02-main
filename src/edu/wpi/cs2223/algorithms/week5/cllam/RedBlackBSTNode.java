package edu.wpi.cs2223.algorithms.week5.cllam;

/**
 * Node of a red-black binary search tree with keys of type Key and values of type Value.
 */
public class RedBlackBSTNode<Key, Value> {
    RedBlackBSTNode<Key, Value> leftChild;
    RedBlackBSTNode<Key, Value> rightChild;

    Key key;
    Value value;

    boolean isRed = false;

    public RedBlackBSTNode(RedBlackBSTNode<Key, Value> leftChild, RedBlackBSTNode<Key, Value> rightChild, Key key, Value value) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.key = key;
        this.value = value;
    }
}
