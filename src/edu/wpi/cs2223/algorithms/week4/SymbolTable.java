package edu.wpi.cs2223.algorithms.week4;

/**
 * Symbol table whose keys are of type Key and values of type Value.
 */
public interface SymbolTable<Key extends Comparable, Value> {
    /**
     * Associates value with key.  If an association had been made previously, that value is overwritten with the
     * value passed in here.  Remove key from table if value is null.
     */
    void put(Key key, Value value);

    /**
     * Retrieve the value associated with the given key.  Return null if no association for the given key exists.
     */
    Value get(Key key);

    /**
     * Removes key from the symbol table.  If key did not exist in the table, return null; if key did exist in the
     * table, return its value prior to removal.
     */
    Value delete(Key key);

    /**
     * Returns true iff an association for the given key exists in this table.
     */
    boolean contains(Key key);

    /**
     * Returns true iff this table contains no keys.
     */
    boolean isEmpty();

    /**
     * Number of keys with associations in this table.
     */
    int size();
}
