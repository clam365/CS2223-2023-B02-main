package edu.wpi.cs2223.algorithms.week6;

/**
 * Array backed implementation of a hash table.
 */
public class HashTable {
    int m = 100;

    int hashKey(String key) {
        return hashCodeOne(key) % m;
    }

    int hashCodeOne(String key) {
        return key.hashCode() & 0x7fffffff;
    }

}
