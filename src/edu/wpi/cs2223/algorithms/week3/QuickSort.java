package edu.wpi.cs2223.algorithms.week3;

import edu.wpi.cs2223.algorithms.week2.SortAlgorithm;
import edu.wpi.cs2223.algorithms.week3.SortHelper;

import java.util.*;

public class QuickSort <T extends Comparable> implements SortAlgorithm<T> {
    SortHelper sortHelper = new SortHelper();
    int numberOfComparisons = 0;

    int partition(T[] input, int low, int high){
        int currentLeft = low;
        int currentRight = high;

        T partitionValue = input[currentLeft];
        currentLeft++;

        // identify values to swap - we want values larger than partitionValue from the left
        // side to be sent to the right side, and values smaller than partitionValue from the right
        // side to be sent to the right
        while (currentLeft <= currentRight) {
            // find next value from left side to swap
            while (input[currentLeft].compareTo(partitionValue) < 0 && currentLeft != high) {
                currentLeft++;
            }

            // find next value from right side to swap
            while (partitionValue.compareTo(input[currentRight]) < 0 && currentRight != low) {
                currentRight--;
            }

            // perform the swap unless left and right have criss-crossed
            if (currentLeft < currentRight) {
                exchange(input, currentLeft, currentRight);
                System.out.printf("After swapping %d and %d, array looks like: %s.\n",
                        input[currentLeft], input[currentRight], sortHelper.printArrayState(input));
            } else {
                break;
            }
        }

        // put partition item into the partition location
        exchange(input, low, currentRight);
        return currentRight;
    }

    public T select(T[] input, int targetElement) {
        // shuffle input to reduce likelihood of bad partition points
        // this is done only once, before the actual algorithm starts and is an
        // O(n) operation
        List<T> inputAsLst = Arrays.asList(input);
        Collections.shuffle(inputAsLst);
        inputAsLst.toArray(input);

        int low = 0;
        int high = input.length -1;

        int currentPartitionIndex = 0;

        while (high > low) {
            currentPartitionIndex = partition(input, low, high);

            if (targetElement > currentPartitionIndex) {
                low = currentPartitionIndex + 1;
                continue;
            }

            if (targetElement < currentPartitionIndex) {
                high = currentPartitionIndex - 1;
                continue;
            }

            break;
        }

        return input[currentPartitionIndex];
    }

    @Override
    public void sort(T[] input) {
        // shuffle input to reduce likelihood of bad partition points
        // this is done only once, before the actual algorithm starts and is an
        // O(n) operation
        List<T> inputAsLst = Arrays.asList(input);
        Collections.shuffle(inputAsLst);
        inputAsLst.toArray(input);

        sortHelper = new SortHelper();
        numberOfComparisons = 0;

        sort(input, 0, input.length -1);
    }

    void sort(T[] input, int low, int high) {
        if (low >= high) {
            return;
        }

        int partitionIndex = partition(input, low, high);
        sort(input, low, partitionIndex - 1);
        sort(input, partitionIndex + 1, high);
    }

    @Override
    public void exchange(T[] array, int indexOne, int indexTwo) {
        sortHelper.exchange(array, indexOne, indexTwo);
    }

    @Override
    public int numberOfExchanges() {
        return sortHelper.numberOfExchanges();
    }

    @Override
    public int numberOfComparisons() {
        return numberOfComparisons;
    }
}
