package edu.wpi.cs2223.algorithms.week3;

import edu.wpi.cs2223.algorithms.week2.SortAlgorithm;

public class MergeSort<T extends Comparable> implements SortAlgorithm<T> {
    int numberOfExchanges = 0;
    int numberOfComparisons = 0;

    void merge(T[] input, T[] aux, int low, int mid, int high){
        // nothing to do for single element
        if (high == low) {
            return;
        }

        int currentLeft = low;
        int currentRight = mid + 1;

        // copy input segment into aux array so that we can operate on aux and write sorted values to input
        for (int a = low; a <= high; a++) {
            aux[a] = input[a];
        }

        for (int index = low; index <= high; index++){
            numberOfExchanges++;

            // have we exhausted the left side completely? if so just take from right
            if (currentLeft > mid) {
                input[index] = aux[currentRight];
                currentRight++;
                continue; // continue skips all logic below and moves onto next iteration of for loop
            }

            // have we exhausted the right side completely? if so just take from left
            if (currentRight > high) {
                input[index] = aux[currentLeft];
                currentLeft++;
                continue;
            }

            numberOfComparisons++;
            // if we get here, neither left nor right has been exhausted, so compare current left to current right
            // and take the smaller value
            if (aux[currentLeft].compareTo(aux[currentRight]) > 0){
                input[index] = aux[currentRight];
                currentRight++;
            } else {
                input[index] = aux[currentLeft];
                currentLeft++;
            }
        }
    }

    @Override
    public void sort(T[] input) {
        numberOfExchanges = 0;
        numberOfComparisons = 0;

        T[] aux = (T[]) new Comparable  [input.length];
        sortDoWork(input, aux, 0, input.length - 1);
    }

    void sortDoWork(T[] input, T[] aux, int low, int high){
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        sortDoWork(input, aux, low, mid);
        sortDoWork(input, aux, mid+1, high);

        merge(input, aux, low, mid, high);
    }

    @Override
    public void exchange(T[] array, int indexOne, int indexTwo) {
        throw new UnsupportedOperationException("merge sort implements its own exchanges.");
    }

    @Override
    public int numberOfExchanges() {
        return numberOfExchanges;
    }

    @Override
    public int numberOfComparisons() {
        return numberOfComparisons;
    }
}
