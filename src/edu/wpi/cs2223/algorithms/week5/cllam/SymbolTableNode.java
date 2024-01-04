package edu.wpi.cs2223.algorithms.week5.cllam;

/**
 * A node for a linked list backed Symbol Table implementation.
 */
public class SymbolTableNode<Key, Value> {
    Key key;
    Value value;

    SymbolTableNode<Key, Value> next;

    public SymbolTableNode(Key key, Value value, SymbolTableNode<Key, Value> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
