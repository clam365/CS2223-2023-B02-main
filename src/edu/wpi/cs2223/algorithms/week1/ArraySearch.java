package edu.wpi.cs2223.algorithms.week1;

/**
 * Implements a search algorithm against an array of integers.
 */
public interface ArraySearch {
    /**
     * @return the index of target in sortedArray if it exists there.  Otherwise -1;
     */
    int find(int[] sortedArray, int target);
}
