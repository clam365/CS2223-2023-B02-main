package edu.wpi.cs2223.algorithms.week1;

/* Looping through ENTIRE array one by one from left to right */
public class LinearSearch implements ArraySearch {

    @Override
    public int find(int[] sortedArray, int target) {
        for (int index = 0; index < sortedArray.length; index++) {
            if (sortedArray[index] == target) {
                returnWithLog(index, index, sortedArray.length);
            }
        }

        // base case of empty array or case of us exhausting the array
        return returnWithLog(-1, sortedArray.length, sortedArray.length);
    }

    int returnWithLog(int returnValue, int loopIterations, int inputArrayLengths) {
        System.out.printf("Returning %d after %d iterations for an array of size %d\n", returnValue, loopIterations, inputArrayLengths);
        return returnValue;
    }
}
