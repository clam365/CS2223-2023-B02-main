package edu.wpi.cs2223.algorithms.week1;

/**
 * Given a sorted list of integers, where values may be repeated, finds the number of times a given integer appears
 * in the array.
 */
public class BinarySearchWithDuplicates {

    int findLowerBound(int[] sortedArray, int target) {
        //Base Case
        if (sortedArray.length == 0) {
            return -1;
        }
        // Bounds
        int lowerRange = 0;
        int upperRange = sortedArray.length - 1;
        int lowerBound = -1;

        //Binary Search Now for Duplicates
        while (lowerRange <= upperRange) {
            //Getting Midpoint of array
            int middlePoint = (lowerRange + upperRange) / 2;
            int midPointValue = sortedArray[middlePoint];

            if (target == midPointValue) {
                lowerBound = middlePoint;
                upperRange = middlePoint - 1;
            }

            if (target > midPointValue) {
                lowerRange = middlePoint + 1;
            } else {
                upperRange = middlePoint - 1;
            }
        }
        return lowerBound;
    }

    int findUpperBound(int[] sortedArray, int target) {
        //Base Case
        if (sortedArray.length == 0) {
            return -1;
        }
        // Bounds
        int lowerRange = 0;
        int upperRange = sortedArray.length - 1;
        int upperBound = -1;

        //Binary Search Now for Duplicates
        while (lowerRange <= upperRange) {
            //Getting Midpoint of array
            int middlePoint = (lowerRange + upperRange) / 2;
            int midPointValue = sortedArray[middlePoint];

            if (target == midPointValue) {
                upperBound = middlePoint;
                lowerRange = middlePoint + 1;
            }

            if (target > midPointValue) {
                lowerRange = middlePoint + 1;
            } else {
                upperRange = middlePoint - 1;
            }
        }
        return upperBound;
    }

    int count(int[] sortedArray, int target) {
        int lowerBound = findLowerBound(sortedArray, target);
        //Empty Array
        if (lowerBound == -1) {
            return 0;
        }

        int upperBound = findUpperBound(sortedArray, target);

        //Find Total Amount of Targets
        return (upperBound - lowerBound) + 1;
    }


}
