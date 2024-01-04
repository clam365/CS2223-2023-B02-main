package edu.wpi.cs2223.algorithms.week4;

/**
 * Node of a binary search tree with keys of type Key and values of type Value.
 */
public class BSTNode<Key, Value> {
    public BSTNode<Key, Value> leftChild;
    public BSTNode<Key, Value> rightChild;

    public Key key;
    public Value value;

    public boolean isActive = true;

    public BSTNode(BSTNode<Key, Value> leftChild, BSTNode<Key, Value> rightChild, Key key, Value value) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.key = key;
        this.value = value;
    }

    public void deActivate(){
        isActive = false;
    }

    public void activate(){
        isActive = true;
    }
}
