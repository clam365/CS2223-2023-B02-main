package edu.wpi.cs2223.algorithms.week1;

/**
 * Implements the binary search algorithm against a sorted array of integers.
 * Each iteration of the algorithm looks for the target at the midpoint index of the array, if the value at
 * the midpoint is:
 * <li>an exact match, then return midpoint</li>
 * <li>less than the target, repeat the iteration on the right half of the current array</li>
 * <li>greater than the target, repeat the iteration on the left half of the current array</li>
 */
public class BinarySearch implements ArraySearch {
    public int find(int[] sortedArray, int target) {
        int loopIterations = 0;

        // empty arrays contain no targets CASE
        if (sortedArray.length == 0) {
            return returnWithLog(-1, loopIterations, sortedArray.length);
        }

        int lowerRange = 0;
        int upperRange = sortedArray.length - 1;

        while (lowerRange <= upperRange) {
            loopIterations++;

            int midpoint = (upperRange + lowerRange) / 2;
            int valueAtMidPoint = sortedArray[midpoint];

            if (target == valueAtMidPoint) {
                return returnWithLog(midpoint, loopIterations, sortedArray.length);
            }

            // narrow range to either left or right half based on comparison to midpoint
            if (target > valueAtMidPoint) {
                lowerRange = midpoint + 1;
            }
            else {
                upperRange = midpoint - 1;
            }
        }

        return returnWithLog(-1, loopIterations, sortedArray.length);
    }

    int returnWithLog(int returnValue, int loopIterations, int inputArrayLengths) {
//        System.out.printf("Returning %d after %d iterations for an array of size %d\n", returnValue, loopIterations, inputArrayLengths);
        return returnValue;
    }
}
