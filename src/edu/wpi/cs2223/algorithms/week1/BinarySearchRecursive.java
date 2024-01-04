package edu.wpi.cs2223.algorithms.week1;

/**
 * Implements binary array search recursively.
 */
public class BinarySearchRecursive implements ArraySearch{
    @Override
    public int find(int[] sortedArray, int target) {
        // empty array base case
        if (sortedArray.length == 0) {
            return -1;
        }

        return find(sortedArray, target, 0, sortedArray.length -1, 0);
    }

    int find(int[] sortedArray, int target, int low, int high, int iterations) {
        if (low <= high) {
            iterations++;
            int midpoint = (high + low) / 2;

            if (sortedArray[midpoint] == target) {
                System.out.printf("Found value in array of size %d after %d iteration.\n", sortedArray.length, iterations);
                return midpoint;
            }

            // recurse on the right half of the current range
            if (target > sortedArray[midpoint]) {
                return find(sortedArray, target, midpoint + 1, high, iterations);
            }

            // recurse on the left half of the current range
            return find(sortedArray, target, low, midpoint -1, iterations);
        }

        // traversed array and did not find target
        System.out.printf("Failed to find value in array of size %d after %d iteration.\n", sortedArray.length, iterations);
        return -1;
    }
}
