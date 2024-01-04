package edu.wpi.cs2223.algorithms.week6.cllam;

import edu.wpi.cs2223.algorithms.week4.SymbolTable;

/**
 * Array backed hash table that resolves collisions via the linear probing technique.s
 *
 * 1. The trade-offs between separate chaining and linear probing collision resolution techniques
 *      - memory: separate chaining gradually increases memory but decreases performance
 *          - linear probing uses less wasted space, but clustering can happen
 *      - structure: separate chaining is easier to develop b/c of linked lists but with
 *        linear probing u must delete the index but rehash and probing sequence changes
 *
 * 2. M = size of our backing array. N = total # of unique keys being stored in
 *    M should be double of our N, so that a = N/M is approximately 1/2. If M
 *    is too large, we have too many empty array entries but if M is too small our search time
 *    is going to die. With a = 1/2 we are able to have a perfect amount of extra space for extra keys
 *    but also handle deletion.
 *
 */
public class LinearProbingHashTable<Key extends Comparable, Value> implements SymbolTable<Key, Value> {
    final int numberOfSlots;
    final Key[] keys;
    final Value[] values;

    int size = 0;

    public LinearProbingHashTable(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;

        keys = (Key[]) new Comparable[numberOfSlots];
        values = (Value[]) new Comparable[numberOfSlots];
    }

    @Override
    public void put(Key key, Value value) {
        int index = indexForKey(key);

        if (key.equals(keys[index])) {
            values[index] = value;
            return;
        }

        keys[index] = key;
        values[index] = value;
        size++;
    }

    @Override
    public Value get(Key key) {
        int index = indexForKey(key);
        return values[index];
    }

    @Override
    public Value delete(Key key) {
        int index = indexForKey(key); //Finding our index for deletion

        // Base Case of when key doesn't exist
        if (!key.equals(keys[index])) {
            return null;
        }

        // Deleting the key by making it null along with its value index, decreasing overall size
        keys[index] = null;
        Value deleted = values[index]; //temp to hold
        values[index] = null;
        size--;

        // Rehashing and reinserting keys and values to maintain our structure from linear probing
        index = (index + 1) % numberOfSlots;
        // Loop to go through and place it all back
        while (keys[index] != null) {
            Key currentKey = keys[index];
            Value currentValue = values[index];

            //reset
            keys[index] = null;
            values[index] = null;

            size--; //decrement size

            // Placing the current key/value back
            put(currentKey, currentValue);
            index = (index + 1) % numberOfSlots; //go up an index
        }

        return deleted; //solution
    }


    @Override
    public boolean contains(Key key) {
        int index = indexForKey(key);
        return keys[index] != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * @return the index where the given key is stored after hashing and linear probing has been applied;
     * null if key is not present in this symbol table
     */
    int indexForKey(Key key) {
        int index = (key.hashCode() & 0x7fffffff) % numberOfSlots;
        while (keys[index] != null && !key.equals(keys[index])) {
            index = (index + 1) % numberOfSlots;
        }

        return index;
    }
}
